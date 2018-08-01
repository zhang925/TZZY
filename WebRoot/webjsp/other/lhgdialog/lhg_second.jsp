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

	  <script type="text/javascript" src="<%=basePath%>plugins_sunny/jquery/jquery-1.8.3.min.js"></script>
	  <script type="text/javascript" src="<%=basePath%>plugins_sunny/lhgDialog/lhgdialog.min.js"></script>

	  <script type="text/javascript">
	 	var frameElement = window.frameElement;
		var api = frameElement.api; 
		W = api.opener;
 		var demoDG3 ;	
 		$(function(){
 		//加载的时候把一级窗口的值传过来
		document.getElementById('val021').value = W.document.getElementById('val01').value;
 			
 			$("#erji").click(function(){
		 		demoDG3 = $.dialog({
		 			id:'third',
				    content: 'url:webjsp/other/lhgdialog/lhg_third.jsp',
				    title: '三级窗口',
				    width: '300px',
				    height: 250,
				    drag: true,//是否允许拖拽
				    resize: false,//是否允许调窗口大小
				    lock:true,
				    max: false,
				    min: false,
				    background: '#E8F2FE', // 背景色
				    opacity: 0.5,       // 透明度 
				    okVal:'保存',
				    zIndex:1978,
				    ok:function(){
				    
				   // 只能调用本页面多的
				   // $.dialog.list['third'].reload($.dialog.list['third'].content);
				   
				    //关闭三级窗口  刷新二级窗口
				    
				    //demoDG2.content.cDG 为B窗口中调用C窗口的实例对象 
				    //demoDG2.content.cDG.content 即为C窗口内容页的window对象
				    //demoDG3.reload(demoDG3.content);
				    //$.dialog({id:'frist'}).close();
							window.location.reload(W.content);
				    },
				    cancelVal: '关闭',
				    cancel: function(){}
				});
 			});
 			
 			
 			//在二级窗口获取一级窗口的值
 			$("#btn02").click(function(){
 				alert(W.document.getElementById('val01').value);
 			});
 			
 			
 			//在二级窗口调用一级窗口的方法
 			$("#btn021").click(function(){
 				W.first();
 			});
 			
 			
 			$("#btn022").click(function(){
 				//$.dialog({id:'second'}).close();
 				api.get('second',1).close();
 				W.sss();
 				
				
 			});
 			
 		});
 		
 		
 		
 		
 		
 		window.onload = function(){
 			
		};
 		
 		
 		function second(){
 			alert("这是二级页面的方法");
 		}
 		
 	</script>
 
  </head>
  
  
  <body>
    
    <div>
    	<div>
    		<h2>这是二级级窗口</h2>
    		<span id="erji" style="cursor: pointer;color: red;" >点击生成三级窗口</span>
    		<br/>
    		<input id="val02" type="text" value="二级窗口的值" />
    		<br/>
    		在二级窗口JSP里面获取一级窗口的值<input id="btn02" type="button" value="确定" />
    		<br/>
    		加载时候把一级窗口的值传过来<input id="val021" type="text" value="" />
    		<br/>
    		在二级窗口JSP里面调用一级窗口的方法<input id="btn021" type="button" value="确定" />
    		<br/>
    		
    		关闭当前窗口：生成新的窗口<input id="btn022" type="button" value="确定" />
    		
    	</div>
    </div>
    
  </body>
</html>
