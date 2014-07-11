package com.bruce.foundation.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.foundation.admin.model.security.AdminResource;
import com.bruce.foundation.admin.model.security.AdminUser;
import com.bruce.foundation.admin.security.WebUserDetails;
import com.bruce.foundation.admin.service.security.AdminResourceService;
import com.bruce.foundation.admin.service.security.AdminUserService;
import com.bruce.foundation.admin.utils.ValidatorUtil;


@Controller
@RequestMapping(value="/home")
public class HomeController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminResourceService adminResourceService;
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@RequestMapping(value = {"/", "/index", "/welcome"})
    public String index(Model model,HttpServletRequest request,HttpServletResponse response){
        
//      adminResourceService.reloadResourcesForUser(request);
        
		//获取导航栏数据
        List<AdminResource> navResourceList = adminResourceService.queryNavResources();
        request.getSession().setAttribute("navResourceList", navResourceList);
        
        
        String userIp = ValidatorUtil.getIpAddr(request);
        model.addAttribute("userIp", userIp);
        
        String servletPath = request.getRequestURI();
        model.addAttribute("servletPath", servletPath);
        return "home/index";
    }
    
    @RequestMapping("/operationRedirect")
    protected String operationRedirect() {
        return "home/operationRedirect";
    }

    @RequestMapping("/operationResult")
    protected String operationResult() {
        return "home/operationResult";
    }
	
	@RequestMapping("/profile")
	public String profile(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WebUserDetails userDetail = getUserInfo();
		int userId = userDetail.getUserId();
		
		AdminUser adminUser = adminUserService.loadById(userId);
		model.addAttribute("adminUser", adminUser);
		return "home/profile";
	}
	
	@RequestMapping("/passwd")
	public String passwd(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WebUserDetails userInfo = this.getUserInfo();
		int userId = userInfo.getUserId();
		
		AdminUser adminUser = adminUserService.loadById(userId);
		model.addAttribute("adminUser", adminUser);
		return "home/passwd";
	}
	
	@RequestMapping(value="/changePasswd", method=RequestMethod.POST)
	public String changePasswd(Model model, HttpServletRequest request, String newPassword, String rePassword) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//检查newPassword和rePassword
		if(StringUtils.isBlank(newPassword)||StringUtils.isBlank(rePassword)){
			//密码不能为空
			request.setAttribute("message", "密码均不能为空");
			return "forward:/home/operationResult";
		}else if(StringUtils.isBlank(newPassword)||StringUtils.isBlank(rePassword)){
			//新密码与确认密码必须一致
			request.setAttribute("message", "新密码与确认密码必须一致");
			return "forward:/home/operationResult";
		}
		
		WebUserDetails userInfo = this.getUserInfo();
		int userId = userInfo.getUserId();
		
		AdminUser adminUser = adminUserService.loadById(userId);
		
		if(adminUser!=null){
			int result = adminUserService.changeUserPassword(userId, pwEncoder.encode(newPassword));
			if(result>0){
				model.addAttribute("redirectUrl", "./index");
				return "forward:/home/operationRedirect";
			}
		}
		request.setAttribute("message", "修改密码失败");
		return "forward:/home/operationResult";
	}
	
	
//	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
//	public String updateProfile(Model model, AdminUser adminUser, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		int result = 0;
////		Map<String, Object> resMap = new HashMap<String, Object>();
//		
//		String userName = adminUser.getUsername();
//		if(adminUser==null || StringUtils.isBlank(userName)){
//			model.addAttribute("message", "用户信息输入有误，请检查！");
//			return "forward:/operationResult";
//		}
//		
//		//过滤非法字符
//		userName = ValidatorUtil.filterUnSafeChar(userName).trim();
//		adminUser.setUsername(userName);
//		
//		WebUserDetails userinfo = getUserInfo();
//		int userId = userinfo.getUserId();
//		adminUser.setId(userId);
//		
//		result = adminUserService.save(adminUser);
//		
//		model.addAttribute("redirectUrl", "./myProfile");
//		return "forward:/operationRedirect";
//	}
	
}
