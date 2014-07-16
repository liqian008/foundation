package com.bruce.foundation.macp.api.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.DelegatingMessageSource;

import com.bruce.foundation.macp.api.entity.ApiResult;
import com.bruce.foundation.macp.api.entity.ApiResultCode;
import com.bruce.foundation.macp.constants.McpConstants;
import com.bruce.foundation.macp.utils.McpUtils;

/**
 * 
 * 
 * @author liqian
 * @version 2012-9-26下午01:32:55
 */
public class BuildUtils {

    private static final Log logger = LogFactory.getLog(BuildUtils.class);

    public static String buildJSONResult(Object result) {

        return McpUtils.gson.toJson(buildObjResult(result));
        
    }
    
    public static Object buildObjResult(Object result) {

        if (result == null) {
            return null;
        }
        
        if (result instanceof List || result instanceof Integer || result instanceof Long
                || result instanceof Boolean || result instanceof String) {

            Map<String, Object> rtMap = new HashMap<String, Object>();
            rtMap.put("result", result);
            return rtMap;
        }
        return result;
    }
    
    public static ApiResult buildX2ApiResult(ApiResult apiResult, DelegatingMessageSource messageSource, String language) {
        if (apiResult == null || messageSource == null || StringUtils.isEmpty(language)) {
            return apiResult;
        }
        boolean isEmptyData = apiResult.getData() == null || "".equals(apiResult.getData());
        // 填充提示或错误消息 sys级的 对data判空是为了兼容antispam的错误是直接返回错误msg的
        if (apiResult.getCode() != ApiResultCode.SUCCESS
                && isEmptyData) {
            fillMessage(apiResult, messageSource, language);
        }
        
//        if (!isEmptyData && apiResult.getData() instanceof BaseResult) {
//            // 服务有返回时， 填充提示或错误消息 biz级的
//            ErrorCode bizResultCode = getBizResultCode(apiResult);
//            if (bizResultCode != ErrorCode.SystemSuccess) {
//                apiResult.setCode(BizErrorMapperUtils.getMcpErrorCode(bizResultCode));
//                fillMessage(apiResult, messageSource, language);
//            } else {
//                // 过滤掉服务端的errorcode
//                filterBizResult(apiResult);                    
//            }
//        }
        return apiResult;
    }
    
    private static void fillMessage(ApiResult apiResult, DelegatingMessageSource messageSource, String language) {

        try {
            if (messageSource == null || StringUtils.isEmpty(language) || language.length() < 3
                    || !language.contains("_")) {
                return;
            }
            String[] tmp = language.split("_");

            String msg = messageSource.getMessage(McpConstants.API_RESULT_MESSAGE_PREFIX
                    + apiResult.getCode(), null, new Locale(tmp[0], tmp[1]));
            apiResult.setData(msg);
        } catch (Exception e) {
            logger.error("fillMessage", e);
        }

        return;
    }
    
//    private static ApiResult filterBizResult(ApiResult apiResult) {
//        BaseResult baseResult = (BaseResult) apiResult.getData();
//        if (BaseResult.class.getName().equals(baseResult.getClass().getName())) {
//            apiResult.setData(new Integer(0));
//        } else {
//            BizErrorMapperUtils.filterBizResult(baseResult);
//        }
//        return apiResult;
//    }
//    
//    private static ErrorCode getBizResultCode(ApiResult apiResult) {
//        BaseResult baseResult = (BaseResult) apiResult.getData();
//        return baseResult.getCode();
//    }
}
