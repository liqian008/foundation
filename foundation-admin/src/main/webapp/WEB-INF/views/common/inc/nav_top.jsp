<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!-- Top navigation bar -->
<div id="topNav">
    <div class="fixed">
        <div class="wrapper">
            <div class="welcome"><a href="#" title=""><img src="${pageContext.request.contextPath}/images/userPic.png" alt="" /></a><span>Hello, <sec:authentication property="principal.username"/>!</span></div>
            <div class="userNav">
                <ul>
                    <li><a href="#" title=""><img src="${pageContext.request.contextPath}/images/icons/topnav/profile.png" alt="" /><span>Profile</span></a></li>
                    <!-- 
                    <li><a href="#" title=""><img src="${pageContext.request.contextPath}/images/icons/topnav/tasks.png" alt="" /><span>Tasks</span></a></li>
                    
                    <li class="dd"><a title=""><img src="${pageContext.request.contextPath}/images/icons/topnav/messages.png" alt="" /><span>Messages</span><strong class="numberTop">8</strong></a>
                        <ul class="menu_body">
                            <li><a href="#" title="" class="sAdd">new message</a></li>
                            <li><a href="#" title="" class="sInbox">inbox</a></li>
                            <li><a href="#" title="" class="sOutbox">outbox</a></li>
                            <li><a href="#" title="" class="sTrash">trash</a></li>
                        </ul>
                    </li>
                    
                    <li><a href="#" title=""><img src="${pageContext.request.contextPath}/images/icons/topnav/settings.png" alt="" /><span>Settings</span></a></li>
                     -->
                    <li><a href="<s:url value='/u/logout'/>" title=""><img src="${pageContext.request.contextPath}/images/icons/topnav/logout.png" alt="" /><span>Logout</span></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- Header -->
<div id="header" class="wrapper">
    <div class="logo"><a href="${pageContext.request.contextPath}/u/index" title=""><img src="${pageContext.request.contextPath}/images/loginLogo.png" alt="" /></a></div>
    <ul class="middleNav">
        <li class="iMes"><a href="#" title=""><span>Support tickets</span></a><span class="numberMiddle">9</span></li>
        <li class="iStat"><a href="#" title=""><span>Statistics</span></a></li>
        <li class="iUser"><a href="#" title=""><span>User list</span></a></li>
        <li class="iOrders"><a href="#" title=""><span>Billing panel</span></a></li>
    </ul>
</div>