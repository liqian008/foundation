/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.command.account;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;

/**
 * @author liqian
 * 
 */
public class WeiboLoginCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(WeiboLoginCommand.class);
    
    private PassportService passportService;

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.notNull(passportService, "commandLookupService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, String> paramMap = context.getStringParams();
    	String wbUid = paramMap.get("wbUid");
    	String wbAccessToken = paramMap.get("wbAccessToken");
    	if(!StringUtils.isBlank(wbUid)&&!StringUtils.isBlank(wbAccessToken)){
    		//校验
    		boolean authenciated= true;
    		if(authenciated){
    			UserPassport userPassport = new UserPassport();
    	        userPassport.setUserId(1);
    	        userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
    	        String ticket = passportService.createTicket(userPassport);
    	        userPassport.setTicket(ticket);
    	        return ResponseBuilderUtil.buildSuccessResult(userPassport);
    		}
    	}
        return ResponseBuilderUtil.buildErrorResult();
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}
    
}
