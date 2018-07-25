<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zzy.model.User"%>
<%@include file="../../js/tags.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户信息查看</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui  --%>
	<%@include file="../../js/easyuiLink.jsp"%>
	
	

	
	<style type="text/css">
	 .gray{
		color:#686868!important;
	 }
	 .cksm{
	 	background-color: #FFCCFF;
	 }
 	/*去除表格间隙*/
 	table{
 		 border-collapse:collapse;
 	}
 	/*表格边框*/
 	table tr,table td{
 		border: 1px solid #959BAA;
 	}
 </style>
 
 <script type="text/javascript">
 	$(function(){
 		$("input").attr("readonly","readonly");
 	
 	
 		$("#photo").click(function(){
 			
		    $('#pohto_dialog').dialog({
			    title: '更换头像',    
			    width: 400,    
			    height: 400,    
			    closed: false, 
			    iconCls:"icon-man",   
			    cache: false,    
			    href: '<%=basePath%>webjsp/userjsp/user_headphoto.jsp',    
			    modal: true,
			    buttons:[{
					text:'上传',
					handler:function(){
						//调用上传的方法
						$("#ff").submit();
					}
				},{
					text:'关闭',
					handler:function(){$('#pohto_dialog').dialog('close');}
				}]
			}); 
 			
 		});
 	});
 	
 	function daochu(){
 		location.href="wordController/createWordByItextForUserinfo.do";
 	}
 </script>
 
</head>
<body>
	<div>
		<table >
			<tr>
				<td colspan="2">
					<a onclick="daochu();" style="cursor: pointer;">导出信息</a>
					<a href="javascript:void(0);">修改个人信息</a>
					<input type="hidden" value="${user.uid }" />
				</td>
			</tr>
			
			<tr>
				<td rowspan="2" align="center">
					<img id="photo" src="${user.photoid }" alt="头像" title="更换头像" width="80px" height="80px" >
				</td>
				<td>${user.name }</td>
			</tr>
			
			<tr>
				<td>${user.username }</td>
			</tr>
			
			<tr>
				<td align="right">密码：</td>
				<td><input type="text" value="${user.password }" /></td>
			</tr>
			
			<tr>
				<td align="right">性别：</td>
				<td><input type="text" value="${user.sex}" /></td>
			</tr>
			<tr>
				<td align="right">出生日期：</td>
				<td><input type="text" value="${user.borntime}" /></td>
			</tr>
			<tr>
				<td align="right">手机号：</td>
				<td><input type="text" value="${user.phone}" /></td>
			</tr>
			<tr>
				<td align="right">QQ号：</td>
				<td><input type="text" value="${user.qq}" /></td>
			</tr>
			<tr>
				<td align="right">微信：</td>
				<td><input type="text" value="${user.weixin}" /></td>
			</tr>
			<tr>
				<td align="right">微博：</td>
				<td><input type="text" value="${user.weibo}" /></td>
			</tr>
			<tr>
				<td align="right">邮箱：</td>
				<td><input type="text" value="${user.email}" /></td>
			</tr>
			
			<tr >
				<td align="right">创建时间：</td>
				<td ><input type="text" value="${user.createtime }" /></td>
			</tr>
				<tr >
				<td align="right">用户状态：</td>
				<td ><input type="text" value="${user.state }" /></td>
			</tr>
		</table>
	</div>
	
	<div id="pohto_dialog"></div>
</body>

</html>