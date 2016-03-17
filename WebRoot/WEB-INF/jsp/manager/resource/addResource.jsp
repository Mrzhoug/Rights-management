<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'addUser.jsp' starting page</title>

  </head>
  
  <body style="text-align: center;">
    	<form action="${pageContext.request.contextPath }/servlet/ResourceServlet?method=add" method="post">
    		<table border="1" width="700px">
    			<tr>
    				<td>资源名</td>
    				<td>
    					<input type="text" name="name" style="width: 160px;">
    				</td>
    			</tr>
    			<tr>
    				<td>URL</td>
    				<td>
    					<input type="text" name="url" style="width: 160px;">
    				</td>
    			</tr>
   				<tr>
    				<td>
    					<input type="reset" value="重置">
    				</td>
    				<td>
    					<input type="submit" value="添加资源">
    				</td>
    			</tr>
    		</table>
    	</form>
  </body>
</html>
