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

<title>My JSP 'listresource.jsp' starting page</title>

</head>

<body style="text-align: center;">
<a href="${pageContext.request.contextPath }/servlet/ResourceServlet?method=addUI">添加资源</a>
	<table border="1" width="600px">
		<tr>
			<td>资源名</td>
			<td>URL</td>
			<td>操作</td>
		</tr>
		<c:forEach var="resource" items="${resources }">
			<tr>
				<td>${resource.name }</td>
				<td>${resource.url}</td>
				<td><a href="${pageContext.request.contextPath }/servlet/ResourceServlet?method=delete&id=${resource.id}">删除</a> 
				<a href="${pageContext.request.contextPath }/servlet/ResourceServlet?method=updateUI&id=${resource.id}">修改</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
