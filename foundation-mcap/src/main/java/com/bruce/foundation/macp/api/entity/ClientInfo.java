/**
 * $Id: ClientInfo.java 110445 2012-10-24 07:42:23Z aiquan.yuan@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.foundation.macp.api.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author yanzhid Initial Created at 2010-10-8
 */
public class ClientInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String model; // 手机型号

    private String os; // 操作系统

    private String screen; // 屏幕尺寸

    private String font;

    private String ua;

    private int cellId;

    private String version; // 客户端内部版本号

    private int from; // 渠道ID

    private String uniqid; // 设备的唯一标识，一般手机取IMEI，iPhone手机取UDID（当UDID取不到时留空）

    private String sdkkey; // SDK唯一标识。可选，只有利用SDK开发的APP才需填写(微客户端专用字段)

    private String mac; // 设备的MAC地址

    private String romappkey; // 人人ROM系列产品上层应用APP的唯一标识

    private String other;

    private String misc;

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getRomappkey() {
        return romappkey;
    }

    public void setRomappkey(String romappkey) {
        this.romappkey = romappkey;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModel() {
        return StringUtils.defaultString(model);
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return StringUtils.defaultString(os);
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getScreen() {
        return StringUtils.defaultString(screen);
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getFont() {
        return StringUtils.defaultString(font);
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getUa() {
        return StringUtils.defaultString(ua);
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public String getVersion() {
        return StringUtils.defaultString(this.version);
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUniqid() {
        return uniqid;
    }

    public void setUniqid(String uniqid) {
        this.uniqid = uniqid;
    }

    public String getSdkkey() {
        return sdkkey;
    }

    public void setSdkkey(String sdkkey) {
        this.sdkkey = sdkkey;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public int getSdkkeyOs() {
        if (this.sdkkey == null) {
            return -1;
        }
        if (this.sdkkey.length() < 4) {
            return -1;
        }
        String sdkOs = this.sdkkey.substring(3, 4);
        if (sdkOs.matches("\\d{2}")) {
            return Integer.valueOf(sdkOs);
        }
        return -1;
    }

    @Override
    public String toString() {
        return String
                .format("ClientInfo [model=%s, os=%s, screen=%s, font=%s, ua=%s, cellId=%s, version=%s, from=%s, uniqid=%s, sdkkey=%s, mac=%s, romappkey=%s, other=%s]",
                        model, os, screen, font, ua, cellId, version, from, uniqid, sdkkey, mac,
                        romappkey, other);
    }

}
