<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../js/easyuiLink.jsp" %>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>个人中心</title>


<style type="text/css">
	
</style>

<script type="text/javascript">
	$(function(){
	
		/* $.messager.show({
			title:'欢迎来到个人中心',
			msg:'消息将在2秒后关闭。',
			timeout:2000,
			showType:'slide',
			/*style:{
				right:'',
				top:document.body.scrollTop+document.documentElement.scrollTop,
				bottom:''
			}
		});
		 */
		
	
		$('#centers').tabs('add',{    
		    title:"个人中心",    
		    content:"<div><h2>个人中心</h2></div>",    
		    closable:false 
		});  
	
		
	
		$('#menuleft').tree({
			//配置菜单的json
    		url:'webjsp/userjsp/menu_data.json',
    		onClick: function(node){
    			
	   			 if ($('#centers').tabs('exists', node.text)){ 
			         $('#centers').tabs('select', node.text); 
			     }else{
			     	$('#centers').tabs('add',{    
					    title:node.text,    
					    content:"<iframe src="+node.url+" frameborder=0 scrolling=no width=800 height=520 ></iframe>",    
					    closable:true,    
					    /*tools:[{    
					        iconCls:'icon-mini-refresh',    
					        handler:function(){    
					            alert('refresh');    
					        }    
					    }] */   
					});  
			     }//判断tab是否存在结束
		    		
					
			}
		}); 
		
		
		 
	});
	
	function gomail(){
		location.href="webjsp/mailjsp/mail_center.jsp";
	}
</script>

</head>
<body style="background-color: #DBE3FB;">
<div>
	<div id="cc" class="easyui-layout" style="width:1024px;height:650px;margin: 0 auto;">   
	    <div  data-options="region:'north',split:false" style="height:30px;vertical-align: middle ;">
	    	
	    	<div style="margin:4px auto;">
	    	<span>个人中心</span>&nbsp;&nbsp;
	    	
	    	<span style="cursor: pointer;" onclick="javascript:location.href='index.jsp'">返回首页</span>&nbsp;&nbsp;
	    	
	    	<img onclick="gomail()" style="vertical-align:middle; margin:0px auto;cursor: pointer;" title="邮件" width="22px" height="16px" alt="邮件" src="images/image/email/email.jpg">
	    	</div>
	    
	    </div>   
	    
	    
	    <div data-options="region:'west',title:'中左侧',split:true,collapsible:false" style="width:180px;">
			<!--	导航内容	-->
			<ul id="menuleft" class="easyui-tree"></ul>
		</div>  
		
	    <div id="centers" class="easyui-tabs"  data-options="region:'center'" style="padding:5px;">
	    	
	    </div>
	     
	    <%--  
	    <div data-options="region:'east',title:'中右侧',split:false,collapsible:true" style="width:100px;">
	   		
	    </div>   
	   --%>
	    <div data-options="region:'south',split:false" style="height:50px;">
	    	<a href="webjsp/chatroom/index.jsp">在线聊天</a>
	    </div>  
	</div> 
</div>		
	
</body>
</html>