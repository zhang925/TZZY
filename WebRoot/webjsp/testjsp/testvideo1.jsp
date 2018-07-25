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
// 				alert($(this).attr("aa"));
// 			});
		});
	</script>
	
  </head>
  <body>
  <div style="border: 1px solid red; height: 400px;" >
  	<object id="video" width="400" height="200" border="0" classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA">
		<param name="ShowDisplay" value="0">
		<param name="ShowControls" value="1">
		<param name="AutoStart" value="1">
		<param name="AutoRewind" value="0">
		<param name="PlayCount" value="0">
		<param name="Appearance value="0 value=""">
		<param name="BorderStyle value="0 value=""">
		<param name="MovieWindowHeight" value="240">
		<param name="MovieWindowWidth" value="320">
		<param name="FileName" value="webjsp/testjsp/test.mp4">
		<embed width="400" height="200" border="0" showdisplay="0" showcontrols="1" autostart="1" autorewind="0" playcount="0" moviewindowheight="240" moviewindowwidth="320"
		 filename="/test.mp4" src="webjsp/testjsp/test.mp4">
		</embed>
	</object>
  </div>
  </body>
</html>
