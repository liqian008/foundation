package com.bruce.foundation.admin.service.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.admin.mapper.security.AdminRoleMapper;
import com.bruce.foundation.admin.mapper.security.AdminRoleResourceMapper;
import com.bruce.foundation.admin.mapper.security.AdminUserRoleMapper;
import com.bruce.foundation.admin.model.security.AdminResource;
import com.bruce.foundation.admin.model.security.AdminResourceCriteria;
import com.bruce.foundation.admin.model.security.AdminRole;
import com.bruce.foundation.admin.model.security.AdminRoleCriteria;
import com.bruce.foundation.admin.model.security.AdminRoleResource;
import com.bruce.foundation.admin.model.security.AdminRoleResourceCriteria;
import com.bruce.foundation.admin.model.security.AdminUserRole;
import com.bruce.foundation.admin.model.security.AdminUserRoleCriteria;
import com.bruce.foundation.admin.service.security.AdminRoleService;
import com.bruce.foundation.enumeration.StatusEnum;
import com.bruce.foundation.model.paging.PagingResult;

@Service
public class AdminRoleServiceImpl implements AdminRoleService{ 

	private static Logger logger = LoggerFactory.getLogger(AdminRoleServiceImpl.class);
	
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;
	@Autowired
	private AdminRoleResourceMapper adminRoleResourceMapper;

	@Override
	public int save(AdminRole adminRole) {
		return adminRoleMapper.insert(adminRole);
	}

	@Override
	public int updateById(AdminRole adminRole) {
		return adminRoleMapper.updateByPrimaryKeySelective(adminRole);
	}

	@Override
	public int updateByCriteria(AdminRole t, AdminRoleCriteria criteria) {
		return adminRoleMapper.updateByExampleSelective(t, criteria);
	}
	
	@Override
	public int deleteById(Integer id) {
		return adminRoleMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int deleteByCriteria(AdminRoleCriteria criteria) {
		return adminRoleMapper.deleteByExample(criteria);
	}

	@Override
	public AdminRole loadById(Integer id) {
		return adminRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdminRole> queryAll() {
		return adminRoleMapper.selectByExample(null);
	}
	
	@Override
	public List<AdminRole> queryAll(String orderByClause) {
		AdminRoleCriteria criteria = new AdminRoleCriteria();
		criteria.setOrderByClause(orderByClause);
		return adminRoleMapper.selectByExample(criteria);
	}
	
	@Override
	public List<AdminRole> queryByCriteria(AdminRoleCriteria criteria) {
		return adminRoleMapper.selectByExample(criteria);
	}
	

	@Override
	public List<AdminRole> fallloadByCriteria(int pageSize, AdminRoleCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<AdminRole> pagingByCriteria(int pageNo, int pageSize, AdminRoleCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?20:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new AdminRoleCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = adminRoleMapper.countByExample(criteria);
		List<AdminRole> dataList = adminRoleMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<AdminRole>(pageNo, pageSize, count, dataList);
	}

	
	@Override
	public List<AdminRole> queryRoles(Short status) {
		AdminRoleCriteria criteria = null;
		if(status!=null){
			criteria= new AdminRoleCriteria();
			criteria.createCriteria().andStatusEqualTo(StatusEnum.ENABLE.getStatus());
		}
		return adminRoleMapper.selectByExample(criteria);
	}
	


	@Override
	public List<AdminRole> queryRolesByUserId(Integer userId, Short status) {
		AdminUserRoleCriteria criteria = new AdminUserRoleCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		List<AdminUserRole> userRolesList =  adminUserRoleMapper.selectByExample(criteria);
		if(userRolesList!=null&&userRolesList.size()>0){
			//此处未使用联合查询，而是使用了两次查询（考虑是后台系统，所以忽视效率问题）
			List<Integer> roleIdList = new ArrayList<Integer>();
			for(AdminUserRole userRole: userRolesList){
				roleIdList.add(userRole.getRoleId());
			}
			AdminRoleCriteria roleCriteria = new AdminRoleCriteria();
			AdminRoleCriteria.Criteria subCriteria = roleCriteria.createCriteria().andIdIn(roleIdList);
			if(status!=null){
				subCriteria.andStatusEqualTo(status);
			}
			return adminRoleMapper.selectByExample(roleCriteria);
		}
		return null;
	}

	@Override
	public int updateRoleResources(Integer roleId, List<Integer> resourceIdList) {
		//先清空角色的权限列表
		deleteResourcesByRoleId(roleId);
		
		if(resourceIdList!=null&&resourceIdList.size()>0){
			for(int menuId: resourceIdList){
				AdminRoleResource adminRoleResource = new AdminRoleResource();
				adminRoleResource.setRoleId(roleId);
				adminRoleResource.setResourceId(menuId);
				adminRoleResourceMapper.insert(adminRoleResource);
			}
			return resourceIdList.size();
		}
		return 0;
	}
	
	@Override
	public int deleteResourcesByRoleId(Integer roleId) {
		AdminRoleResourceCriteria criteria = new AdminRoleResourceCriteria();
		criteria.createCriteria().andRoleIdEqualTo(roleId);
		return adminRoleResourceMapper.deleteByExample(criteria);
	}


}
