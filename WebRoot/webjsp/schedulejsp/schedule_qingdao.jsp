<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ include file="../../js/easyuiLink.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>青岛</title>
</head>
<body>
	<div><a href="<%=basePath%>webjsp/schedulejsp/schedule_list.jsp">返回上一页</a></div>
	<br/>
	<div>
		<span id="jp" style="cursor: pointer;">机票信息</span>
	</div>
	<br/>
	<div>
		<span id="juti" style="cursor: pointer;">行程</span>
	</div>
	
	<br/>
	
	<div id="mydialog"></div>
</body>
<script type="text/javascript">
	$(function(){
		$("#jp").click(function(){
			$('#mydialog').dialog({    
			    title: '机票信息',    
			    width: 1000,
			    top:10,  
			    //height: 300,    
			    closed: false,    
			    cache: false,  
			    content:"<img src=\"<%=basePath%>images/image/qingdao/airInfo.png\"></img>",
			    //href: '<img src="../../images/image/qingdao/airInfo.png"></img>',
			    modal: true,
			    buttons:[{
					text:'关闭',
					handler:function(){$('#mydialog').dialog('close'); }
				}]
			}); 
		});
		
		
		$("#juti").click(function(){
			$('#mydialog').dialog({    
			    title: '游玩记录',    
			    width: 300,
			    top:10, 
			    left:150, 
			    //height: 300,    
			    closed: false,    
			    cache: false,  
			    content:"<h2>(计划2016年6月)<br/><br/>"
			    		+"(预计2016年6月17日)<br/><br/>"
			    		+"2016年6月17日晚出发，<br/><br/>"
			    		+"6月18号清晨4点到达青岛，<br/><br/>"
			    		+"6月20号晚上回北京，<br/><br/>"
			    		+"待了三天。<br/><br/></h2>",     
			    modal: true,
			    buttons:[{
					text:'关闭',
					handler:function(){$('#mydialog').dialog('close'); }
				}]
			}); 
		});
		
		
	});
</script>

</html>