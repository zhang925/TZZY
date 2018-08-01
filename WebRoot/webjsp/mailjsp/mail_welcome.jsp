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
    <title>欢迎</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		$(function(){
			//获取当前时间 上下午
			$.ajax({
				url:"zmailController/getQuantumTime.do",
				dataType:"json",
				data:{},
				type:"POST",
				success:function(data){
					var hello="您";
					if(data.hello!=null && data.hello!="undefined" && data.hello!="" ){
						hello = data.hello
					}
					$("#welcometime").text(hello+"好，");
				},
				error:function(data){
					var hello="您";
					if(data.hello!=null && data.hello!="undefined" && data.hello!="" ){
						hello = data.hello
					}
					$("#welcometime").text(hello+"好，");
				}
			});

		
	//加载经典语录
	
		$.ajax({
			url:'classicController/getClassicNow.do',
			type:'POST',
			dataType:'json',
			success:function(data){
				$("#mrjd").html(data.classic.content);
				//$("#mrjd2").html(data.classic.uid.name+"："+data.classic.createtime);
			}
		});
		
	//获取未读邮件数量
		
		
	});
	
	
	//跳转到未读邮件列表
	function goUnReadMail(){
		location.href="webjsp/mailjsp/mail_list.jsp";
	}
	
	</script>
	
  </head>
  <body>
  	<div>
	  	<div>
			<h3><span id="welcometime"></span>${user.name }。</h3>
		</div>
			
		<div>
			<img style="vertical-align:middle; margin:0px auto;cursor: pointer;" title="邮件" width="22px" height="16px" alt="邮件" src="images/image/email/email.jpg">
			：
		<span style="cursor: pointer;" onclick="goUnReadMail()"><span> 0 </span>封未读邮件。</span> 
			
		</div>
		<div>
			<%-- 每日经典部分 --%>
			<h3 id="mrjd" style="font-size:14px;color:red;" ></h3>
		</div>
		
	</div>
  </body>
</html>
