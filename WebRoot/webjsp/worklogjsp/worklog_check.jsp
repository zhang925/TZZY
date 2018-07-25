<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- 引用公用标签 --%>
<%@include file="../../js/tags.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看worklog</title>

</head>
<body>
<style type="text/css">
	/*body{
 	background-color:#DBE3FB;
 }*/
 	/*去除表格间隙*/
 	#tablecheck{
 		 border-collapse:collapse;
 	}
 	/*表格边框*/
 	#tablecheck tr,#tablecheck td{
 		border: 1px solid #959BAA;
 	}
</style>
	<form action="" method="post">
	<br/>
		<table align="center" width="490px;" id="tablecheck">
			<tr>
				<td colspan="2" style="font-size: 16px;color: blue;">查看WorkLog记录</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;">
					<!--创建人：${worklog.uid }<br/>-->
					创建人：${worklog.createusername }<br/>
					创建时间：${worklog.createtime }<br/>
					<c:if test="${! empty  worklog.uptime }">
					修改时间：${worklog.uptime }
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="width: 50px;">标题:</td>
				<td>
					${worklog.title }
				</td>
			</tr>
			<tr>
				<td>内容:</td>
				<td>
					${worklog.content }
				</td>
			</tr>
		</table>
		<br/><br/>
	</form>
</body>
<script type="text/javascript">
	//var windowapi = frameElement.api, W = windowapi.opener;
</script>
</html>