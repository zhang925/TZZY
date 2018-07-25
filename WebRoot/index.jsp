<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>ZZYhomePage</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%-- 头部js css 引入  开始 --%>
	<%-- 引用公用标签  <jsp:include page="js/tags.jsp"/> --%>
	<%@include file="js/tags.jsp"%>
<!-- 首页的css	-->
<link rel="stylesheet" type="text/css" href="css/index.css" />
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/lhgDialog/lhgdialog.min.js"></script>
		<%--  头部js css 引入  结束 --%>
	
  </head>
  
  <body class="body">
  <%-- 最外侧div开始 --%>
  <div id="sidediv" style="width: 100%;height: 100%;" >
  
  <%-- 头部开始 --%>
    <div id="head">
    	<!-- 后台	-->
    	<c:if test="${sessionScope.user.state=='admin'}">
    	&nbsp;
    		<span onclick="gobackstage();"  style="font-weight:bolder; color:red ; cursor: pointer;">后台管理</span>
    	</c:if>
		<!--    worklog	-->
		&nbsp;
    	<span onclick="goworklog();"  style="font-weight:bolder; color:red ; cursor: pointer;">worklog</span>
    	&nbsp;
    	<!--    快递	-->
<!--    	<span onclick="kuaidicx();" style="cursor: pointer;color: blue;" >快递查询</span>-->
    	
    	
    	
    	<!-- 登录 -->
    	<c:if test="${sessionScope.user==null }">
    		<div style="float: right;">
    			<span onclick="zhuce();"  style="font-weight:bolder; color: blue; cursor: pointer;" >注册</span>
	    		&nbsp;&nbsp;&nbsp;
	    	</div>
    		<div style="float: right;">
    			<span onclick="logindialog();"  style="font-weight:bolder; color: blue; cursor: pointer;" >登录</span>
	    		&nbsp;
	    	</div>
    	</c:if>
    	<!-- 当前用户  退出 -->
    	<c:if test="${sessionScope.user!=null }">
    		<div style="float: right; position: relative; right: 50px; ">
	    		<b style="font-weight:bolder; color: red; ">当前用户：<span id="nowuser" title="个人中心" style="cursor: pointer;"><img src="${user.photoid }" width="20px;" height="20px;" />${sessionScope.user.name }</span></b>&nbsp;
	    		<span onclick="exitdialog();"  style="font-weight:bolder; color: blue;cursor: pointer;" >退出</span>
	    		
	    	</div>
    	</c:if>
    	
    </div>
    <%-- 头部结束<div class="clearfloat"></div> --%>	
   
    
    <div id="important"  >
  		
    </div>
      
   
<!--    滚动字幕结束  -->

     <%-- 中部开始 --%>
    <div id="main"  >
   
   
    <!--     滚动字幕开始  -->
    <div id="scrolltext">
    	<marquee direction=left behavior=scroll onmouseover=this.stop() onmouseout=this.start()>
    	<span style="color: red;">	
    人生就像蒲公英，看似自由，实则身不由己！许多人，许多事，明明很在乎，可又能怎样呢!缘分来了，就来了!</span>
    	</marquee>
<!--   	◎ direction表示滚动的方向，值可以是left，right，up，down，默认为left
		◎ behavior表示滚动的方式，值可以是scroll（连续滚动）slide（滑动一次）alternate（往返滚动）
		◎onmouseover=this.stop() onmouseout=this.start()表示当鼠标以上区域的时候滚动停止，当鼠标移开的时候又继续滚动。
 	-->
    </div>  	
    <!--     滚动字幕结束  -->
    
    
	  	<%-- 中间部分左侧开始 --%>
	  	<div id="mian_left" style="height: 500px;" >

	  	</div>
	  	<%-- 中间部分左侧结束 --%>
	  	
	  	<%-- 中间部分中部开始 --%>
	  	<div id="mian_center">
	  	 	
	
	  	
	  	
	  	
			<div class="main_wenzi">
			
			

			
			
			
			<a style="color: red;">还有我--任贤齐</a>


<!--	播放器		-->
	<div >
<!--	1、自动播放：autostart=true、false	
		2、循环：loop=正整数、true、false 
		3、板面显示：hidden=ture、no 	
		4、开始时间：starttime=mm:ss（分：秒）
		5、音量大小：volume=0-100之间的整数  
		6、容器属性：：height=# width=#
		7、容器单位：语法：units=pixels、en 说明：该属性指定高和宽的单位为pixels或en。
		8、外观设置：语法：controls=console、smallconsole、playbutton、pausebutton、stopbutton、volumelever
		9、对象名称：name=#
		10、说明文字：语法：title=#
		11、前景色和背景色：palette=color|color
		12、对齐方式：align=top、bottom、center、baseline、 left、right、texttop、middle、absmiddle、absbottom
						height=60px;  -->
 <%-- 
				<embed src="mp3/haiyouwo.mp3" title="冬天的秘密" height=80px;  loop="true" autostart="false" type="audio/mpeg"></embed>
				
	--%>			
				
		  	</div>
<pre>
看着你有些累
想要一个人静一会
你的眼含着泪
我的心也跟着碎
你为哪一个人憔悴
为他扛下所有罪
我为你执迷不悔
整夜无法入睡
就算全世界离开你
还有一个我来陪
怎么舍得让你受尽冷风吹
就算全世界在下雪
就算候鸟已南飞
还有我在这里
痴痴地等你归
你装做无所谓
其实已痛彻心扉
没想像中的坚强
坚强的面对事于非
想要给你的安慰
你淡淡笑着拒绝
满身伤痕的爱情
不值得你付出一切
就算全世界离开你
还有一个我来陪
怎么舍得让你受尽冷风吹
就算全世界在下雪
就算候鸟已南飞
还有我在这里
痴痴地等你归
就算全世界离开你
还有一个我来陪
怎么舍得让你受尽冷风吹
就算全世界在下雪
就算候鸟已南飞
还有我在这里
痴痴地等你归





