package com.bruce.baseAdmin.utils;

public enum AdminStatusEnum {
    
    OPEN("开启", (short)1),
    CLOSED("关闭", (short)0);
    
    private String name;  
    private short status;  
    
    private AdminStatusEnum(String name, short status){
        this.name = name;
        this.status = status;
    }
    
    public String getName(short status){
        for (AdminStatusEnum e : AdminStatusEnum.values()) {  
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
