<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>管理角色的权限</title>
  </head>
  
  <body style="text-align: center;">
    	<table frame="border" width="600px">
    		<tr>
    			<td>用户名称</td>
    			<td>${user.username }</td>
    		</tr>
    		<tr>
    			<td>已分配的角色</td>
    			<td>
    				<c:forEach var="role" items="${userRoles }">
    					${role.name }<br>
    				</c:forEach>
    			</td>
    		</tr>
    		<tr>
    			<td>系统所有的角色</td>
    			<td>
    				<form action="${pageContext.request.contextPath }/servlet/UserServlet?method=addRole" method="post">
	    				<input type="hidden" name="userid" value="${user.id }" />
	    				<c:forEach var="role" items="${systemRoles }">
	    					<input type="checkbox" name="role" value="${role.id}"/>${role.name }<br>
	    				</c:forEach>
	    				<input type="submit" value="分配角色">
    				</form>
    			</td>
    		</tr>
    	</table>
  </body>
</html>
