package com.bruce.base.model.json;


/**
 * 对数据的json包装，ajax请求及mcp共用此数据类型
 * 
 * @author liqian
 * 
 */
public class JsonResultBean{

    private int result;
    
    private int errorcode;

    private String message;

    private Object data;

    public JsonResultBean() {
        super();
    }
    
    public JsonResultBean(int result,  Object data, int errorcode, String message){
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
    
}