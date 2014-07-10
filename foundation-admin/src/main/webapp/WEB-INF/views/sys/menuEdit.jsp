<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/include_tag.jsp" %>
<%@ include file="../common/page_var.jsp" %>

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

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/tables/jquery.dataTables.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/tables/colResizable.min.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/forms.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.autosize.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/autotab.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.validationEngine-en.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.validationEngine.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.dualListBox.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.select2.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.maskedinput.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.inputlimiter.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.tagsinput.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/forms/jquery.wysiwyg.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/globalize/globalize.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/globalize/globalize.culture.de-DE.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/globalize/globalize.culture.ja-JP.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/other/calendar.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/other/elfinder.min.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/uploader/plupload.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/uploader/plupload.html5.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/uploader/plupload.html4.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/uploader/jquery.plupload.queue.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.progress.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.jgrowl.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.tipsy.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.alerts.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.colorpicker.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.mousewheel.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/wizards/jquery.form.wizard.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/wizards/jquery.validate.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.breadcrumbs.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.collapsible.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.ToTop.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.listnav.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.sourcerer.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.timeentry.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/plugins/ui/jquery.prettyPhoto.js"></script>

<script type="text/javascript"  src="${pageContext.request.contextPath}/js/custom.js"></script>

</head>

<body>

<jsp:include page="../common/inc/nav_top.jsp" />

<!-- Main wrapper -->
<div class="wrapper">

	<jsp:include page="../common/inc/nav_left_menu.jsp" />
   

	<!-- Content -->
    <div class="content" id="container">
    	<div class="title"><h5>菜单管理</h5></div>
        
        
        <!-- Form begins -->
		<form id="form1" action="<s:url value='./saveMenu'/>" method="post" class="mainForm">
		      
        	<!-- Input text fields -->
            <fieldset>
					<div class="widget first">
						<div class="head">
							<h5 class="iList">编辑资源</h5>
						</div>
						<div class="rowElem noborder">
							<label>父菜单:</label> 
							<div class="formRight">
							<form:select path="menuEntity.parentId"  items="${parentMenus}"  itemValue="id"  itemLabel="menuName" class="styled"></form:select>
							</div>
						</div>
						<div class="rowElem noborder">
							<label>菜单名称:</label>
							<div class="formRight">
								<input name="menuName" type="text" value="${menuEntity.menuName }" maxlength="11"/>
								<form:hidden path="menuEntity.id" />
							</div>
						</div>
						<div class="rowElem noborder">
							<label>菜单Code:</label>
							<div class="formRight">
								<form:input path="menuEntity.menuCode"  cssClass="input rounded" size="25" />
							</div>
						</div>
						
						<div class="rowElem noborder">
							<label>菜单Url:</label>
							<div class="formRight">
								<form:input path="menuEntity.menuUrl"/>
							</div>
						</div>
						
						<div class="rowElem noborder">
							<label>在导航菜单中显示：</label>
							<div class="formRight">
								<form:radiobutton path="menuEntity.navMenu" value="1"/><label for="radio1">显示</label>
								<form:radiobutton path="menuEntity.navMenu" value="0"/><label for="radio1">不显示</label>
							</div>
						</div>
						
						<div class="rowElem noborder">
							<label>排序数值:</label>
							<div class="formRight">
								<form:input path="menuEntity.sort"/>
							</div>
						</div>
						
						<div class="submitForm">
							<input type="submit" value="保 存" class="greyishBtn" />
						</div>

					</div>
				</fieldset>
       </form>     
    </div>
</div>

<jsp:include page="../common/inc/nav_bottom.jsp" />

</body>
</html>
