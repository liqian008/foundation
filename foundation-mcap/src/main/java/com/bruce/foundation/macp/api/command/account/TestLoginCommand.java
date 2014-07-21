/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.command.account;

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
public class TestLoginCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(TestLoginCommand.class);
    
    private PassportService passportService;

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.notNull(passportService, "commandLookupService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		UserPassport userPassport = new UserPassport();
        userPassport.setUserId(100007);
        userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
        String ticket = passportService.createTicket(userPassport);
        userPassport.setTicket(ticket);
//        userPassport.setUserSecretKey(String.valueOf(System.currentTimeMillis()));
        
        paramMap.put("userPassport", userPassport);
        return ResponseBuilderUtil.buildSuccessResult(paramMap);
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}
    
}
