<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String pathEasyUI = request.getContextPath();
String basePathEasyUI = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathEasyUI+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePathEasyUI%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	<%-- 引入easuui --%>
	<!-- 1 . 导入基础 	js	jquery.min.js  -->
	<script src="<%=basePathEasyUI%>plugins_sunny/easyui/jquery.min.js" type="text/javascript"></script>
	<!-- 2 . 导入themes 下的 默认 css -->
	<link rel="stylesheet" href="<%=basePathEasyUI%>plugins_sunny/easyui/themes/default/easyui.css" type="text/css"></link>
	<!-- 3 . 导入 按钮 图标 css	-->
	<link rel="stylesheet" href="<%=basePathEasyUI%>plugins_sunny/easyui/themes/icon.css" type="text/css"></link>
	<!-- 4 . 引入	jquery.easyui.min.js  	-->
	<script src="<%=basePathEasyUI%>plugins_sunny/easyui/jquery.easyui.min.js" type="text/javascript"></script>
	<!-- 5 . 国际化资源文件 easyui-lang-zh_CN.js -->
	<script src="<%=basePathEasyUI%>plugins_sunny/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	</head>
	<body>
	</body>
</html>