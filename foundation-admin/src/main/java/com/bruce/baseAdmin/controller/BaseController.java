package com.bruce.baseAdmin.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.bruce.baseAdmin.security.WebUserDetails;

public abstract class BaseController {
	
	protected WebUserDetails getUserInfo(){
		WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return webUserDetails;
	}
	
}
