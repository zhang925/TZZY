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
	<script type="text/javascript" src="<%=basePath%>plugins_sunny/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugins_sunny/lhgDialog/lhgdialog.min.js"></script>
 
 	<script type="text/javascript">
 	
 	
 	
 	
 		$(function(){
 		//初始化一级窗口的值
 		$("#val01").val("这是一级窗口的值");
 		var mm ; //窗口 对象
 		var erji="";
 		
 		
 		/*
 		这样可以使弹出的窗口始终在第一位
 		var windowapi = frameElement==null?window.top:frameElement.api;
  		var W = windowapi==null?window.top:windowapi.opener;//内容页中调用窗口实例对象接口
		var zIndex = W==null?1976:W.$.dialog.setting.zIndex+1;
		//设置  zIndex:zIndex,*/
		
		
		
 			$("#yiji").click(function(){
		 		mm = $.dialog({
		 			id:'second',
				    content: 'url:webpage/other/lhgdialog/lhg_second.jsp',
				    title: '二级窗口',
				    width: '500px',
				    height: 300,
				    drag: true,//是否允许拖拽
				    resize: true,//是否允许调窗口大小
				    lock:true,
				    max: true,
				    min: true,
				    background: '#E8F2FE', // 背景色
				    opacity: 0.5,       // 透明度 
				    okVal:'确定',
				    
				    background:'#DA0000',
				    zIndex:1977,//层的顺序最低是1976
				    //button:[{name: '登录', callback: function () {alert(1);}}, {name: '取消'}],
				    init:function(){//对话框弹出后执行的funciton
				    
				    },
				    ok:function(){
				    	erji = mm.content.document.getElementById("val02").value;
				    	alert("在一级窗口JSP里面获取二级窗口的值是："+erji);
				    	
				    	//一级页面JSP中调用二级方法
				 		mm.content.second();
				 		
				    	//在 一级窗口生成的二级下	点击	确定	刷新一级窗口
				    	window.location.reload();
				    	//parent.location.reload();
				    	
				    	/*windowapi.get('jlbscs',1).close();
				    	
				    	//$.dialog({id:'second'}).close();
 						api.get('second',1).close();
 				
						var windowapi = frameElement.api, W = windowapi.opener;
						windowapi.location.reload();
						
						
						*/
				    },
				    cancelVal: '关闭',
				    cancel: function(){
				    	if(confirm("确定关闭吗？")){
				    	
				    	}else{
				    		return false;
				    	}
				    }
				});
				
 			});
 			
 			$("#btn011").click(function(){
 				
 			});
 			
 			
 		});
 		
 		
 		function first(){
 			alert("这是first.jsp的一个方法");
 		}
 		
 		
 		
 		
 		
 		
 		function sss(){
 			$.dialog({
		 			id:'second',
				    content: '哈哈哈',
				    title: '二级窗口',
				    width: '500px',
				    height: 300,
				    drag: true,//是否允许拖拽
				    resize: true,//是否允许调窗口大小
				    lock:true,
				    max: true,
				    min: true,
				    background: '#E8F2FE', // 背景色
				    opacity: 0.5,       // 透明度 
				    okVal:'确定',
				    background:'#DA0000',
				    
				    zIndex:1977,
				    ok:function(){},
				    cancelVal: '关闭',
				    cancel: function(){}
				});
 		}
 		//关闭窗口
 		/*$.dialog({id:'sportdg'}).close();
 		var api = frameElement.api, W = api.opener; 
 		api.opener 为载加lhgdialog.min.js文件的页面的window对象
		api.reload();
		api.close();
		方法二、
		var DG = frameElement.lhgDG;
		DG.cancel();  //关闭窗口
		*/
 	</script>
 
  </head>
  
  
  <body>
    
    <div>
    	<div>
    		<h2>这是一级窗口</h2>
    		<span id="yiji" style="cursor: pointer;color: red;">点击生成二级窗口</span>
    		<br/>
    		<input id="val01" type="text" value="" />
    		<br/>
<!--     		在一级窗口获取二级窗口的值：<input id="btn02" type="button" value="确定" /> -->

<br/>
     		<input id="btn011" type="button" value="确定" /> 
    	</div>
    </div>
    
  </body>
</html>
