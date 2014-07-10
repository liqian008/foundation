package com.bruce.base.enumeration;

public enum StatusEnum {
    
	CLOSED("禁用", (short)0),
    OPEN("启用", (short)1);
    
    private String name;
    private short status;
    
    private StatusEnum(String name, short status){
        this.name = name;
        this.status = status;
    }
    
    public String getName(short status){
        for (StatusEnum e : StatusEnum.values()) {  
            if (e.getStatus() == status) {  
                return e.name;  
            }  
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
    
}
