<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
		
		  <!-- 下拉刷新 -->
<script type="text/javascript" src="phone/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="phone/js/jquery.masonry.min.js"></script>
<script type="text/javascript" src="phone/js/jquery.infinitescroll.min.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	var $main = $('.main');
	var page="${page}";
	//page = (page==null || page=="") ? "1" : parseInt(page)-1;
	
	$main.infinitescroll({
		navSelector  : '#page-nav',    // 选择的分页导航 
		nextSelector : '#page-nav a',  // 选择的下一个链接到（第2页）
		itemSelector : '.mxlan',     // 选择检索所有项目
		animate: true,
		extraScrollPx: 20,        //离网页底部多少像素时触发ajax  
		loading: {
			finishedMsg: '已经加载全部',
			img: 'phone/images/loading.gif'
		},
		state : {
			   currPage : page // 翻页后避免从1开始
			  },
		 pathParse: ["<%=basePath%>testController/phoneuserlist.do?type=2&page=",""]
	});
	
});
</script>

	
	
  </head>
  
  <body>
    <center>
    <div style="width:100%;height: 20%;background-color: yellow;">ddd</div>
    
    	 <div  class="main" style="width:100%;">
    	 
    		<h3><a href="testController/phoneuserlist.do">刚开始加载一次list</a></h3>
    		<table width="100%">
    			<tr >
    				<td>ID</td><td>昵称</td><td>QQ</td>
    			</tr>
    			<c:forEach items="${list }" var="u">
	    			<tr class="mxlan" style="height:300px;background-color: blue;color: white;">
	    				<td style="width: 30%">${u.uid }</td>
	    				<td style="width: 40%">${u.name }</td>
	    				<td style="width: 30%">${u.qq }</td>
	    			</tr>
	    		
    			</c:forEach>
    		</table>
    	</div>
    	<div id="page-nav">
    		<a href="testController/phoneuserlist.do?page=2"></a>
      	</div>
    	
    	
  </center>
  </body>
  
 


</html>
