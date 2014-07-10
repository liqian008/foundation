package com.bruce.foundation.admin.service.security;

import java.util.List;

import com.bruce.foundation.admin.model.security.AdminUser;
import com.bruce.foundation.admin.model.security.AdminUserCriteria;
import com.bruce.foundation.service.IBaseService;

public interface AdminUserService extends IBaseService<AdminUser, Integer, AdminUserCriteria> {

	public AdminUser loadUserByUsername(String username);
	
	public int changeUserPassword(int userId, String newPassword);

	public int saveUserRoles(Integer userId, List<Integer> roleIdList);
	
	public int deleteRolesByUserId(Integer userId);

}
