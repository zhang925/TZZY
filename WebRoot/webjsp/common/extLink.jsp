<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String pathExtJs = request.getContextPath();
String basePathExtJs = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathExtJs+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePathExtJs%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	<%-- extjs  含有 jquery  --%>
	<%--<script type ="text/javascript" src = "<%=basePathExtJs%>plugins_sunny/jquery/jquery-1.9.1.js" > </script>--%>
	<!-- extjs 4.0 以上版本 只需引入这些就可以 -->
	<link rel = "stylesheet" type ="text/css" href= "<%=basePathExtJs%>extjs/resources/css/ext-all.css" />
	<script type ="text/javascript" src = "<%=basePathExtJs%>extjs/bootstrap.js" > </script>


</head>
	<body>
	</body>
</html>