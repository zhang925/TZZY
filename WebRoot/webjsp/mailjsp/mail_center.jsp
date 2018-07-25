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
<title>邮件</title>
<style type="text/css">
	.mailmenu01{
		cursor: pointer;
		color: blue;
		font-size: 16px;
	}
</style>

<script type="text/javascript">
		/*$.messager.show({
			title:'提示',
			msg:'删除成功！',
			timeout:1000,
			showType:'slide',
			 style:{
				right:'',
				top:document.body.scrollTop+document.documentElement.scrollTop,
				bottom:''
			} 
		});*/
	$(function(){
		//默认进来是欢迎界面
		$("#center").html("<iframe src=\"webjsp/mailjsp/mail_welcome.jsp\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
		//
	
	});
	
	  function mailmenu(obj){
   		var lis=document.getElementsByName("mailmenu");
   		for(var i=0;i<lis.length;i++){
    		if(lis[i]!=obj){
     			lis[i].style.background="#FFFFFF";//原来的背景颜色
     		}else{
      			lis[i].style.background="#E8F2FE";//被点击的换颜色
      		}
    	}
    	var flag=$(obj).text();
    	if(flag=="写信"){//点击	写信
   			$("#center").html("<iframe src=\"webjsp/mailjsp/mail_addordraft.jsp\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="通讯录"){//点击		通讯录
   			$("#center").html("<iframe src=\"txlController/txllist.do\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="草稿箱"){//点击		草稿箱
   			$("#center").html("<iframe src=\"webjsp/mailjsp/mail_list.jsp\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="收件箱"){//点击		收件箱
   			$("#center").html("<iframe src=\"webjsp/mailjsp/mail_list.jsp\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="已发送"){//点击		已发送
   			$("#center").html("<iframe src=\"webjsp/mailjsp/mail_list.jsp\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="已删除"){//点击		已删除
   			$("#center").html("<iframe src=\"webjsp/mailjsp/mail_list.jsp\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}
   		
   		else if(flag=="分组"){//点击	分组
   			$("#center").html("<iframe src=\"webjsp/txljsp/txlgroup_list.jsp\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}
   		
   		
   		/* if(flag=="写信"){//点击写信
   			$("#center").html("<iframe src=\"zmailController/goMailPage.do?type=xx\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="通讯录"){//点击通讯录
   			$("#center").html("<iframe src=\"zmailController/goMailPage.do?type=txl\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="草稿箱"){//点击草稿箱
   			$("#center").html("<iframe src=\"zmailController/goMailPage.do?type=cg\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="收件箱"){//点击收件箱
   			$("#center").html("<iframe src=\"zmailController/goMailPage.do?type=sjx\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="已发送"){//点击已发送
   			$("#center").html("<iframe src=\"zmailController/goMailPage.do?type=yfs\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		}else if(flag=="已删除"){//点击已删除
   			$("#center").html("<iframe src=\"zmailController/goMailPage.do?type=ysc\" frameborder=0 scrolling=no width=800 height=520 ></iframe>");
   		} */
      		
   }
   
  
</script>

</head>
<body style="background-color: #DBE3FB;">
<div>
	<div  class="easyui-layout" style="width:1024px;height:650px;margin: 0 auto;">   
		<!--头部-->
	    <div  data-options="region:'north',split:false" style="height:30px;vertical-align: middle ;">
	    	<div style="margin:4px auto;">
		    	<span>邮件</span>&nbsp;&nbsp;
		    	
		    	<span style="cursor: pointer;" onclick="javascript:location.href='index.jsp'">返回首页</span>&nbsp;&nbsp;
		    	
		    </div>
	    </div>   
	    
	    <!-- 左侧 -->
	    <div data-options="region:'west',title:'中左侧',split:true,collapsible:false" style="width:150px;">
			<!--	导航内容	-->
			<div style="width: 50px;margin: 0 auto; padding: 0px;">
				<br/>
				<div name="mailmenu" class="mailmenu01" onclick="mailmenu(this)">写信</div><br/>
				<div name="mailmenu" class="mailmenu01" onclick="mailmenu(this)">通讯录</div><br/>
				<div name="mailmenu" class="mailmenu01" onclick="mailmenu(this)">草稿箱</div><br/>
				<div name="mailmenu" class="mailmenu01" onclick="mailmenu(this)">收件箱</div><br/>
				<div name="mailmenu" class="mailmenu01" onclick="mailmenu(this)">已发送</div><br/>
				<div name="mailmenu" class="mailmenu01" onclick="mailmenu(this)">已删除</div><br/>
				<div name="mailmenu" class="mailmenu01" onclick="mailmenu(this)">分组</div>
			</div>
		</div>  
		<!-- 中部 -->
	    <div id="center" class="easyui-tabs"  data-options="region:'center'" style="padding:5px;">
	     </div>
	     
	    <!-- 中部右侧 默认关闭 -->
	    <div id="zyc" data-options="closed:true,region:'east',title:'中右侧',split:false,collapsible:false" style="width:150px;">
	   	</div>   
	  
	   
	</div> 
</div>		
	
</body>
</html>