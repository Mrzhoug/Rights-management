<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addResource.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align: center;">
    	<!-- 
    	显示已分配的权限
    	资源全部列出来， 让用户选择(复选框) -->
    	<table frame="border" width="600px">
    		<tr>
    			<td>角色名称</td>
    			<td>${role.name }</td>
    		</tr>
    		<tr>
    			<td>已分配的权限</td>
    			<td>
    				<c:forEach var="resource" items="${roleResources }">
    					${resource.name }<br>
    				</c:forEach>
    			</td>
    		</tr>
    		<tr>
    			<td>系统所有的权限</td>
    			<td>
    				<form action="${pageContext.request.contextPath }/servlet/RoleServlet?method=addResource" method="post">
	    				<input type="hidden" name="roleid" value="${role.id }" />
	    				<c:forEach var="resource" items="${systemResources }">
	    					<input type="checkbox" name="resource" value="${resource.id}"/>${resource.name }<br>
	    				</c:forEach>
	    				<input type="submit" value="分配权限">
    				</form>
    			</td>
    		</tr>
    	</table>
  </body>
</html>
