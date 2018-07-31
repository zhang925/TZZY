<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String pathBs = request.getContextPath();
String basePathBs = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathBs+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePathBs%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	<link rel="stylesheet" href="<%=basePathBs%>plugins_sunny/bootstrap/css/bootstrap.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=basePathBs%>plugins_sunny/bootstrap/css/bootstrap-theme.css" type="text/css"></link>
	<script src="<%=basePathBs%>plugins_sunny/bootstrap/js/bootstrap.js" type="text/javascript"></script>
</head>
	<body>
	</body>
</html>