<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>百度Echarts示例</title>
    <script src="<%=basePath%>plugins_sunny/jquery/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>plugins_sunny/echarts/echarts.min.js"></script>

    <style>
        div a{

        }
    </style>

</head>
<body>
<div  style="padding: 50px;">
<a href="http://echarts.baidu.com/examples/" target="_blank">官方demo</a><br/><br/>

<a href="<%=basePath%>webjsp/other/echarts/echarts_zxt_jc.jsp" target="_blank" >基础折线图</a><br/><br/>
    <a href="<%=basePath%>webjsp/other/echarts/echarts_zxt_more.jsp" target="_blank" >多折线图</a><br/><br/>


<a href="<%=basePath%>webjsp/other/echarts/echarts_zhutu_jc.jsp" target="_blank" >基础柱图</a><br/><br/>
<a href="<%=basePath%>webjsp/other/echarts/echarts_zhutu_more.jsp" target="_blank" >多柱图</a><br/><br/>


    <a href="<%=basePath%>webjsp/other/echarts/echarts_饼图.jsp" target="_blank" >饼图</a><br/><br/>

    <a href="<%=basePath%>webjsp/other/echarts/echarts_散点图.jsp" target="_blank" >散点图</a><br/><br/>
    <a href="<%=basePath%>webjsp/other/echarts/echarts_雷达图.jsp" target="_blank" >雷达图</a><br/><br/>

</div>
</body>


</html>
