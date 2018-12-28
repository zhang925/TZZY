<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/tags.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>zTree读取数据库信息</title>
    <link rel="stylesheet" href="<%=basePath%>plugins_sunny/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.core-3.5.js"></script>



    <%-- 引入layui  --%>
    <link rel="stylesheet" href="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/css/layui.css">
    <script src="<%=basePath%>plugins_sunny/jquery/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/layui.js"></script>


<script language="JavaScript">
    /*var zTreeObj;

    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {};
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）

    var zNodes = [
        {name:"test1", open:true, children:[{name:"test1_1"}, {name:"test1_2"}]},

        {name:"test2", open:true, children:[{name:"test2_1"}, {name:"test2_2"}]}

    ];

    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
*/

    layui.use(['laypage', 'layer','table','form'], function(){
        var  layer = layui.layer
            ,form = layui.form;


        $("#addMenu").on("click",function () {
            layer.open({
                type:2,
                btn: ['确定', '取消'],
                area: ['50%', '60%'],
                skin: 'layui-layer-lan',
                title: "添加菜单" ,
                shade: [0.5, '#393D49'],
                maxmin: true, //开启最大化最小化按钮
                content:"<%=basePath%>api/menu/jump?load=add",
                yes:function(index,frameDom){
                    var openFrame = $(frameDom).find("iframe")[0].contentWindow; // 窗口
                    var res = openFrame.save();

                    layer.close(index);//关闭当前窗口

                    if(res){//调用 添加方法

                    }else{
                        return false;
                    }
                }

            });
        });



    });




</script>
</head>
    <body>
        <div><button id="addMenu" >添加一级菜单</button></div>


        <div>
            <ul id="treeDemo" class="ztree"></ul>
        </div>

    </body>
</html>


