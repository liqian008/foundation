package com.bruce.foundation.model.result;


/**
 * 对响应的包装，ajax请求及mcap共用此数据类型
 * 
 * @author liqian
 * 
 */
public class CommonResultBean{

    private int result;
    
    private int errorcode;

    private String message;

    private Object data;

    public CommonResultBean() {
        super();
    }
    
    public CommonResultBean(int result,  Object data, int errorcode, String message){
        this.result = result;
        this.data = data;
        this.errorcode = errorcode;
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
    
	@Override
    public String toString() {
        return "ApiResult [result=" + result + ",errorcode=" + errorcode + ",message=" + message + ", data=" + data + "]";
    }
}