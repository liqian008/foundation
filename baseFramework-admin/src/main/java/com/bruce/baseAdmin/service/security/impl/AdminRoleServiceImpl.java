package com.bruce.baseAdmin.service.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.baseAdmin.mapper.security.AdminRoleMapper;
import com.bruce.baseAdmin.mapper.security.AdminRoleResourceMapper;
import com.bruce.baseAdmin.mapper.security.AdminUserRoleMapper;
import com.bruce.baseAdmin.model.security.AdminRole;
import com.bruce.baseAdmin.model.security.AdminRoleCriteria;
import com.bruce.baseAdmin.model.security.AdminRoleResource;
import com.bruce.baseAdmin.model.security.AdminRoleResourceCriteria;
import com.bruce.baseAdmin.model.security.AdminUserRole;
import com.bruce.baseAdmin.model.security.AdminUserRoleCriteria;
import com.bruce.baseAdmin.service.security.AdminRoleService;
import com.bruce.baseAdmin.utils.AdminStatusEnum;

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
	public int deleteById(Integer id) {
		return adminRoleMapper.deleteByPrimaryKey(id);
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
	public List<AdminRole> getAvailableRoles() {
		AdminRoleCriteria criteria = new AdminRoleCriteria();
		criteria.createCriteria().andStatusEqualTo(AdminStatusEnum.OPEN.getStatus());
		return adminRoleMapper.selectByExample(criteria);
	}
	


	@Override
	public List<AdminRole> getRolesByUserId(Integer userId) {
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
			roleCriteria.createCriteria().andIdIn(roleIdList);
			return adminRoleMapper.selectByExample(roleCriteria);
		}
		return null;
	}

	@Override
	public int saveRoleResources(Integer roleId, List<Integer> menuIdList) {
		if(menuIdList!=null&&menuIdList.size()>0){
			for(int menuId: menuIdList){
				AdminRoleResource adminRoleResource = new AdminRoleResource();
				adminRoleResource.setRoleId(roleId);
				adminRoleResource.setResourceId(menuId);
				adminRoleResourceMapper.insert(adminRoleResource);
			}
			return menuIdList.size();
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
