<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/tags.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>zTree复选框——静态数据</title>
    <link rel="stylesheet" href="<%=basePath%>plugins_sunny/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>

    <script>

        var zTreeObj;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {
            check: {
                enable: true,
                autoCheckTrigger: true,//设置自动关联勾选时是否触发 beforeCheck / onCheck 事件回调函数。
                chkStyle: "checkbox",//radio
            }
        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        var zNodes = [
            {name:"test1", open:true, children:[
                    {name:"test1_1"}, {name:"test1_2"}]},
            {name:"test2", open:true, children:[
                    {name:"test2_1"}, {name:"test2_2"}]}
        ];
        $(document).ready(function(){
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });



    </script>



</head>
<body>

<div>
    <ul id="treeDemo" class="ztree"></ul>
</div>

</body>
</html>


