/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.command.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.bruce.designer.mcp.api.command.AbstractApiCommand;
import com.bruce.designer.mcp.api.entity.ApiCommandContext;
import com.bruce.designer.mcp.api.entity.ApiResult;
import com.bruce.designer.mcp.api.entity.ApiResultCode;

/**
 * @author liqian
 * 
 */
public class TestCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(TestCommand.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        //Assert.notNull(commandLookupService, "commandLookupService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
        String testParamA = context.getStringParams().get("testParamA");
        String testParamB = context.getStringParams().get("testParamB");
        String testParamC = context.getStringParams().get("testParamC");
        Map<String, String> rt = new HashMap<String, String>();
        rt.put("title", "Hello World!");
        rt.put("testParamA", testParamA);
        rt.put("testParamB", testParamB);
        rt.put("testParamC", testParamC);
        return new ApiResult(ApiResultCode.SUCCESS, rt);
    }

    public static void main(String[] s) {
    }
}
