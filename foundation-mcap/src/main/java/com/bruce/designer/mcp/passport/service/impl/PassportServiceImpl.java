/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.mcp.passport.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.bruce.designer.mcp.passport.entity.UserPassport;
import com.bruce.designer.mcp.passport.service.PassportService;
import com.bruce.designer.mcp.passport.utils.TicketUtils;

/**
 * @author liqian
 * 
 */
public class PassportServiceImpl implements PassportService, InitializingBean {

    private static final Log logger = LogFactory.getLog(PassportServiceImpl.class);

    // TODO: passport cache
//    private ICacheService t2PassportCache;
    private Map<String, Object> passportCache = new HashMap<String, Object>();

    /**
     * 销毁tickt
     */
    @Override
    public boolean destroyTicket(String ticket) {
        boolean rt = false;
        if (StringUtils.isEmpty(ticket)) {
            return rt;
        }
        UserPassport userPassport = getPassportByTicket(ticket);
        if (userPassport != null) {
            String mix = TicketUtils.decryptTicket(ticket);
            int uid = TicketUtils.getUserIdFromMix(mix);
            if (uid <= 0) {
                return rt;
            }
            try {
//                t2PassportCache.del(uid + "");
                passportCache.remove(uid + "");
                rt = true;
            } catch (Exception e) {
                logger.error("[" + this.getClass().getName() + "]", e);
                rt = false;
            }

        }
        return rt;
    }
    
    /**
     * 销毁ticket
     */
    @Override
    public void destroyTicket(int userId) {
        try {
            //                t2PassportCache.del(uid + "");
            passportCache.remove(userId + "");
        } catch (Exception e) {
            logger.error("[" + this.getClass().getName() + "]", e);
        }

        return;
    }

    /**
     * 根据ticket获取UserPassport
     */
    @Override
    public UserPassport getPassportByTicket(String ticket) {
        UserPassport userPassport = null;
        if (StringUtils.isEmpty(ticket)) {
            return userPassport;
        }
        String mix = TicketUtils.decryptTicket(ticket);
        int uid = TicketUtils.getUserIdFromMix(mix);
        if (uid <= 0) {
            return userPassport;
        }
        try {
//            userPassport = t2PassportCache.get(uid + "", UserPassport.class);
            userPassport = (UserPassport) passportCache.get(uid + "");
        } catch (Exception e) {
            logger.error("[" + this.getClass().getName() + "]", e);
        }
        return userPassport;
    }
    

    /**
     * 根据ticket换取userid
     */
    @Override
    public int getUserIdByTicket(String ticket) {
        int userId = 0;
        UserPassport userPassport = getPassportByTicket(ticket);
        if (userPassport == null) {
            return userId;
        }
        userId = userPassport.getUserId();
        return userId;
    }

    /**
     * 根据userPassport对象生成ticket
     */
    @Override
    public String createTicket(UserPassport userPassport) {
        String ticket = null;
        if (userPassport == null || userPassport.getUserId() == 0 || StringUtils.isEmpty(userPassport.getIdentity())) {
            return ticket;
        }
        ticket = TicketUtils.generateTicket(userPassport.getUserId(), userPassport.getIdentity());
        userPassport.setTicket(ticket);
        if (!StringUtils.isEmpty(ticket)) {
            try {
//                t2PassportCache
//                        .set(userPassport.getUserId() + "", userPassport, UserPassport.class);
                passportCache.put(userPassport.getUserId() + "", userPassport);
            } catch (Exception e) {
                logger.error("[" + this.getClass().getName() + "]", e);
                return null;
            }
        }
        return ticket;
    }

    @Override
    public String getTicketByUserId(int userId) {
        String ticket = null;

        if (userId != 0) {
            UserPassport userPassport = this.getPassportByUserId(userId);
            if (userPassport != null) {
                ticket = userPassport.getTicket();
                String mix = TicketUtils.decryptTicket(ticket);
                int uid = TicketUtils.getUserIdFromMix(mix);
                if (uid <= 0) {
                    return null;
                }
                if (uid != userId) {
                    return null;
                }
            }
        }
        return ticket;
    }
    
    @Override
    public UserPassport getPassportByUserId(int userId) {
        UserPassport userPassport = null;

        if (userId != 0) {
            try {
//                userPassport = t2PassportCache.get(userId + "", UserPassport.class);
                userPassport = (UserPassport) passportCache.get(userId + "");  
            } catch (Exception e) {
                logger.error("[" + this.getClass().getName() + "]", e);
            }
        }
        return userPassport;
    }

//    public void setT2PassportCache(ICacheService t2PassportCache) {
//        this.t2PassportCache = t2PassportCache;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.notNull(t2PassportCache, "t2PassportCache is required!");
    }

}
