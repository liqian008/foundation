/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.api.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 应用的基本信息
 * 
 * @author liqian
 * 
 */
public class MobileClientAppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 接入方编号 */
    private int appId;

    /** 接入方名称 */
    private String appName;

    // /** 接入方简称 */
    // private String shortName;

    // /** 接入方唯一标识 */
    // private String appKey;

    /** 接入方私钥 */
    private String secretKey;

    /** 接入方的主页 */
    private String appUrl;

    private Date createTime;

    // /** 支持的视频类型 */
    // private int videoType;
    //
    // /** 置顶新鲜事定义的平台类型，实际应用时，已经被当平真正的平台 */
    // private int topfeedPlatform;
    //
    // /** 升级相关选项，暂时用不到 */
    // private int updateStep;
    //
    // /** 上传照片的单相册图片数量限制 */
    // private int photoLimit;
    //
    // /** 16x16 logo图片地址，上传到人人图片系统之后的地址 */
    // private String logoSmall;
    //
    // /** 80x80 logo图片地址，上传到人人图片系统之后的地址 */
    // private String logoLarge;
    //
    // /** APP级别，和开发者级别对应：1，普通；2，高级；3，紧密合作；4，公司内合作 */
    // private int level;

    // /** APP对应的无线应用中心pageid */
    // private int pageId;

    // /** 状态 */
    // private int status;

    // /** 是否开放私有登录 */
    // private int privateLogin;
    //
    // private int isMainClient;
    //
    // private int banTopFeed;
    //
    // /** 测试期 */
    // public static final int STATUS_TEST = 1;
    //
    // /** 上线 */
    // public static final int STATUS_ONLINE = 2;
    //
    // /** 停用 */
    // public static final int STATUS_STOP = 0;

    /** 接入方编号 */
    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    /** 接入方名称 */
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    // /** 接入方唯一标识 */
    // public String getAppKey() {
    // return appKey;
    // }
    //
    // public void setAppKey(String appKey) {
    // this.appKey = appKey;
    // }

    /** 接入方私钥 */
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /** 接入方的主页,已经被current.getFeedDisplayUrl()取代 */
    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MobileClientAppInfo [appId=" + appId + ", appName=" + appName + ", secretKey="
                + secretKey + ", appUrl=" + appUrl + ", createTime=" + createTime + "]";
    }

}
