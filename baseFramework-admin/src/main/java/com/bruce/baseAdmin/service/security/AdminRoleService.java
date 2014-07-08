package com.bruce.baseAdmin.service.security;

import java.util.List;


import com.bruce.base.service.IBaseService;
import com.bruce.baseAdmin.model.security.AdminRole;

public interface AdminRoleService extends IBaseService<AdminRole, Integer>{

	public List<AdminRole> getRolesByUserId(Integer userId);

	public List<AdminRole> getAvailableRoles();

	public int saveRoleResources(Integer roleId, List<Integer> menuIdList);
	
	public int deleteResourcesByRoleId(Integer roleId);
		
}
