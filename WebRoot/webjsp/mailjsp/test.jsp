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
			
// 			$("[name=tt]").click(function(){
				alert();
// 				alert($(this).attr("aa"));
// 			});
		});
	</script>
	
  </head>
  <body>
<!--    		ddd<input type="text" aa="ff" id="ss" value="" />sss -->
<!--    		<input name="tt" id="button" type="button" value="测试" aa="bb"  /> -->

<form return="" action=""></form>
  </body>
</html>
