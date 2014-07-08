package com.bruce.baseAdmin.service.security.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bruce.baseAdmin.mapper.security.AdminResourceMapper;
import com.bruce.baseAdmin.mapper.security.AdminRoleResourceMapper;
import com.bruce.baseAdmin.model.security.AdminResource;
import com.bruce.baseAdmin.model.security.AdminResourceCriteria;
import com.bruce.baseAdmin.model.security.AdminRoleResource;
import com.bruce.baseAdmin.model.security.AdminRoleResourceCriteria;
import com.bruce.baseAdmin.security.WebSecurityMetadataSource;
import com.bruce.baseAdmin.service.security.AdminResourceService;
import com.bruce.baseAdmin.utils.AdminStatusEnum;
import com.bruce.baseAdmin.utils.ConstantsUtil;

@Service
public class AdminResourceServiceImpl implements AdminResourceService{ 

	private static Logger logger = LoggerFactory.getLogger(AdminResourceServiceImpl.class);
	
	@Autowired
	private WebSecurityMetadataSource securityMetadataSource;
	@Autowired
	private AdminResourceMapper adminResourceMapper;
	@Autowired
	private AdminRoleResourceMapper adminRoleResourceMapper;

	@Override
	public int save(AdminResource adminResource) {
		return adminResourceMapper.insert(adminResource);
	}

	@Override
	public int updateById(AdminResource adminResource) {
		return adminResourceMapper.updateByPrimaryKeySelective(adminResource);
	}

