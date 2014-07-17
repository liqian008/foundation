/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.utils;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.DelegatingMessageSource;

import com.bruce.foundation.macp.api.entity.ErrorCode;
import com.bruce.foundation.macp.constants.McpConstants;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 构造Response对象
 * 
 * @author liqian
 * 
 */
public final class ResponseBuilderUtil {
	
	private static final Log logger = LogFactory.getLog(ResponseBuilderUtil.class);

	public final static int RESULT_SUCCESS = 0X01;

	public final static int RESULT_FAILED = 0X00;

	private ResponseBuilderUtil() {
		super();
	}

	public static ApiResult buildSuccessResult() {
		return buildSuccessResult(null);
	}

	/**
	 * 构造成功的响应对象
	 * 
	 * @param data
	 * @return
	 */
	public static ApiResult buildSuccessResult(Object data) {
		ApiResult responseResult = new ApiResult();
		responseResult.setResult(RESULT_SUCCESS);
		if (data != null) {
			responseResult.setData(data);
		}
		return responseResult;
	}

	/**
	 * 构造失败的响应对象，默认错误码
	 * 
	 * @return
	 */
	public static ApiResult buildErrorResult() {
		ApiResult responseResult = new ApiResult();
		responseResult.setResult(RESULT_FAILED);
		responseResult.setErrorcode(ErrorCode.E_SYS_UNKNOWN);
		return responseResult;
	}

	public static ApiResult buildErrorResult(int errorCode) {
		ApiResult responseResult = new ApiResult();
		responseResult.setResult(RESULT_FAILED);
		responseResult.setErrorcode(errorCode);
		return responseResult;
	}

	public static ApiResult buildErrorResult(int errorCode, String errorMsg) {
		ApiResult responseResult = new ApiResult();
		responseResult.setResult(RESULT_FAILED);
		responseResult.setErrorcode(errorCode);
		responseResult.setMessage(errorMsg);
		return responseResult;
	}

	/**
	 * 将erorcode转为String
	 * 
	 * @param apiResult
	 * @param messageSource
	 * @param language
	 */
	public static void fillMessage(ApiResult apiResult,
			DelegatingMessageSource messageSource, String language) {

		try {
			if (messageSource == null || StringUtils.isEmpty(language)
					|| language.length() < 3 || !language.contains("_")) {
				return;
			}
			String[] tmp = language.split("_");

			String msg = messageSource.getMessage(
					McpConstants.API_RESULT_MESSAGE_PREFIX
							+ apiResult.getErrorcode(), null, new Locale(
							tmp[0], tmp[1]));
			apiResult.setMessage(msg);
		} catch (Exception e) {
			logger.error("fillMessage", e);
		}

		return;
	}

}
