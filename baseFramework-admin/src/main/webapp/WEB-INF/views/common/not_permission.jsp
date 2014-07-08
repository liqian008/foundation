<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/include_tag.jsp" %>
<%@ include file="../common/page_var.jsp" %>

<%
    String message = (String) request.getAttribute("message");
%>

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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.orderBars.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.pie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/flot/excanvas.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.resize.js"></script>

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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/charts/chart.js"></script>

</head>

<body>

<jsp:include page="./inc/nav_top.jsp" />


<!-- Content wrapper -->
<div class="wrapper">
	
	<jsp:include page="./inc/nav_left_menu.jsp" />
    
    <!-- Content -->
    <div class="content">
    	<div class="title"><h5>权限受限</h5></div>
       
        <div class="pt20">
            <div class="nNote nSuccess">
                <p><strong>对不起，您没有访问该内容权限，请尝试联系管理员。</strong></p>
            </div>  
        </div>
    </div>
</div>

<jsp:include page="./inc/nav_bottom.jsp" />

</body>
</html>