	@Override
	public int deleteById(Integer id) {
		return adminResourceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdminResource loadById(Integer id) {
		return adminResourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdminResource> queryAll() {
		return adminResourceMapper.selectByExample(null);
	}
	
	@Override
	public List<AdminResource> getChildResources(Integer parentResourceId) {
		AdminResourceCriteria criteria = new AdminResourceCriteria();
		criteria.createCriteria().andParentIdEqualTo(parentResourceId)
		.andNavMenuEqualTo(AdminStatusEnum.OPEN.getStatus());
		return adminResourceMapper.selectByExample(criteria);
	}
	
	@Override
    public List<AdminResource> getAvailableResources() {
	    AdminResourceCriteria criteria = new AdminResourceCriteria();
        criteria.createCriteria().andStatusEqualTo(AdminStatusEnum.OPEN.getStatus());
        return adminResourceMapper.selectByExample(criteria);
    }
	
	@SuppressWarnings("unchecked")
    @Override
    public List<AdminResource> getNavResources() {
		// 用户有权限的展示菜单
        List<AdminResource> allResources = null;
        
        List<GrantedAuthority> authList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<Integer> roleIdList = new ArrayList<Integer>();
        if (authList != null) {
            SimpleGrantedAuthority superAuthority = new SimpleGrantedAuthority(
                    ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER");
            if (authList.contains(superAuthority)) {// 超级用户取得所有资源菜单
                allResources = getAllNavResources();
            } else {//非超级用户
                for (GrantedAuthority authority : authList) {
                    String authorityName = authority.toString();
                    if (!(ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER").equals(authorityName)) {
                        String roleIdStr = authorityName.substring(ConstantsUtil.SECURITY_AUTHORITY_PREFIX.length(), authorityName.length());
                        Integer roleId = new Integer(roleIdStr);
                        roleIdList.add(roleId);
                    }
                }
                //普通用户取得有显示权的资源菜单
                allResources = getNavResourcesByRoleIds(roleIdList);
            }
        }
        if (true) {
        	//刷新资源
            securityMetadataSource.initResource();
        }
        return treeAdminResources(allResources);
    }
	
	
	/**
	 * 按层级整理菜单
	 * @param allResources
	 * @return
	 */
	private List<AdminResource> treeAdminResources(List<AdminResource> allResources) {
		List<AdminResource> resources = new ArrayList<AdminResource>();
		if(allResources!=null){
		    for (AdminResource resource : allResources) {
	            // 先遍历出第1级菜单
	            if (resource.getParentId() == 0) {
	                AdminResource currentResource = new AdminResource();
	                currentResource.setId(resource.getId());
	                currentResource.setResourceName(resource.getResourceName());
	                currentResource.setCode(resource.getCode());
	                currentResource.setUrl(resource.getUrl());
	                
	                resources.add(currentResource);
	                this.loadChildResources(currentResource, allResources);
	            }
	        }
		}
		return resources;
	}
	
	/**
	 * 加载子菜单
	 * @param currentResource
	 * @param allResources
	 */
	private void loadChildResources(AdminResource currentResource, List<AdminResource> allResources) {
		for (AdminResource resource : allResources) {
			// 如果是当前菜单的子菜单
			if (resource.getParentId() == currentResource.getId()){
				AdminResource childResource = new AdminResource();
				childResource.setId(resource.getId());
				childResource.setResourceName(resource.getResourceName());
				childResource.setCode(resource.getCode());
				childResource.setUrl(resource.getUrl());
				currentResource.addChild(childResource);
				// 递归
				this.loadChildResources(childResource, allResources);
			}
		}
	}
	
	@Override
	public List<AdminResource> getResourcesByRoleId(Integer roleId) {
		AdminRoleResourceCriteria criteria = new AdminRoleResourceCriteria();
		criteria.createCriteria().andRoleIdEqualTo(roleId);
		List<AdminRoleResource> roleResourcesList =  adminRoleResourceMapper.selectByExample(criteria);
		if(roleResourcesList!=null&&roleResourcesList.size()>0){
			//此处未使用联合查询，而是使用了两次查询（考虑是后台系统，所以忽视效率问题）
			List<Integer> resourceIdList = new ArrayList<Integer>();
			for(AdminRoleResource roleResource: roleResourcesList){
				resourceIdList.add(roleResource.getResourceId());
			}
			AdminResourceCriteria resourceCriteria = new AdminResourceCriteria();
			resourceCriteria.createCriteria().andIdIn(resourceIdList);
			return adminResourceMapper.selectByExample(resourceCriteria);
		}
		return null;
	}
	
	/**
	 * 获取所有的导航资源
	 * @return
	 */
	private List<AdminResource> getAllNavResources() {
        AdminResourceCriteria criteria = new AdminResourceCriteria();
        criteria.createCriteria().andNavMenuEqualTo(AdminStatusEnum.OPEN.getStatus());
        criteria.setOrderByClause("sort, id");
        return adminResourceMapper.selectByExample(criteria);
    }
	
	/**
	 * 根据给定的roleIdList合集获取所关联的导航资源
	 * @param roleIdList
	 * @return
	 */
	private List<AdminResource> getNavResourcesByRoleIds(List<Integer> roleIdList) {
		AdminRoleResourceCriteria criteria = new AdminRoleResourceCriteria();
		criteria.createCriteria().andRoleIdIn(roleIdList);
		List<AdminRoleResource> roleResourcesList =  adminRoleResourceMapper.selectByExample(criteria);
		if(roleResourcesList!=null&&roleResourcesList.size()>0){
			//此处未使用联合查询，而是使用了两次查询（考虑是后台系统，所以忽视效率问题）
			List<Integer> resourceIdList = new ArrayList<Integer>();
			for(AdminRoleResource roleResource: roleResourcesList){
				resourceIdList.add(roleResource.getResourceId());
			}
			AdminResourceCriteria resourceCriteria = new AdminResourceCriteria();
			//状态为开启状态 && 导航为显示状态
			resourceCriteria.createCriteria().andIdIn(resourceIdList)
			.andNavMenuEqualTo(AdminStatusEnum.OPEN.getStatus())
			.andStatusEqualTo(AdminStatusEnum.OPEN.getStatus());
			criteria.setOrderByClause("sort, id");
			return adminResourceMapper.selectByExample(resourceCriteria);
		}
		return null;
	}
	
	
	@Deprecated
    @Override
    @SuppressWarnings("unchecked")
    public void reloadResourcesForUser(HttpServletRequest request) {
        // 用户有权限的展示菜单
        List<AdminResource> allResources = null;
        
        List<GrantedAuthority> authList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<Integer> roleIdList = new ArrayList<Integer>();
        if (authList != null) {
            SimpleGrantedAuthority superAuthority = new SimpleGrantedAuthority(
                    ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER");
            if (authList.contains(superAuthority)) {
                // 超级用户取得所有资源菜单
                allResources = getAllNavResources();
            } else {
                for (GrantedAuthority authority : authList) {
                    String authorityName = authority.toString();
                    if (!(ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER").equals(authorityName)) {
                        String roleIdStr = authorityName.substring(
                                ConstantsUtil.SECURITY_AUTHORITY_PREFIX
                                        .length(), authorityName.length());
                        Integer roleId = new Integer(roleIdStr);
                        roleIdList.add(roleId);
                    }
                }
                // 普通用户取得有显示权的资源菜单
                allResources = getNavResourcesByRoleIds(roleIdList);
            }
        }

        List<AdminResource> resources = treeAdminResources(allResources);
        request.getSession().setAttribute("resources", resources);
        reloadCachedAuthories();
    }
    
    /**
     * 权限变更后，需要即时刷新以生效
     */
    
    @Deprecated
    private void reloadCachedAuthories() {
        if (true) {
            securityMetadataSource.initResource();
        }
    }
	
}
