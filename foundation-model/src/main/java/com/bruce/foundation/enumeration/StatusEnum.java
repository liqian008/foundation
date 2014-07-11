package com.bruce.foundation.enumeration;

public enum StatusEnum {
    
	//ALL("全部", (short)-1),
	DISABLE("禁用", (short)0),
    ENABLE("启用", (short)1),
	DELETED("已删除", (short)2);
    
    private String name;
    private short status;
    
    private StatusEnum(String name, short status){
        this.name = name;
        this.status = status;
    }
    
    public static String getName(Short status){
    	if(status!=null){
	        for (StatusEnum e : StatusEnum.values()) {  
	            if (e.getStatus() == status) {  
	                return e.name;  
	            }  
	        }
    	}
        return "状态异常";
    }

    public String getName() {
        return name;
    }

    public short getStatus() {
        return status;
    }
    
    
}
