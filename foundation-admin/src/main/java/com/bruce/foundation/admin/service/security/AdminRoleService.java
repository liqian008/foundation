package com.bruce.foundation.admin.service.security;

import java.util.List;


import com.bruce.foundation.admin.model.security.AdminRole;
import com.bruce.foundation.admin.model.security.AdminRoleCriteria;
import com.bruce.foundation.service.IBaseService;

public interface AdminRoleService extends IBaseService<AdminRole, Integer, AdminRoleCriteria>{

	public List<AdminRole> getRolesByUserId(Integer userId);

	public List<AdminRole> getAvailableRoles();

	public int saveRoleResources(Integer roleId, List<Integer> menuIdList);
	
	public int deleteResourcesByRoleId(Integer roleId);
		
}
