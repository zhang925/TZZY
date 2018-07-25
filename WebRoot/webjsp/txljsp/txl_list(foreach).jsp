<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>通讯录</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui --%>
	<%@include file="../../js/easyuiLink.jsp"%>
	<%--  头标签 --%>
	<%@include file="../../js/tags.jsp"%>
	
<style type="text/css">
	#mian_left table{width:500px;border:0px;border-collapse:collapse;border-spacing:0;}
    #mian_left tr{height:30px;}
    #mian_left tr td{border-bottom:1px solid #BBB;}
	/*中部开始*/
	#main{
		position:absolute;
		/*top:310px;margin-left: 80px;*/
		width: 850px;
	}
	/*中间左侧*/
	#mian_left{
		width:505px;
		float:left;
		margin-left:10px;
	}
	/*中间右侧*/
	#mian_right{
		width:200px;
		float:left;
		height:600px;
		margin-top:10px;
		margin-left:50px;
		padding-left:10px;
		padding-right:10px;
		border: 1px solid black;
	}
/*中部结束*/
//分组信息css
.spangroup01{
	cursor: pointer;
}
	</style>
	
<script type="text/javascript">

		
		//向通讯录分组div添加值
		$(function(){
			$.ajax({
				url:"txlController/txlbygroup.do",
				type:"POST",
				data:"",
				dataType:"json",
				success:function(data){
					var txllist = data.list;
					for(var i=0;i<txllist.length;i++){
						$("#txl01").append("<span class=\"lspan\">"+txllist[i].name+"</span><span class=\"lspan\" >"+txllist[i].email+"</span><span class=\"lspan\" >"+txllist[i].phone+"</span><hr/>"); 
					}
				}
			});
		});
		
		//通讯录分组点击事件
		 function spangroup(obj){
	   		var lis=document.getElementsByName("spangroup");
	   		for(var i=0;i<lis.length;i++){
	    		if(lis[i]!=obj){
	     			lis[i].style.background="#FFFFFF";//原来的背景颜色
	     		}else{
	      			lis[i].style.background="#EFEFEF";//被点击的换颜色
	      			var groupid = lis[i].getAttribute("groupid");
	      			//location.href="txlController/txllist.do?groupid="+groupid;
	      		}
	    	}
    	}
    	
    	function groupall(){
    		//location.href="txlController/txllist.do";
    	}
	</script>	

</head>

<body>

	<div>
		<div id="main">
			<div id="mian_left">
				<%-- 列表信息 --%>
				<%--<span class="lspan">姓名</span><span class="lspan">邮箱</span><span class="lspan">电话</span><hr/>
				<span id="txl01"></span> --%>
				<table>
					<tr align="center" >
						<td>姓名</td>
						<td>邮箱</td>
						<td>电话</td>
					</tr>
					<c:forEach items="${list }" var="txl">
						<tr align="center" >
							<td >${txl.name }</td>
							<td >${txl.email }</td>
							<td >${txl.phone }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<div id="mian_right">
				<%-- 分组信息 --%>
				<div>通讯录&nbsp;&nbsp;&nbsp;&nbsp;新建组</div>
				<div onclick="groupall()" style="cursor: pointer;">全部(<span>${totaltxlnum }</span>)</div>
				<hr/>
				<c:forEach items="${listtxlgroup }" var="txlgroup">
					<div groupid="${txlgroup.id }" name="spangroup" style="cursor: pointer;" onclick="spangroup(this)">&nbsp;&nbsp;
					${txlgroup.name }&nbsp;(&nbsp;0&nbsp;)
					</div>
				</c:forEach>
				
			</div>
		
		
	 	
   		</div>
   		
   		<%-- 添加窗口 子页面和父页面可认为同一个页面方法随意调取 但子页面的script要写在body内--%>
   		<div id="mydialog"></div>
	</div>
	 	
</body>
</html>