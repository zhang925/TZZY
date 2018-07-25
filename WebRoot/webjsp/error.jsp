<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../js/easyuiLink.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
<base href="<%=basePath%>">
<title>ERROR</title>
<script type="text/javascript">
	$(function(){
		$("#buttons").click(function(){
			alert($("#selects").val());
		});
	});
</script>


</head>
<body>
	<h1 style="color: red;">错误页面！/(ㄒoㄒ)/~~</h1>
	<input id="buttons" type="button" value="获取select" />
	<select id="selects">
		<option value="2002">2002</option>
		<option value="2003">2003</option>
		<option value="2004">2004</option>
		<option value="2005">2005</option>
		<option value="2006">2006</option>
	</select>
</body>
</html>