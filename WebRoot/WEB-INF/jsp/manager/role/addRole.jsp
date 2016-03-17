<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addUser.jsp' starting page</title>
    
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
    	<form action="${pageContext.request.contextPath }/servlet/RoleServlet?method=add" method="post">
    		<table border="1" width="700px">
    			<tr>
    				<td>角色名</td>
    				<td>
    					<input type="text" name="name" style="width: 160px;">
    				</td>
    			</tr>
    			<tr>
    				<td>角色描述</td>
    				<td>
    					<textarea rows="3" cols="30" name="description"></textarea>
    				</td>
    			</tr>
   				<tr>
    				<td>
    					<input type="reset" value="重置">
    				</td>
    				<td>
    					<input type="submit" value="添加角色">
    				</td>
    			</tr>
    		</table>
    	</form>
  </body>
</html>
