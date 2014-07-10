<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.baseAdmin.model.security.AdminResource"%>


<%!public String liActive(String servletPath, String resourceUrl) {
		//System.err.println("====="+servletPath+"===="+resourceUrl);
		//System.err.println(servletPath.contains(resourceUrl));
		if (servletPath != null && servletPath.contains(resourceUrl)) {
			//System.err.println("servletPath: " + servletPath);
			//System.err.println("resourceUrl: " + resourceUrl);
			return " class='active'";
		}
		return "";
	}

	public String isCurrentSubmenu(String servletPath, String resourceUrl) {
		//System.err.println("=====" + servletPath + "====" + resourceUrl);
		//System.err.println(servletPath.contains(resourceUrl));
		if (servletPath != null && servletPath.contains("/baseAdmin" + resourceUrl)) {
		//if (servletPath != null && servletPath.contains("/baseAdmin" + resourceUrl)) {
			return " class='active'";
		}
		return "";
	}%>

<%
	String current = request.getParameter("current");

	//菜单加载，我放在session中，如果你需要考虑session过期的问题，也可以放在一个缓存或静态对象中
	//或者每次都去数据库读取也行，但是不推荐每次去读取
	List<AdminResource> navResourceList = (List<AdminResource>) request
			.getSession().getAttribute("navResourceList");
	if (navResourceList == null) {
		navResourceList = new ArrayList<AdminResource>();
	}
%>


<div class="sidebar collapse">
	<div class="sidebar-content">

		<div class="user-menu dropdown">
			<a href="#"><img src="${pageContext.request.contextPath}/images/demo/users/default_avatar.jpg" 
				alt="">
			<div class="user-info">
					<sec:authentication property="name" /> <span>管理员</span>
				</div></a>
		</div>


		<!-- Main navigation -->
		<ul class="navigation navigation-icons-left"">
			<%
	        	String servletPath = (String)request.getAttribute("servletPath");
            	for(AdminResource resource : navResourceList){
	        %>
		
			<li <%=liActive(servletPath, resource.getUrl())%>>
				<a href="#" class="expand" id='second-level'><span><%=resource.getResourceName()%></span>
					<i class="icon-grid"></i></a>
			<ul>
			<%
            for(AdminResource childResource : resource.getChildResources()){
            %>
				<li><a href="<s:url value='<%=childResource.getUrl()%>'/>" <%=isCurrentSubmenu(servletPath, childResource.getUrl())%>><%=childResource.getResourceName()%></a></li>
			<%}%>
			</ul>
			</li>
			<%}%>
		</ul>
		<!-- /main navigation -->
	</div>
</div>