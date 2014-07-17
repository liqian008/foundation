/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.command;

import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.model.result.ApiResult;

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
