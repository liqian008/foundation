/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.command.test;

import java.util.HashMap;
import java.util.Map;

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
public class TestCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(TestCommand.class);
    
    private PassportService passportService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //Assert.notNull(commandLookupService, "commandLookupService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, String> rt = new HashMap<String, String>();
    	int userId = context.getUserId();
    	if(userId<=0){
	        UserPassport userPassport = new UserPassport();
	        userPassport.setUserId(1);
	        userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
	        String ticket = passportService.createTicket(userPassport);
	        
	        int cacheUserId = passportService.getUserIdByTicket(ticket);
	        rt.put("cacheUserId", String.valueOf(cacheUserId));
	        rt.put("ticket", ticket);
    	}
        
    	String testParamA = context.getStringParams().get("testParamA");
        String testParamB = context.getStringParams().get("testParamB");
        String testParamC = context.getStringParams().get("testParamC");
       
        rt.put("title", "Hello World!");
        rt.put("testParamA", testParamA);
        rt.put("testParamB", testParamB);
        rt.put("testParamC", testParamC);
//        return new ApiResult(rt);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

    public static void main(String[] s) {
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}
    
}
