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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

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
	location.href="./userAdd";
}

function edit(id){
	location.href="./userEdit?id="+id;
}
/* 
function changeUserStatus(id,status){
	$.jBox.confirm("确定 "+(status==0?"禁用":"启用")+" 用户吗？", "确认操作", function (v, h, f) {
	    if (v == 'ok'){
	    	
	    	$.post("<s:url value='/u/changeUserStatus'/>",{id:id,status:status},function(responseText){
	    		if(responseText==true){
	    			$.jBox.info("操作成功，请刷新查看结果", "成功信息",{top: '20%'});
	    			//window.location.reload();
	    		}else{
	    			$.jBox.error("操作失败", "失败信息");
	    		}
	    	});
		}
	    return true; 
	},{top: '40%'});
} */

function delUser(id,uname){
	if(confirm("确定删除 ["+uname+"] 用户吗？")){
		location.href="./delUser?id="+id;
	}
}

function userRoleSet(userId){
	location.href="./userRoleSet?userId="+userId;
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
    	<div class="title"><h5>用户管理</h5></div>
        
        <!-- 
        <jsp:include page="../common/inc/nav_shortcut.jsp" />
        -->


		<form id="usualValidate" class="mainForm" method="post" action="./users">
			<fieldset>
                <div class="widget first">
                    <div class="head"><h5 class="iLocked">用户搜索</h5></div>
		            <table cellpadding="0" cellspacing="0" width="100%" class="searchForm">
		                <tbody>
		                	<tr>
		                        <td width="20%">用户名</td>
		                        <td><input type="text" class="required" name="userName" id="firstname"/></td>
		                        <td width="20%"></td>
		                        <td></td> 
		                    </tr>
		                	<tr>
		                        <td width="20%">开始日期</td>
		                        <td>
                           			<input type="text" class="datepicker" name="startDate"/>
		                        </td>
		                        <td width="20%">结束日期</td>
		                        <td>
		                        	<input type="text" class="datepicker"  name="endDate"/>
								</td>
		                    </tr>
		                    <tr>
		                        <td colspan="4" align="center">
		                        	<input type="submit" value="查询" class="redBtn"/>
		                        	<input type="reset" value="重置" class="greyishBtn"/>
		                        </td>
		                    </tr>
		                </tbody>
		            </table>
                </div>
            </fieldset>
		

		<!-- Dynamic table -->
        <div class="widget">
            <div class="head">
            	<h5 class="iFrames">用户列表</h5>
	            <div class="num"><a href="javascript:;" onclick="add();" class="redNum">&nbsp;新增用户&nbsp;</a></div>
            </div>
            <table cellpadding="0" cellspacing="0" border="0" class="tableStatic"  width="100%">
                <thead>
                     <tr>
                        <td>ID</td>
                        <td>用户名称</td>
                        <td>用户状态</td>
                        <td>创建时间</td>
                        <td>最后更新时间</td>
                        <td>操作</td>
                    </tr>
                </thead>
                
                <tbody>
                	 <c:if test="${totalCount==0}">
                    <tr>
                        <td colspan="6">没有相关数据</td>
                    </tr>
                    </c:if>
                	
                	<c:forEach items="${dataList}"  var="dataItem" varStatus="rowStatus" >
                   	 <tr>
						<td align="center">
							${dataItem.id}
						</td>
						<td align="center">${dataItem.userName}</td>
						<td align="center">
						<c:choose>
							<c:when test="${dataItem.status==0}">
								<font color='red'>禁用</font>
							</c:when>
							<c:when test="${dataItem.status==1}">
								<font color='green'>启用</font>
							</c:when>	
							<c:otherwise>
								<font color='gray'>未知:${dataItem.status}</font>
							</c:otherwise>
						</c:choose>
												
						</td>
						<td align="center" title='<fmt:formatDate value="${dataItem.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'>	
                           <fmt:formatDate value="${dataItem.createTime}" pattern="yyyy-MM-dd"/>
						</td>
						<td align="center" title='<fmt:formatDate value="${dataItem.lastUpdate}" pattern="yyyy-MM-dd HH:mm:ss"/>'>
							<fmt:formatDate value="${dataItem.lastUpdate}" pattern="yyyy-MM-dd"/>
						</td>
						<td align="center">
							<a href="javascript:;" onclick="edit(${dataItem.id});">编辑</a>&nbsp;|
							 <!-- 
							 <c:choose>
								<c:when test="${dataItem.status==0}">
									 <a href="javascript:;" onclick="changeUserStatus(${dataItem.id},1);">启用</a>&nbsp;|
								</c:when>					
								<c:otherwise>
		                            <a href="javascript:;" onclick="changeUserStatus(${dataItem.id},0);">禁用</a>&nbsp;|
								</c:otherwise>
							</c:choose>
							 -->
							 <a href="javascript:;" onclick="userRoleSet(${dataItem.id});">角色</a>&nbsp;|
							 <a href="javascript:;" onclick="delUser(${dataItem.id},'${dataItem.userName}');">删除</a>
						</td>
						
					</tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
        
       
        
         <!-- Pagination -->
		<div class="pagination">
			<ul class="pages">
				${pageNav}
			</ul>
		</div>
		
		 </form>
        
    </div>
</div>

<jsp:include page="../common/inc/nav_bottom.jsp" />


</body>
</html>
