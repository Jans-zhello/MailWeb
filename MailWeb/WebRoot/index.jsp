<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>QQ邮箱</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	 #main{
	   margin:150px;
	   background-color:#aaffee;
	   font-size:20px;
	 }
	
	</style>
  </head>
  
  <body>
      <div id = "main">
      <form action="doAction" method="post">
             收件人:<input type="text" name="m_name"/><br>
             主&emsp;题:<input type="text" name="m_topic"/><br>
             正&emsp;文:<textarea rows="5" cols="15" name ="m_content"></textarea>
      <br>
      &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="发送"/>
      </form>
      </div>
  </body>
</html>
