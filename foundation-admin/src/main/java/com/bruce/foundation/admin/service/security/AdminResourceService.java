package com.bruce.foundation.admin.service.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bruce.foundation.admin.model.security.AdminResource;
import com.bruce.foundation.admin.model.security.AdminResourceCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface AdminResourceService extends IFoundationService<AdminResource, Integer, AdminResourceCriteria>{
	
	/*获取指定状态的权限资源*/
	public List<AdminResource> queryResources(Short status);
	
	/*获取指定角色、指定状态下的权限资源*/
	public List<AdminResource> queryResourcesByRoleId(Integer roleId, Short status);
	
	/*获取子权限*/
	public List<AdminResource> queryChildResources(Integer parentId);
	
	/*获取可用的导航权限*/
	public List<AdminResource> queryNavResources();
	
	/*重新加载权限列表*/
	@Deprecated
	public void reloadResourcesForUser(HttpServletRequest request);
	
	
	//public List<AdminResource> getAllNavResources();
}
