<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>测试省市县联动</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="jquery-1.9.1.js"></script>
	<script type="text/javascript">
		$(function(){



		    $("#select1").on("change",function () {
                alert(1);
            });

            $("#select2").on("change",function () {
                alert(2);
            });


		});
	</script>
	
  </head>
  <body>
   		  
     省:
     <select id="select1" name="select1" onchange="getSelectOptionCity(this.value,'select2')">
     	<option id="init" value="">-----请选择-----</option>
     	
     </select>
    市：<select id="select2" name="select2" onchange="getSelectOptionArea(this.value,'select3')"><option value="">-----请选择-----</option></select>
    县：<select id="select3" name="select3" onchange="getSelectOption(this.value,'select4' )"><option value="">-----请选择-----</option></select>

  
  </body>
</html>
