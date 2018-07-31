<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
	<div>
		<form action="userController/addOrUpdate.do" id="userform" method="post">
		<table align="center">
			<tr>
				<td align="right">用户ID：</td>
				<td><input type="text" name="uid" value="${user.uid }" /></td>
			</tr>
			<tr>
				<td align="right">用户名：</td>
				<td><input type="text" name="username" value="${user.username }" /></td>
			</tr>
			<tr>
				<td align="right">用户昵称：</td>
				<td><input type="text" name="name" value="${user.name }" /></td>
			</tr>

			<tr>
				<td align="right">性别：</td>
				<td><input type="text" name="sex" value="${user.sex}" /></td>
			</tr>
			<tr>
				<td align="right">出生日期：</td>
				<td><input type="text" name="borntime" value="${user.borntime}" /></td>
			</tr>
			<tr>
				<td align="right">手机号：</td>
				<td><input type="text" name="phone" value="${user.phone}" /></td>
			</tr>
			<tr>
				<td align="right">QQ号：</td>
				<td><input type="text" name="qq" value="${user.qq}" /></td>
			</tr>
			<tr>
				<td align="right">微信：</td>
				<td><input type="text" name="weixin" value="${user.weixin}" /></td>
			</tr>
			<tr>
				<td align="right">微博：</td>
				<td><input type="text" name="weibo" value="${user.weibo}" /></td>
			</tr>
			<tr>
				<td align="right">邮箱：</td>
				<td><input type="text" name="email" value="${user.email}" /></td>
			</tr>
			<%--
			<tr>
				<td align="right">头像ID：</td>
				<td>${user.photoid}</td>
			</tr>

			<tr>
				<td align="right">密码：</td>
				<td>${user.password }</td>
			</tr>
			<tr>
				<td align="right">MD5密码：</td>
				<td>${user.passwordmd5 }</td>
			</tr>

			<tr >
				<td align="right">创建时间：</td>
				<td >${user.createtime }</td>
			</tr>
			<tr >
				<td align="right">用户状态：</td>
				<td >${user.state }</td>
			</tr>
			--%>
		</table>
		</form>
	</div>
</body>

</html>