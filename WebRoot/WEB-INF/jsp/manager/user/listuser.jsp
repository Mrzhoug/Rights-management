<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'listuser.jsp' starting page</title>

</head>

<body style="text-align: center;">
<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=addUI">添加用户</a>
	<table border="1" width="600px">
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>操作</td>
		</tr>
		<c:forEach var="user" items="${users }">
			<tr>
				<td>${user.username }</td>
				<td>${user.password }</td>
				<td><a href="${pageContext.request.contextPath }/servlet/UserServlet?method=delete&id=${user.id}">删除</a> 
				<a href="${pageContext.request.contextPath }/servlet/UserServlet?method=updateUI&id=${user.id}">修改</a>
				 <a href="${pageContext.request.contextPath }/servlet/UserServlet?method=addRoleUI&id=${user.id}">分配角色</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
