<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看经典语录</title>
</head>
<body>
<style type="text/css">
	body{
	/*#E5BC7A*/
 	background-color:#DBE3FB;
 }
 	/*去除表格间隙*/
 	#checktable{
 		 border-collapse:collapse;
 	}
 	/*表格边框*/
 	#checktable tr,#checktable td{
 		border: 1px solid #959BAA;
 	}
</style>

	<table id="checktable" align="center">
		<tr>
			<td>标题：</td>
			<td style="color: blue;">${classic.title }</td>
		</tr>
		<tr>
			<td>创建人：</td>
			<td style="color: red;">${classic.uid.name }&nbsp;&nbsp;${classic.createtime }</td>
		</tr>
		<tr>
			<td>级别：</td>
			<td>${classic.level }</td>
		</tr>
		<tr>
			<td>状态：</td>
			<td>${classic.state }</td>
		</tr>
		<tr>
			<td>内容：</td>
			<td>
				<br/>
				${classic.content }
				<br/>
			</td>
		</tr>
	</table>
</body>
</html>