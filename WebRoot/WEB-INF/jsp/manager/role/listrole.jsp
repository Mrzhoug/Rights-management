<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'listrole.jsp' starting page</title>

</head>

<body style="text-align: center;">
<a href="${pageContext.request.contextPath }/servlet/RoleServlet?method=addUI">添加角色</a>
	<table border="1" width="600px">
		<tr>
			<td>角色名</td>
			<td>描述</td>
			<td>操作</td>
		</tr>
		<c:forEach var="role" items="${roles }">
			<tr>
				<td>${role.name }</td>
				<td>${fn:substring(role.description, 0, 10) }${fn:length(role.description)>10?'...':'' }</td>
				<td><a href="${pageContext.request.contextPath }/servlet/RoleServlet?method=delete&id=${role.id}">删除</a> 
				<a href="${pageContext.request.contextPath }/servlet/RoleServlet?method=updateUI&id=${role.id}">修改</a>
				 <a href="${pageContext.request.contextPath }/servlet/RoleServlet?method=addResourceUI&id=${role.id}">分配权限</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
