<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../js/easyuiLink.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>测试</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		$(function(){
			

		});
	</script>
	
  </head>
  <body>
	<div>
		邮件列表[已发送、收件箱、已删除、草稿箱]
		<a href="webjsp/mailjsp/mail_welcome.jsp">返回</a>
	</div>
  </body>
</html>
