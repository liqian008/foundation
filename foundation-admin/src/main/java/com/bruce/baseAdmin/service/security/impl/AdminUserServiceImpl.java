package com.bruce.baseAdmin.service.security.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.baseAdmin.mapper.security.AdminUserMapper;
import com.bruce.baseAdmin.mapper.security.AdminUserRoleMapper;
import com.bruce.baseAdmin.model.security.AdminRoleResourceCriteria;
import com.bruce.baseAdmin.model.security.AdminUser;
import com.bruce.baseAdmin.model.security.AdminUserCriteria;
import com.bruce.baseAdmin.model.security.AdminUserRole;
import com.bruce.baseAdmin.model.security.AdminUserRoleCriteria;
import com.bruce.baseAdmin.service.security.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService{ 

	private static Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;

	@Override
	public int save(AdminUser adminUser) {
		return adminUserMapper.insert(adminUser);
	}

	@Override
	public int updateById(AdminUser adminUser) {
		return adminUserMapper.updateByPrimaryKeySelective(adminUser);
	}

	@Override
	public int deleteById(Integer id) {
		return adminUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdminUser loadById(Integer id) {
		return adminUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdminUser> queryAll() {
		return adminUserMapper.selectByExample(null);
	}

	@Override
	public AdminUser loadUserByUsername(String username) {
		AdminUserCriteria criteria = new AdminUserCriteria();
		criteria.createCriteria().andUsernameEqualTo(username);
		List<AdminUser> adminUserList =  adminUserMapper.selectByExample(criteria);
		return adminUserList!=null&&adminUserList.size()==1?adminUserList.get(0):null;
	}
	
	@Override
	public int changeUserPassword(int userId, String encryptNewPassword) {
		AdminUser adminUser = new AdminUser();
		adminUser.setPassword(encryptNewPassword);
		
		AdminUserCriteria criteria = new AdminUserCriteria();
		criteria.createCriteria().andIdEqualTo(userId);
		return adminUserMapper.updateByExampleSelective(adminUser, criteria);
	}
	


	@Override
	public int saveUserRoles(Integer userId, List<Integer> roleIdList) {
		if(roleIdList!=null&&roleIdList.size()>0){
			for(int roleId: roleIdList){
				AdminUserRole adminUserRole = new AdminUserRole();
				adminUserRole.setUserId(userId);
				adminUserRole.setRoleId(roleId);
				adminUserRoleMapper.insert(adminUserRole);
			}
			return roleIdList.size();
		}
		return 0;
	}
	
	@Override
	public int deleteRolesByUserId(Integer userId) {
		AdminUserRoleCriteria criteria = new AdminUserRoleCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		return adminUserRoleMapper.deleteByExample(criteria);
	}

	
}