<!--
取暖回忆	回忆无香
有阳光	还感觉冷
我站在分隔岛上
没有方向	不想回家
你太善良	你太美丽
我讨厌这样想你的自己
不屑此刻的我太甘心与脆弱为邻
没有魂魄	化体温成冰
尴尬的我	始终独自怀抱整个秘密
但朋友都说我太过忧郁
爱你我不能说	看你们拥抱甜蜜
谈笑自若	忍受逾期的伤心
如果我说	我真的爱你
谁来收拾	那些被破坏的友谊
如果我	忍住这个秘密
温暖冬天	就会遥遥而无期
如果我说	我必须爱你
答应给你比友谊更完整的心
如果我	忍住这个秘密
就该错过埋葬冬天的秘密
如果我说	我真的爱你
谁来收拾这被破坏的友谊
如果我	忍住这个秘密
温暖冬天	就会遥遥而无期
就该错过	埋葬冬天的秘密
--></pre>
</div>
		</div>
	  	<%-- 中间部分中部结束 --%>
	  	
	  	
	  	<%-- 中间部分右侧开始 --%>
	  	<div id="mian_right" style="height: 760px;" >
<!--	  		<img src="image/indeximg/bj_main_right01.jpg"></img><br/>
	  			人生就像蒲公英<br/>
		  		看似自由<br/>
		  		实则身不由己<br/>
		  		许多人<br/>
		  		许多事<br/>
		  		明明很在乎<br/>
		  		可又能怎样呢<br/>
		  		缘分来了<br/>
		  		就来了<br/>-->
	  	</div>
	  	<%-- 中间部分右侧结束 --%>
     </div>
      <%-- 中部结束 --%>
      
      <%-- 清除浮动 --%>
      <div class="clearfloat"></div>
      
      <%-- 底部开始 --%>
    <div id="foot">
    	<div id="foot_main">Author：ENDLESS FOREVER </div>
    </div>
    <%-- 底部结束 --%>
    
    
    
  </div>
  <%-- 最外侧div结束 --%>
  </body>
  
  <script type="text/javascript">
  	/*登陆的窗口*/
function logindialog(){
	$.dialog({
	    content: "url:webjsp/loginjsp/login.jsp",//login1.jsp密码明码和暗码可切换
	    title: "登陆",
	    width: "350px",
	    drag: true,//是否允许拖拽
	    resize: false,//是否允许调窗口大小
	    height: 300,
	    lock:true,
	    max: false,
	    min: false,
	    background: "#000", /* 背景色 */
	    opacity: 0.5       /* 透明度 */
	});
}
  
  /*查看worklog窗口*/
  function goworklog(){
  	var flag = "${sessionScope.user.username}";
  	if(flag==null || flag==""){
  		logindialog();
  	}else{
  		//location.href="workloglist.do";
  		location.href="<%=basePath%>webjsp/worklogjsp/worklog_list.jsp";
  	}
  }
  
  /*退出提示框*/
  function exitdialog(){
  	var cont = "<span style=\"font-size:16px;font-weight:bolder;color:red;\">确定退出吗?</span>";
  	$.dialog({
		    content: cont,
		    title: "提示",
		    width: "150px",
		    drag: true,//是否允许拖拽
		    resize: false,//是否允许调窗口大小
		    height: 50,
		    lock:true,
		    max: false,
		    min: false,
		    background: "#000", /* 背景色 */
		    opacity: 0.5,      /* 透明度 */
		    okVal:"继续退出",
		    ok:function(){//退出
		    	$.ajax({
		    		url:"userController/exit.do",
		    		type:"POST",
		    		success:function(){
		    			location.href="<%=basePath%>index.jsp";
		    		},
		    		error:function(){
		    			location.href="<%=basePath%>index.jsp";
		    		}
		    	});
		    },
		    cancelVal:"再看看",
		    cancel:function(){}
		});
  }
  
  
  function gouserlist(){
  	location.href="<%=basePath%>webjsp/userjsp/user_list.jsp";
  }
  
  //进入后台的判断
  function gobackstage(){
  	var flag = '${sessionScope.user.username}';
  	if(flag==null || flag==""){//没有登录  
  		logindialog();
  	}else{
  		location.href="<%=basePath%>webjsp/backstage/main.jsp";
  	}
  }
  
  
  
  function kuaidicx(){
  	$.dialog({
		    content: "url:http://m.kuaidi100.com/index_all.html",
		    title: "快递查询",
		    width: "600px",
		    height: 400,
		    drag: true,//是否允许拖拽
		    resize: false,//是否允许调窗口大小
		    lock:true,
		    max: false,
		    min: false,
		    background: "#FFFF77", /* 背景色 */
		    opacity: 0.5       /* 透明度 */
		    
		});
  }
  

 function zhuce(){
 	window.open("webjsp/userjsp/user_zc.jsp","_self");
  }
  
  	$(function(){
  		$("#nowuser").click(function(){
  			location.href="webjsp/userjsp/user_center.jsp";
  			//window.open('webjsp/userjsp/user_center.jsp','_self','width=1024,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
  		});
  	});
  	
  	
  
  </script>
  
</html>
