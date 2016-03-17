<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>后台权限管理页面</title>
  </head>
  
  <frameset rows="15%,*">
  		<frame src="${pageContext.request.contextPath }/servlet/HeadServlet" name="head" />
  		<frameset cols="20%, *">
  			<frame src="${pageContext.request.contextPath }/servlet/LeftServlet" name="left" />
  			<frame src="" name="right" />
  		</frameset>
  </frameset>
  <body>
    
  </body>
</html>
