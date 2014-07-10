package com.bruce.baseAdmin.service.security;

import java.util.List;

import com.bruce.base.service.IBaseService;
import com.bruce.baseAdmin.model.security.AdminUser;

public interface AdminUserService extends IBaseService<AdminUser, Integer> {

	public AdminUser loadUserByUsername(String username);
	
	public int changeUserPassword(int userId, String newPassword);

	public int saveUserRoles(Integer userId, List<Integer> roleIdList);
	
	public int deleteRolesByUserId(Integer userId);

}
