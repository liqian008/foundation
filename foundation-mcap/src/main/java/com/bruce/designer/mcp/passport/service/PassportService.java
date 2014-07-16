/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.passport.service;

import com.bruce.designer.mcp.passport.entity.UserPassport;

/**
 * @author liqian
 * 
 */
public interface PassportService {

    /**
     * 通过票获得userId
     * 
     * @return
     */
    public int getUserIdByTicket(String ticket);

    /**
     * 生成票
     * 
     * @param userId
     * @param accountOrigin
     * @param expirePeriod
     * @return
     */
    public String createTicket(UserPassport userPassport);

    /**
     * 通过票获得passport
     * 
     * @param ticket
     * @return
     */
    public UserPassport getPassportByTicket(String ticket);

    /**
     * 通过userId换票，同时可以检查此用户在不在线
     * 
     * @param userId
     * @return
     */
    public String getTicketByUserId(int userId);
    
    /**
     * 通过userId获得passport
     * 
     * @param userId
     * @return
     */
    public UserPassport getPassportByUserId(int userId);
    
    /**
     * 销毁票
     * 
     * @param ticket
     * @return
     */
    public boolean destroyTicket(String ticket);
    
    /**
     * 根据userId销毁票
     * 
     * @param ticket
     * @return
     */
    public void destroyTicket(int userId);
    
    /**
     * 通过票获得带有踢人信息的passport
     * 
     * @param ticket
     * @return
     */
//    public KickedUserPassport getKickedPassportByTicket(String ticket);

}
