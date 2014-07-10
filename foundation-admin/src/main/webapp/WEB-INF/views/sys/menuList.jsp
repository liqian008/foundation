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

<script type="text/javascript">
function add(){
	location.href="./menuAdd";
}

function edit(id){
	location.href="./menuEdit?id="+id;
}
function delMenu(id, menuName){
	if(confirm("确定删除 ["+menuName+"] 资源吗？")){
		location.href="./delMenu?id="+id;
	}
}
</script>

</head>

<body>

<jsp:include page="../common/inc/nav_top.jsp" />

<!-- Content wrapper -->
<div class="wrapper">
	
	<jsp:include page="../common/inc/nav_left_menu.jsp" />
    
    <!-- Content -->
    <div class="content">
    	<div class="title"><h5>菜单管理</h5></div>
        
        <jsp:include page="../common/inc/nav_shortcut.jsp" />
       
       
		<!-- Dynamic table -->
        <div class="widget">
            <div class="head">
            <h5 class="iFrames">资源列表</h5>
       		<div class="num"><a href="javascript:;" onclick="add();" class="redNum">&nbsp;新增资源&nbsp;</a></div>
           
       </div>
            <table cellpadding="0" cellspacing="0" border="0" class="tableStatic"  width="100%">
                <thead>
                     <tr>
                        <td>菜单ID</td>
                        <td>菜单名称</td>
                        <td>菜单Code</td>
                        <td>菜单Url</td>
                        <td>菜单显示</td>
                        <td>排序</td>
                        <td>创建时间</td>
                        <td>最后更新时间</td>
                        <td>操作</td>
                    </tr>
                </thead>
                
                <tbody>
                	 <c:if test="${totalCount==0}">
                    <tr>
                        <td colspan="8">没有相关数据</td>
                    </tr>
                    </c:if>
                	
                	<!-- 
                    <tr class="gradeA">
                        <td>KHTML</td>
                        <td>Konqureror 3.3</td>
                        <td>KDE 3.3</td>
                        <td class="center">3.3</td>
                        <td class="center">A</td>
                    </tr>
                    -->
                    
                    <c:forEach items="${dataList}"  var="dataItem" varStatus="rowStatus" >
                   	 <tr class="gradeA">
						<td align="center">${dataItem.id}</td>
						<td align="center">${dataItem.menuName}</td>
						<td align="center">
						${dataItem.menuCode}
						</td>
						<td style="text-align:left;word-wrap:break-word;word-break:break-all;">
						${dataItem.menuUrl}
						</td>
						<td align="center">
						
						<c:choose>
							<c:when test="${dataItem.navMenu==0}">
								<font color='red'>隐藏</font>
							</c:when>
							<c:when test="${dataItem.navMenu==1}">
								<font color='green'>显示</font>
							</c:when>	
							<c:otherwise>
								<font color='gray'>未知:${dataItem.navMenu}</font>
							</c:otherwise>
						</c:choose>
						</td>
						<td align="center">
						${dataItem.sort}
						</td>
						<td align="center" title='<fmt:formatDate value="${dataItem.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>	
                           <fmt:formatDate value="${dataItem.createTime}" pattern="yyyy-MM-dd"/>
						</td>
						<td align="center" title='<fmt:formatDate value="${dataItem.lastUpdate}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
							<fmt:formatDate value="${dataItem.lastUpdate}" pattern="yyyy-MM-dd"/>
						</td>
						<td align="center">
							<a href="javascript:;" onclick="edit(${dataItem.id});">编辑</a>&nbsp;|
							<a href="javascript:;" onclick="delMenu(${dataItem.id},'${dataItem.menuName}');">删除</a>
						</td>
						
					</tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
        
        
    </div>
</div>

<jsp:include page="../common/inc/nav_bottom.jsp" />

</body>
</html>
