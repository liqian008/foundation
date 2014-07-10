package com.bruce.baseAdmin.controller.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.baseAdmin.controller.BaseController;
import com.bruce.baseAdmin.model.security.AdminResource;
import com.bruce.baseAdmin.service.security.AdminResourceService;
import com.bruce.baseAdmin.utils.ValidatorUtil;


@Controller
@RequestMapping("/sys")
public class AdminResourceController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AdminResourceController.class);
	
	@Autowired
	private AdminResourceService adminResourceService;
	
	@RequestMapping("/resources")
	public String resourceList(Model model, String resourceName, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<AdminResource> adminResourceList = adminResourceService.queryAll();
		model.addAttribute("adminResourceList", adminResourceList);
		return "sys/resourceList";
	}
	
	@RequestMapping("/resourceAdd")
	public String resourceAdd(Model model,AdminResource adminResource, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<AdminResource> parentResources = adminResourceService.getChildResources(0);
		AdminResource rootResource = new AdminResource();
		rootResource.setId(0);
		rootResource.setResourceName("--顶级菜单--");
		parentResources.add(0, rootResource);
		
		model.addAttribute("adminResource", adminResource);
		model.addAttribute("parentResources", parentResources);
		
		return "sys/resourceEdit";
	}
	
	@RequestMapping("/resourceEdit")
	public String resourceEdit(Model model,int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		AdminResource adminResource = adminResourceService.loadById(id);
		
		List<AdminResource> parentResources = adminResourceService.getChildResources(0);
		AdminResource rootResource = new AdminResource();
		rootResource.setId(0);
		rootResource.setResourceName("--顶级菜单--");
		parentResources.add(0, rootResource);
		
		model.addAttribute("adminResource", adminResource);
		model.addAttribute("parentResources", parentResources);
		
		return "sys/resourceEdit";
	}
	
	@RequestMapping(value = "/saveResource", method = RequestMethod.POST)
	public String saveResource(Model model,AdminResource adminResource, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
//		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String resourceName = adminResource.getResourceName();
		if(adminResource==null || StringUtils.isBlank(resourceName)){
			model.addAttribute("message", "角色信息输入有误，请检查！");
			return "forward:/home/operationResult";
		}
		
		//过滤非法字符
		resourceName = ValidatorUtil.filterUnSafeChar(resourceName).trim();
		adminResource.setResourceName(resourceName);
		
		Date currentTime = new Date();
		adminResource.setUpdateTime(currentTime);
		if(adminResource!=null&&adminResource.getId()!=null&&adminResource.getId()>0){
			result = adminResourceService.updateById(adminResource);
		}else{
			adminResource.setCreateTime(currentTime);
			result = adminResourceService.save(adminResource);
		}
		model.addAttribute("redirectUrl", "../sys/resources");
		
		//刷新菜单资源
		//adminResourceService.reloadResourcesForUser(request);
		//获取导航栏数据
        List<AdminResource> navResourceList = adminResourceService.getNavResources();
        request.getSession().setAttribute("navResourceList", navResourceList);
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping(value = "/delResource")
    public String delResource(Model model, int id, HttpServletRequest request) {
        String servletPath = request.getRequestURI();
        model.addAttribute("servletPath", servletPath);
        //删除单个
        int result = adminResourceService.deleteById(id);
        model.addAttribute("redirectUrl", "../sys/resources");
        //刷新菜单资源
        //adminResourceService.reloadResourcesForUser(request);
        return "forward:/home/operationRedirect";
    }
	
}
