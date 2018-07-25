<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>测试extjs</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 <link href="<%=basePath%>extjs/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
	 <script src="<%=basePath%>extjs/adapter/ext/ext-base.js"></script>
	 <script src="<%=basePath%>extjs/ext-all-debug.js"></script>
	
	<link  href="<%=basePath%>extjs/resources/css/ext‐all.css" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath%>extjs/adapter/ext/ext‐base.js"></script>
	<script src="<%=basePath%>extjs/ext‐all‐debug.js"></script>
	
	
	<script src="<%=basePath%>extjs/adapter/ext/ext‐base.js"></script> 
	<script src="<%=basePath%>extjs/jquery.js"></script>
	<script src="<%=basePath%>extjs/adapter/jquery/ext‐jquery‐adapter.js"> </script> 
	<script src="<%=basePath%>extjs/adapter/yui/ext‐yui‐adapter.js"></script> 
	<script src="<%=basePath%>scriptaculous.js?load=effects"></script> 
	<script src="<%=basePath%>extjs/adapter/prototype/ext‐prototype‐adapter.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>extjs/resources/css/ext‐all.css" />
	<script src="<%=basePath%>extjs/adapter/ext/ext‐base.js"></script>
	<script src="<%=basePath%>extjs/ext‐all‐debug.js"></script>
	<script src="<%=basePath%>extjs/build/locale/ext‐lang‐es.js"></script> 

	
	<script type="text/javascript">
		   //alert弹框
		 /*   Ext.onReady(function(){
		   	Ext.Msg.alert('Hello', 'World');
		   });  
		   
		   Ext.onReady(function(){
				Ext.BLANK_IMAGE_URL = 'images/s.gif';
				Ext.Msg.show({
					title: '这是个测试窗口',
					msg: '哈哈哈哈哈',
					buttons: {
						yes: true,
						//no: true,
						cancel: true
						}
				});
			});
		  */ 
		  
		  Ext.onReady(function(){
		  
			});
	</script>
	
	
	
	
	
	
  </head>
  <body>
	<div style="border: 1px red solid;">
	
	<h1>哈哈哈</h1>
	</div>


  </body>
</html>
