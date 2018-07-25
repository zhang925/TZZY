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
	
	 var ue = UE.getEditor('editor',
    {
    	elementPathEnabled:false,
    	toolbars:[[
    		'Undo','Redo','Bold','Italic','RemoveFormat',
    		'ForeColor','backColor','Underline',
    		'Paragraph','FontFamily','FontSize',
    		'JustifyLeft','JustifyCenter','JustifyRight','JustifyJustify'
    	]]
    	
    
    });	
   		/*  //给正文赋值
    	$("#yjzt").val(type);
	    var arr = [];
	    arr.push(UE.getEditor('editor').getContent());
	    $("#zw").val(arr);
	    
	    ue.ready(function() {//编辑器初始化完成再赋值  
            ue.setContent('${lxsZnxPage.zw}');  //赋值给UEditor  
       	 });  */
       	 
		$(function(){
			

		});
	</script>
	
  </head>
  <body>
	<div>
		草稿或者写信的时候
		<a href="webjsp/mailjsp/mail_welcome.jsp">返回</a>
	</div>
  </body>
</html>
