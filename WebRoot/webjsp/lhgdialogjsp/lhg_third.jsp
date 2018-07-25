<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'lgh_first.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/lhgDialog/lhgdialog.min.js"></script>
 
 
 	<script type="text/javascript">
 		$(function(){
 			var frameElement = window.frameElement;
			var api = frameElement.api; 
			W = api.opener;
 			
 			//获取二级窗口的值
 			$("#btn31").click(function(){
 				alert(W.document.getElementById('val02').value);
 			});
 			
 			//调用二级窗口的方法
 			$("#btn32").click(function(){
 				W.second();
 			});
 			
 		});
 	</script>
 
  </head>
  
  
  <body>
    
    <div>
    	<div>
    		<h2>这是三级级级窗口</h2>
    		<br/>
    		<input type="text" value="三级窗口的值" />
    		<br/>
    		<input id="btn31" type="button" value="获取二级级窗口的值" />
    		<br/>
    		<input id="btn32" type="button" value="调用二级级窗口的方法" />
    		
    	</div>
    </div>
    
  </body>
</html>
