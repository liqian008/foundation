package com.bruce.foundation.admin.service.security;

import java.util.List;


import com.bruce.foundation.admin.model.security.AdminRole;
import com.bruce.foundation.admin.model.security.AdminRoleCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface AdminRoleService extends IFoundationService<AdminRole, Integer, AdminRoleCriteria>{
	
	/*获取所有可用的角色*/
	public List<AdminRole> queryRoles(Short status);
	
	/*获取指定用户的所有角色*/
	public List<AdminRole> queryRolesByUserId(Integer userId, Short status);
	
	/*更新指定角色&权限的关联关系*/
	public int updateRoleResources(Integer roleId, List<Integer> resourceIdList);
	
	/*取消指定角色的所有权限*/
	public int deleteResourcesByRoleId(Integer roleId);
		
}
