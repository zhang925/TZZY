<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	</script>

</head>
<body>
	<div style="border: 1px solid red; height: 400px;">

		<OBJECT id=video1 style="LEFT: 100px; TOP: 100px" height=352 width=350
			classid=clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA>
			<PARAM NAME="_ExtentX" VALUE="12118">
			<PARAM NAME="_ExtentY" VALUE="7938">
			<PARAM NAME="AUTOSTART" VALUE="1">
			<PARAM NAME="SHUFFLE" VALUE="0">
			<PARAM NAME="PREFETCH" VALUE="0">
			<PARAM NAME="NOLABELS" VALUE="0">
			<PARAM NAME="SRC" VALUE="webjsp/test/test.mp4">
			<PARAM NAME="CONTROLS" VALUE="StatusBar,Imagewindow,ControlPanel">
			<PARAM NAME="CONSOLE" VALUE="RAPLAYER">
			<PARAM NAME="LOOP" VALUE="0">
			<PARAM NAME="NUMLOOP" VALUE="0">
			<PARAM NAME="CENTER" VALUE="0">
			<PARAM NAME="MAINTAINASPECT" VALUE="0">
			<PARAM NAME="BACKGROUNDCOLOR" VALUE="#000000">
			<PARAM NAME="EnableFullScreenControls" VALUE="0">
			<embed src="webjsp/testjsp/test.mp4" width="350" height="352"
				autostart="1" loop="0" _extentx="12118" _extenty="7938" shuffle="0"
				prefetch="0" nolabels="0"
				controls="StatusBar,Imagewindow,ControlPanel" console="RAPLAYER"
				numloop="0" center="0" maintainaspect="0" backgroundcolor="#000000"
				enablefullscreencontrols="0"></embed>
		</OBJECT>


	</div>
</body>
</html>
