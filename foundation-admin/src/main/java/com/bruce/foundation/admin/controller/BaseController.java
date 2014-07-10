package com.bruce.foundation.admin.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.bruce.foundation.admin.security.WebUserDetails;

public abstract class BaseController {
	
	protected WebUserDetails getUserInfo(){
		WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return webUserDetails;
	}
	
}
