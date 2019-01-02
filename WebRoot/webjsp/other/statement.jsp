<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>各个教程的说明列表</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />

    <style type="text/css">
        #tts{
            border-collapse:collapse;
        }
        #tts tr,#tts td{
            border: 1px solid black;
            text-align: center;
        }
    </style>

    <!--  引入jquery -->
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/jquery/jquery-1.9.1.min.js"></script>

</head>
<body>


<div  style="width: 100%;">
    <table id="tts">
        <tr>
            <td><a href="webjsp/other/extjs/userList.jsp">extjs教程[ 4.0 ]</a></td>
            <td>2017年10月20日 15:32:19</td>
        </tr>
        <tr>
            <td><a href="webjsp/other/easyui/user_list.jsp">easyui教程[ 1.4.3 ]</a></td>
            <td>2017年10月23日 11:05:57</td>
        </tr>
        <tr>
            <td><a href="webjsp/other/jqgrid/user_list.html">jqGrid教程[ 5.2.1 ]</a></td>
            <td>2017年10月23日 11:05:57</td>
        </tr>
        <tr>
            <td><a href="webjsp/other/lhgdialog/lhg_first.jsp">lghdialog教程[ 4.2.0 ]</a></td>
            <td>2017年10月23日 11:05:57</td>
        </tr>
        <tr>
            <td><a href="webjsp/other/layerui/user_list.html">layerui教程[ 2.1.5 ]</a></td>
            <td>2017年10月27日 16:43:42</td>
        </tr>

        <tr>
            <td>angularjs</td>
            <td>2017年</td>
        </tr>

        <tr>
            <td>bootstrap</td>
            <td>2017年</td>
        </tr>

        <tr>
            <td><a target="_blank" href="webjsp/other/echarts/echarts_index.jsp">echarts教程[ 4.2.0 ]</a></td>
            <td>2017年</td>
        </tr>

        <tr>
            <td><a href="webjsp/other/ztree/ztree_index.jsp">ztree教程[ 3.5.3 ]</a></td>
            <td>2017年</td>
        </tr>
        <tr>
            <td>ueditor</td>
            <td>2017年</td>
        </tr>

        <tr>
            <td>Jquery-ui</td>
            <td>2017年</td>
        </tr>
        <tr>
            <td>ArcGIS</td>
            <td>2017年</td>
        </tr>
        <tr>
            <td>Nodejs</td>
            <td>2017年</td>
        </tr>
        <tr>
            <td>mapbox</td>
            <td>2017年</td>
        </tr>
        <tr>
            <td>JQuery Mobile</td>
            <td>2017年</td>
        </tr>
        <tr>
            <td>requirejs</td>
            <td>2017年</td>
        </tr>

        <tr>
            <td><a href="webjsp/liandong/ssxld.jsp">三级联动</a></td>
            <td>2018年08月10日 11:59:21</td>
        </tr>

    </table>


</div>



</body>
</html>
