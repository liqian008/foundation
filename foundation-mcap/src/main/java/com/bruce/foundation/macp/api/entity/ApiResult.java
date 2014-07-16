/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.entity;

import java.io.Serializable;

/**
 * @author liqian
 * 
 */
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private Object data;

    public ApiResult(int resultCode, Object data) {
        super();
        this.code = resultCode;
        this.data = data;
    }

    public ApiResult(int resultCode) {
        super();
        this.code = resultCode;
        this.data = "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult [code=" + code + ", data=" + data + "]";
    }
}
