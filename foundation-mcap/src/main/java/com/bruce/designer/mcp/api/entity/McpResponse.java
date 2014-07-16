/**
 * $Id: McpResponse.java 118779 2012-11-26 14:05:51Z aiquan.yuan@XIAONEI.OPI.COM $
 * Copyright 2009-2012 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.entity;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.DelegatingMessageSource;

import com.bruce.designer.mcp.api.utils.BuildUtils;
import com.bruce.designer.mcp.constants.HttpConstants;
import com.bruce.designer.mcp.utils.McpUtils;

/**
 * 
 * @author liqian
 * 
 */
public class McpResponse {

    private static final Log logger = LogFactory.getLog(McpResponse.class);

    private HttpServletResponse response;

    private String format;

    private boolean requireCompression;

    private DelegatingMessageSource messageSource;

    private String language;

    public McpResponse(HttpServletResponse response, String format, boolean requireCompression,
            String language) {
        super();
        this.response = response;
        this.format = format;
        this.requireCompression = requireCompression;
    }

    public McpResponse(HttpServletResponse response) {
        this(response, HttpConstants.DEFAULT_FORMAT, false, HttpConstants.DEFAULT_LANGUAGE
                .toString());
    }

    /**
     * 输出
     * 
     * @param apiResult
     * @throws IOException
     */
    public void write(ApiResult apiResult) {
        if (apiResult == null) {
            return;
        }

        OutputStream os = null;
        try {
//            boolean isEmptyData = apiResult.getData() == null || "".equals(apiResult.getData());
//            // 填充提示或错误消息 sys级的 对data判空是为了兼容antispam的错误是直接返回错误msg的
//            if (apiResult.getCode() != ApiResultCode.SUCCESS
//                    && isEmptyData) {
//                this.fillMessage(apiResult);
//            }
//            
//            if (!isEmptyData && apiResult.getData() instanceof BaseResult) {
//                
//                // 服务有返回时， 填充提示或错误消息 biz级的
//                ErrorCode bizResultCode = this.getBizResultCode(apiResult);
//                if (bizResultCode != ErrorCode.SystemSuccess) {
//                    apiResult.setCode(BizErrorMapperUtils.getMcpErrorCode(bizResultCode));
//                    this.fillMessage(apiResult);
//                } else {
//                    // 过滤掉服务端的errorcode
//                    this.filterBizResult(apiResult);                    
//                }
//            }
            
            BuildUtils.buildX2ApiResult(apiResult, messageSource, language);

            this.writeHttpHeader();

            os = response.getOutputStream();

            if (requireCompression) {
                os = new GZIPOutputStream(os);
            }
            if (HttpConstants.FORMAT_JSON.equalsIgnoreCase(format)) {
                // json格式
                this.writeJSON(apiResult, os);
            } else {
                // 默认json格式
                this.writeJSON(apiResult, os);
            }

            os.flush();
        } catch (IOException e) {
            logger.error("McpResponse.write", e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error("McpResponse.write", e);
            }
        }
    }
    
    private void writeJSON(ApiResult apiResult, OutputStream os)
            throws UnsupportedEncodingException, IOException {
        //        logger.debug("apiResult----------" + apiResult);

        if (apiResult.getCode() == ApiResultCode.SUCCESS) {
            Object obj = apiResult.getData();
            String rt = BuildUtils.buildJSONResult(obj);
            if (logger.isDebugEnabled()) {
                logger.debug(String
                        .format("cmd result ok:%s", rt));
            }
            byte[] ob = rt == null ? null : rt.getBytes(CharEncoding.UTF_8);
            os.write(ob);
        } else {
            String rt = McpUtils.gson.toJson(apiResult);
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("cmd result err:%s", rt));
            }
            byte[] ob = rt == null ? null : rt.getBytes(CharEncoding.UTF_8);
            os.write(ob);
        }

    }

    /**
     * write http header
     * 
     * @param response
     * @param format
     * @param requireCompression
     */
    private void writeHttpHeader() {

        try {
            response.setStatus(HttpServletResponse.SC_OK);
            if (StringUtils.equalsIgnoreCase(format, HttpConstants.FORMAT_JSON)) {// json
                if (requireCompression) {
                    response.setContentType("application/json-gz");
                    response.setHeader("content-encoding", "gzip");
                } else {
                    response.setContentType("text/plain;charset=UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("writeHttpHeader", e);
        }

    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isRequireCompression() {
        return requireCompression;
    }

    public void setRequireCompression(boolean requireCompression) {
        this.requireCompression = requireCompression;
    }

    public DelegatingMessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(DelegatingMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
