<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"  %>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mingtang.hotel.model.MenuInfo"%>

<%
String current = request.getParameter("current");

//菜单加载，我放在session中，如果你需要考虑session过期的问题，也可以放在一个缓存或静态对象中
//或者每次都去数据库读取也行，但是不推荐每次去读取
List<MenuInfo> menus = new ArrayList<MenuInfo>();
Object obj = request.getSession().getAttribute("menus");
if(obj!=null){
	menus = (List<MenuInfo>)request.getSession().getAttribute("menus");
}
%>


<!-- Left navigation -->
    <div class="leftNav">
    	<ul id="menu">
    		<!-- menu simple  -->
    		<!-- 
        	<li class="dash"><a href="#" title="" class="active"><span>Dashboard</span><strong>2</strong></a></li>
            <li class="tables"><a href="tables.html" title=""><span>Tables</span></a></li>
            <li class="errors"><a href="#" title="" class="exp"><span>系统管理</span></a>
            	<ul class="sub">
                    <li><a href="403.html" title="">用户管理</a></li>
                    <li><a href="404.html" title="">角色管理</a></li>
                    <li><a href="405.html" title="">权限管理</a></li>
                </ul>
            </li>
             -->
           
			<%!
			public String isCurrent(String servletPath, String menuUrl){
				/* System.out.println("======================"+servletPath);
				System.out.println("======================"+menuUrl); */
			    if(servletPath!=null&&servletPath.contains("/hotel"+menuUrl)){
			         return " class='active'";
			     }
			    return " class='exp'";
			}
			%>
			
            <%
	        	String servletPath = (String)request.getAttribute("servletPath");
            	for(MenuInfo menu : menus){
	        %>       
				<li class="tables">
					<a href="#" title="" <%=isCurrent(servletPath, menu.getMenuUrl())%>><span><%=menu.getMenuName() %></span></a>        
	            	<ul class="sub">
	            <%
	             for(MenuInfo childMenu : menu.getChildMenus()){
	            %>
	            	<li ><a href="<s:url value='<%=childMenu.getMenuUrl() %>'/>" title="" class="sub_active"><%=childMenu.getMenuName() %></a></li>
	            <%}%>
	            </ul></li>
            <%
            }%>
            <!-- 
            <li class="forms"><a href="#" title=""><span>Form elements</span></a></li>
            <li class="login"><a href="#" title=""><span>Interface elements</span></a></li>
            <li class="typo"><a href="#" title=""><span>Typography</span></a></li>
            <li class="tables"><a href="#" title=""><span>Tables</span></a></li>
              -->
        </ul>
    </div>