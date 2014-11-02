package com.bruce.foundation.admin.service.security;

import java.util.List;

import com.bruce.foundation.admin.model.security.AdminUser;
import com.bruce.foundation.admin.model.security.AdminUserCriteria;
import com.bruce.foundation.service.IFoundationPagingService;

public interface AdminUserService extends IFoundationPagingService<AdminUser, Integer, AdminUserCriteria> {
	
	public AdminUser loadUserByUsername(String username);
	
	public int changeUserPassword(int userId, String newPassword);

	public int updateUserRoles(Integer userId, List<Integer> roleIdList);
	
	public int deleteRolesByUserId(Integer userId);

}
