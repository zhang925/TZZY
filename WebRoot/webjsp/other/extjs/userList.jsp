<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户信息列表</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <%-- 引入 extjs --%>
    <%@include file="../../common/extLink.jsp"%>
    <!--  引入jquery -->
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
    <%-- 引入 自定义js --%>
    <script type="text/javascript" src="<%=basePath%>webjsp/other/extjs/js/userList.js"></script>
</head>
<body>
用户信息列表<br/>

<div id="userGrid" style="width: 600px;"></div>



</body>
</html>
