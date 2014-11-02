package com.bruce.foundation.admin.controller.security;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.admin.controller.BaseController;
import com.bruce.foundation.admin.model.security.AdminResource;
import com.bruce.foundation.admin.model.security.AdminRole;
import com.bruce.foundation.admin.model.security.AdminRoleCriteria;
import com.bruce.foundation.admin.service.security.AdminResourceService;
import com.bruce.foundation.admin.service.security.AdminRoleService;
import com.bruce.foundation.enumeration.StatusEnum;
import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.foundation.util.ValidatorUtil;


@Controller
@RequestMapping("/sys")
public class AdminRoleController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AdminRoleController.class);
	
	private static final int pageSize = 50;
	
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private AdminResourceService adminResourceService;
	
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/rolePaging")
	public String rolePaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		AdminRoleCriteria criteria =  null;
		
		PagingResult<AdminRole> adminRoleList = adminRoleService.pagingByCriteria(pageNo, pageSize , criteria);
		if(adminRoleList!=null){
			adminRoleList.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap();
			queryMap.putAll(request.getParameterMap());
			adminRoleList.setQueryMap(queryMap);
			model.addAttribute("adminRoleList", adminRoleList);
		}
		return "sys/roleListPaging";
	}
	
	
	@RequestMapping("/roles")
	public String roleList(Model model, String roleName, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<AdminRole> adminRoleList = adminRoleService.queryAll();
		model.addAttribute("adminRoleList", adminRoleList);
		return "sys/roleList";
	}
	
	@RequestMapping("/roleAdd")
	public String roleAdd(Model model, AdminRole adminRole, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		adminRole.setStatus(StatusEnum.ENABLE.getStatus());
		model.addAttribute("adminRole", adminRole);
		return "sys/roleEdit";
	}
	
	@RequestMapping("/roleEdit")
	public String roleEdit(Model model,int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		AdminRole adminRole = adminRoleService.loadById(id);
		model.addAttribute("adminRole", adminRole);
		return "sys/roleEdit";
	}
	
	
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	public String saveRole(Model model,AdminRole adminRole, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
//		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String roleName = adminRole.getRoleName();
		if(adminRole==null || StringUtils.isBlank(roleName)){
			model.addAttribute("message", "角色信息输入有误，请检查！");
			return "forward:/home/operationResult";
		}
		
		//过滤非法字符
		roleName = ValidatorUtil.filterUnSafeChar(roleName).trim();
		adminRole.setRoleName(roleName);
		
		Date currentTime = new Date();
		adminRole.setUpdateTime(currentTime);
		if(adminRole!=null&&adminRole.getId()!=null&&adminRole.getId()>0){
			result = adminRoleService.updateById(adminRole);
		}else{
			adminRole.setCreateTime(currentTime);
			result = adminRoleService.save(adminRole);
		}
		
		
		model.addAttribute("redirectUrl", "../sys/roles");
		return "forward:/home/operationRedirect";
	}
	
	
	@RequestMapping(value = "/delRole")
	public String delRole(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		//删除单个
		adminRoleService.deleteById(id);
		model.addAttribute("redirectUrl", "../sys/roles");
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 进入角色权限关联界面
	 * @param model
	 * @param roleId
	 * @param request
	 * @return
	 */
	@RequestMapping("/roleResourceSet")
	public String roleResourceSet(Model model,int roleId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		AdminRole adminRole = adminRoleService.loadById(roleId);
		
		//取所有资源
		List<AdminResource> allResources = adminResourceService.queryResources(null);
		//取角色拥有的资源
		List<AdminResource> roleResources = adminResourceService.queryResourcesByRoleId(roleId, null);
		
		model.addAttribute("adminRole", adminRole);
		model.addAttribute("allResources", allResources);
		model.addAttribute("roleResources", roleResources);
		
		return "sys/roleResourceSet";
	}
	
	/**
	 * 关联角色&权限
	 * @param model
	 * @param roleId
	 * @param resourceIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRoleResource", method = RequestMethod.POST)
	public String saveRoleResource(Model model,  Integer roleId, Integer[] resourceIds, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		if(roleId!=null && roleId>0){
//		    adminRoleService.deleteResourcesByRoleId(roleId);
		    if(resourceIds!=null && resourceIds.length>0){
			    List<Integer> resourceIdList = Arrays.asList(resourceIds);
			    result = adminRoleService.updateRoleResources(roleId, resourceIdList);
		    }
		}
		model.addAttribute("redirectUrl", "../sys/roles");
		return "forward:/home/operationRedirect";
	}
	
}
