<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath1%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<%-- 
<%@include file="../../js/easyuiLink.jsp"%>
<script type="text/javascript" src="<%=basePath1%>js/ajaxfileupload.js"></script>
--%>
</head>
<body>
	<div style="">
	<form id="form" action="uploadServlet" method="post"  enctype="multipart/form-data" >
		<input type="file" name="upload" id="upload" /><br/><br/>
		<input type="submit" value="上传" />
	</form>
	</div>
	<%--
	<a href="download.action?fileName=shouwang.jpg">点击此处下载文档</a>
	 <br/><img src="<%=basePath1%>upload/files/shouwang.jpg"></img>--%>
</body>
 
</html>