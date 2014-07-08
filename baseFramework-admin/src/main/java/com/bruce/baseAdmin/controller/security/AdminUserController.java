package com.bruce.baseAdmin.controller.security;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.baseAdmin.controller.BaseController;
import com.bruce.baseAdmin.model.security.AdminRole;
import com.bruce.baseAdmin.model.security.AdminUser;
import com.bruce.baseAdmin.security.WebUserDetails;
import com.bruce.baseAdmin.service.security.AdminRoleService;
import com.bruce.baseAdmin.service.security.AdminUserService;
import com.bruce.baseAdmin.utils.AdminStatusEnum;
import com.bruce.baseAdmin.utils.ValidatorUtil;


@Controller
@RequestMapping("/sys")
public class AdminUserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private PasswordEncoder pwEncoder;
	//<beans:bean id="pwEncoder" class="org.springframework.security.crypto.password.StandardPasswordEncoder"/>
	
	@RequestMapping("/users")
	public String userList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<AdminUser> adminUserList = adminUserService.queryAll();
		model.addAttribute("adminUserList", adminUserList);
		return "sys/userList";
	}
	
	@RequestMapping("/userAdd")
	public String userAdd(Model model, AdminUser adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		adminUser.setStatus(AdminStatusEnum.OPEN.getStatus());
		model.addAttribute("adminUser", adminUser);
		return "sys/userEdit";
	}
	
	@RequestMapping("/userEdit")
	public String userEdit(Model model,int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		AdminUser adminUser = adminUserService.loadById(id);
		model.addAttribute("adminUser", adminUser);
		return "sys/userEdit";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(Model model, AdminUser adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		String username = adminUser.getUsername();
		if(adminUser==null || StringUtils.isBlank(username)){
			model.addAttribute("message", "用户信息输入有误，请检查！");
			return "forward:/home/operationResult";
		}
		
		//过滤非法字符
		username = ValidatorUtil.filterUnSafeChar(username).trim();
		adminUser.setUsername(username);
		
		Date currentTime = new Date();
		adminUser.setUpdateTime(currentTime);
		if(adminUser!=null&&adminUser.getId()!=null&&adminUser.getId()>0){
			adminUser.setUsername(null);
			adminUser.setNickname(null);
			adminUser.setPassword(null);
			result = adminUserService.updateById(adminUser);
		}else{
			//创建新用户时对密码进行加密
			String password = adminUser.getPassword();
			adminUser.setPassword(pwEncoder.encode(password));
			adminUser.setCreateTime(currentTime);
			result = adminUserService.save(adminUser);
		}
		
		model.addAttribute("redirectUrl", "../sys/users");
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping(value = "/delUser")
	public String delUser(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//删除单个
		adminUserService.deleteById(id);
		model.addAttribute("redirectUrl", "../sys/users");
		return "forward:/home/operationRedirect";
	}
	
	
	@RequestMapping("/userRoleSet")
	public String userRoleSet(Model model,int userId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		//取用户
		AdminUser adminUser = adminUserService.loadById(userId);
		//取所有正常的角色(status=1)
		List<AdminRole> allRoles = adminRoleService.getAvailableRoles();
		//取用户拥有的角色
		List<AdminRole> userRoles = adminRoleService.getRolesByUserId(userId);
		
		model.addAttribute("adminUser", adminUser);
		model.addAttribute("allRoles", allRoles);
		model.addAttribute("userRoles", userRoles);
		
		return "sys/userRoleSet";
	}
	
	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
    public String saveUserRole(Model model, Integer userId, Integer[] roleIds, HttpServletRequest request) {
        String servletPath = request.getRequestURI();
        model.addAttribute("servletPath", servletPath);
        
        int result = 0;
        if(userId!=null && userId>0){
        	adminUserService.deleteRolesByUserId(userId);
        	if(roleIds!=null && roleIds.length>0){
        		List<Integer> roleIdList = Arrays.asList(roleIds);
        		result = adminUserService.saveUserRoles(userId, roleIdList);
        	}
        }
        
        model.addAttribute("redirectUrl", "../sys/users");
        return "forward:/home/operationRedirect";
    }
	
//	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
//	public String saveUserRole(Model model, Integer userId, List<Integer> roleIds, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		adminUserService.deleteRolesByUserId(userId);
//		int result = adminUserService.saveUserRoles(userId, roleIds);
//		
//		model.addAttribute("redirectUrl", "../sys/users");
//		return "forward:/home/operationRedirect";
//	}
	
//	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
//	public String saveUserRole(Model model, UserInfo userInfo, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		boolean resultStatus = true;
//		int userId = userInfo.getId();
//		if(userId<=0){
//			resultStatus = false;
//			model.addAttribute("message", "没有指定用户");
//			return "forward:/home/operationResult";
//		}
//		
//		resultStatus = adminUserService.saveUserRole(userInfo);
//		
//		model.addAttribute("redirectUrl", "../sys/users");
//		return "forward:/home/operationRedirect";
//	}
	
}
