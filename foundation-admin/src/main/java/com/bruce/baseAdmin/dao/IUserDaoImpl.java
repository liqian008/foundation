package com.bruce.baseAdmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.base.dao.IBaseDao;
import com.bruce.base.model.PagingResult;
import com.bruce.baseAdmin.mapper.security.AdminUserMapper;
import com.bruce.baseAdmin.mapper.security.AdminUserRoleMapper;
import com.bruce.baseAdmin.model.security.AdminUser;
import com.bruce.baseAdmin.model.security.AdminUserCriteria;

public class IUserDaoImpl implements IBaseDao<AdminUser, Integer, AdminUserCriteria> {

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
	public int updateByCriteria(AdminUser t, AdminUserCriteria criteria) {
		return adminUserMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteByCriteria(AdminUserCriteria criteria) {
		return adminUserMapper.deleteByExample(criteria);
	}

	@Override
	public List<AdminUser> queryByCriteria(AdminUserCriteria criteria) {
		return adminUserMapper.selectByExample(criteria);
	}

	@Override
	public List<AdminUser> fallloadByCriteria(int pageSize,
			AdminUserCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<AdminUser> pagingByCriteria(int pageNo, int pageSize,
			AdminUserCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<AdminUser> fallloadByCriteria(AdminUserCriteria criteria) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PagingResult<AdminUser> pagingByCriteria(AdminUserCriteria criteria) {
//		int count = adminUserMapper.countByExample(criteria);
//		List<AdminUser> adminUserList =  adminUserMapper.selectByExample(criteria);
//		return null;
//	}

}
