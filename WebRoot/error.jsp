<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../js/easyuiLink.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">
<title>ERROR</title>
<script type="text/javascript">
	$(function(){
		
	});
</script>
</head>
<body>
	<img alt="" src="${fileUrl }" />
	<h1>O(∩_∩)O~出现错误了O(∩_∩)O~</h1>
</body>
</html>