<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="./common/include_tag.jsp" %>
<%@ include file="./common/page_var.jsp" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>名堂客栈后台管理系统</title>
 
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<link href='http://fonts.googleapis.com/css?family=Cuprum' rel='stylesheet' type='text/css' />

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/tables/jquery.dataTables.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/tables/colResizable.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/forms.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.autosize.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/autotab.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.dualListBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.select2.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.inputlimiter.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/jquery.wysiwyg.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/other/calendar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/other/elfinder.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/uploader/plupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/uploader/plupload.html5.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/uploader/plupload.html4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/uploader/jquery.plupload.queue.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.progress.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.tipsy.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.colorpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.mousewheel.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/wizards/jquery.form.wizard.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/wizards/jquery.validate.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.breadcrumbs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.collapsible.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.ToTop.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.listnav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.sourcerer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.timeentry.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/ui/jquery.prettyPhoto.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom.js"></script>

</head>

<body>

<!-- Top navigation bar -->
<div id="topNav">
    <div class="fixed">
        <div class="wrapper">
            <div class="backTo"><a href="#" title=""><img src="images/icons/topnav/mainWebsite.png" alt="" /><span>Main website</span></a></div>

            <div class="userNav">
                <ul>
                	<!--  
                    <li><a href="#" title=""><img src="images/icons/topnav/register.png" alt="" /><span>Register</span></a></li>
                    <li><a href="#" title=""><img src="images/icons/topnav/contactAdmin.png" alt="" /><span>Contact admin</span></a></li>
                    -->
                    <li><a href="#" title=""><img src="images/icons/topnav/help.png" alt="" /><span>Help</span></a></li>
                </ul>
            </div>

        </div>
    </div>
</div>

<!-- Login form area -->

<div class="loginWrapper">
    <div class="loginLogo"><img src="images/loginLogo.png" alt="" /></div>
    <div class="loginPanel">
        <div class="head"><h5 class="iUser">登 录</h5></div>
        <form id="valid" class="mainForm" action="<s:url value='/doLogin'/>" method="post">
            <fieldset>
                <div class="loginRow noborder">
                    <label for="req1">用户名:</label>
                    <div class="loginInput"><input type="text" name="userName" value="admin" class="validate[required]" id="userName" /></div>
                </div>
                
                <div class="loginRow">
                    <label for="req2">密码:</label>
                    <div class="loginInput"><input type="password" name="passWord" value="admin" class="validate[required]" id="passWord" /></div>
                </div>
                  <div class="loginRow">
                    <label for="req2">验证码:</label>
                    <div class="loginInput">
                    	<input type="text" name="verifyCode" class="validate[required]" id="verifyCode"  size="5" maxlength="5"/>
						<img id="verifyImage"  src="<s:url value='/getVerifyCode'/>"  align="middle" style="cursor:pointer;"  onclick="reloadVerifyCode();" title="Change" alt="Change" />
                    </div>
                </div>
                
                <div class="loginRow">
                    <div class="rememberMe"><input type="checkbox" id="check2" name="chbox" /><label for="check2">记住我</label></div>

                    <div class="submitForm"><input type="submit" value="登 录" class="redBtn" /></div>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<%@ include file="./common/inc/nav_bottom.jsp" %>

</body>
</html>
