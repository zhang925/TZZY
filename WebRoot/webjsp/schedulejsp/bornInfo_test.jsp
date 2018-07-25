<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
	<%-- 引入easuui --%>
	<%@include file="../../js/easyuiLink.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>判断页面</title>
	<script type="text/javascript">
	//回车键进入	
	document.onkeydown=function mykeyDown(e){   
       e = e||event;  
       if(e.keyCode == 13) {
       		//location.href="<%=basePath%>webjsp/schedulejsp/bornInfo_list.jsp"; 
       		if($("#test").val()=="147258369"){
       			location.href="webjsp/schedulejsp/bornInfo_list.jsp";
       		}else{
       			alert("密码错误!");
       			//location.href="webjsp/schedulejsp/bornInfo_list.jsp";
       		}
		} 
       return;  
	}
	
		function test(){
			//location.href="<%=basePath%>webjsp/schedulejsp/bornInfo_list.jsp";
			var flag = $("#test").val();
			if(flag=="147258369"){
				location.href="webjsp/schedulejsp/bornInfo_list.jsp";
			}else{
				$.messager.alert("警告","独立密码错误!","warning");
				//location.href="webjsp/schedulejsp/bornInfo_list.jsp";
			}
		};
		$(function(){
			$('#mydialog').dialog({
			    title: '请输入独立密码',    
			    //width: 220,
			    top:100,
			    //height: 300,    
			    closed: false,    
			    cache: false,    
			    //href: "<input id=\"test\" type=\"text\" /><input type=\"button\" onclick=\"test();\" value=\"确定\" />",   
			    modal: true,
			   buttons:[]
			});
			
		});
	</script>
</head>
<body>
	<div id="mydialog" style="background-color:#FFFFDD;">
		<span id="tishi"></span>
		<input id="test" type="password" /><input type="button" onclick="test();" value="确定" />
		
	</div>
</body>
</html>