/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.command.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.entity.VersionCheckResult;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;

/**
 * @author liqian
 * 
 */
public class SystemCheckCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(SystemCheckCommand.class);
    
    private PassportService passportService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //Assert.notNull(commandLookupService, "commandLookupService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	String versionName = context.getStringParams().get("v_name");
    	String versionCode = context.getStringParams().get("v_code");
    	VersionCheckResult versionCheckResult = new VersionCheckResult(0, "测试标题", "测试内容", "http://softfile.3g.qq.com:8080/msoft/179/24659/43549/qq_hd_mini_1.4.apk");
    	rt.put("versionCheckResult", versionCheckResult);

    	boolean needLogin = true;
    	int userId = context.getUserId();
    	if(userId>0){
    		needLogin = false;
    	}
    	rt.put("needLogin", needLogin);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}
    
}
