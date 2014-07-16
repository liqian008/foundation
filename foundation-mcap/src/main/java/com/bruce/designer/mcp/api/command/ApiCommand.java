/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.command;

import com.bruce.designer.mcp.api.entity.ApiCommandContext;
import com.bruce.designer.mcp.api.entity.ApiResult;

/**
 * 命令接口
 * 
 * @author liqian
 */
public interface ApiCommand {

    /**
     * Execute the command and return result
     * 
     * @param context
     * @return
     */
    ApiResult execute(ApiCommandContext context);

}
