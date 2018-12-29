<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>zTree示例</title>
    <link rel="stylesheet" href="<%=basePath%>plugins_sunny/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.core-3.5.js"></script>
</head>
<body>
<div>
   <div style="margin: 20px;">

       <a href="ztree_jbsl.jsp" target="_blank">基本示例</a> &nbsp;&nbsp;&nbsp;
       <a href="ztree_fxk.jsp" target="_blank">复选框测试</a> &nbsp;&nbsp;&nbsp;

       <a href="ztree_sjk.jsp" target="_blank">读取数据库信息</a> &nbsp;&nbsp;&nbsp;

       <a href="ztree_menu_index.jsp" target="_blank">菜单</a> &nbsp;&nbsp;&nbsp;
   </div>
</div>
</body>
</html>


