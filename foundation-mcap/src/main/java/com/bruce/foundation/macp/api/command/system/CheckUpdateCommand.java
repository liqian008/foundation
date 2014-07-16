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
import com.bruce.foundation.macp.api.entity.ApiResult;
import com.bruce.foundation.macp.api.entity.ErrorCode;

/**
 * @author liqian
 * 
 */
public class CheckUpdateCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(CheckUpdateCommand.class);

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	//检查版本更新
        String testParamA = context.getStringParams().get("testParamA");
        String testParamB = context.getStringParams().get("testParamB");
        String testParamC = context.getStringParams().get("testParamC");
        Map<String, String> rt = new HashMap<String, String>();
        rt.put("title", "Hello World!");
        rt.put("testParamA", testParamA);
        rt.put("testParamB", testParamB);
        rt.put("testParamC", testParamC);
        return new ApiResult(ErrorCode.SUCCESS, rt);
    }

    public static void main(String[] s) {
    }
}
