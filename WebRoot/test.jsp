<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../js/easyuiLink.jsp" %>
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
  
  
<div id="tt" class="easyui-tabs" style="width:200px;height:250px;">   
    <div title="Tab1" style="padding:20px;">   
        tab1参数1   
    </div>   
    <div title="Tab2" data-options="" style="padding:20px;">   
        tab2 参数2   
    </div>   
    <div title="Tab3" data-options="" style="padding:20px;">   
        tab3   参数3 
    </div>
    <div title="Tab4" data-options="" style="padding:20px;">   
        tab4  参数4  
    </div>  
    <div title="Tab5" data-options="" style="padding:20px;">   
        tab5    参数5 
    </div>   
</div>  


  </body>
</html>
