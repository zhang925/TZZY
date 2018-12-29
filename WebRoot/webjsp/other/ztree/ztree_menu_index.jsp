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
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>




    <script>
        //点击树
        function zTreeOnCheck(event, treeId, treeNode) {//这个不好，父级元素跟着checked了，最好别用，重复调用了。
            console.log("zTreeOnCheck:")
            console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked)
            console.log("- - - - - - - - - - - - - - - - - - - - - - - - - - -  - --")
        }
        function zTreeOnClick(event, treeId, treeNode) {
            console.log("zTreeOnClick:")
            console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked)
            console.log("- - - - - - - - - - - - - - - - - - - - - - - - - - -  - --")
        }
        function zTreeOnDblClick(event, treeId, treeNode) {
            console.log("zTreeOnDblClick:")
            console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked)
            console.log("- - - - - - - - - - - - - - - - - - - - - - - - - - -  - --")
        }
        function zTreeOnRightClick(event, treeId, treeNode) {
            console.log("zTreeOnRightClick:")
            console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked)
            console.log("- - - - - - - - - - - - - - - - - - - - - - - - - - -  - --")
        }





        function loadTreeData() {
            var datas;
            $.ajax({
                url:"<%=basePath%>api/menu/tree",
                type:"POST",
                data:{},
                async:false,
                dataType:"json",
                success:function(data){
                    datas = data.list;
                }
            });
            return datas;

        }

        var zTreeObj;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {
            check: {
                enable: true,
                autoCheckTrigger: true,
                chkStyle: "checkbox"
            },
            callback:{
                //onCheck : zTreeOnCheck,// checkbox / radio 被勾选 或 取消勾选的事件回调函数

                onClick : zTreeOnClick,//节点被点击的事件回调函数 如果设置了 setting.callback.beforeClick 方法，且返回 false，将无法触发 onClick 事件回调函数。

                onDblClick : zTreeOnDblClick,// zTree 上鼠标双击之后的事件回调函数

                onRightClick : zTreeOnRightClick// zTree 上鼠标右键点击之后的事件回调函数
            }

        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        var zNodes =  loadTreeData();
        $(document).ready(function(){
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });

    </script>




<%-- 引入layui  --%>
    <link rel="stylesheet" href="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/css/layui.css">
    <script src="<%=basePath%>plugins_sunny/jquery/jquery-1.9.1.min.js"></script>
    <script> jq1_9_1 = $.noConflict(true);</script>
    <script src="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/layui.js"></script>




<script language="JavaScript">
    layui.use(['laypage', 'layer','table','form'], function(){
   var  layer = layui.layer
       ,form = layui.form;


     jq1_9_1("#addMenu").on("click",function () {
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
                    location.reload();

                }

            });
        });



    });





    //获取选中信息
    function xz() {
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true),
            v="";
        for(var i=0;i<nodes.length;i++) {
            v = nodes[i].id + ":" + nodes[i].name + "\n" + v;
        }
        console.log(v);
    }



</script>
</head>
    <body>
       <div style="margin:50px;border: 1px solid red;width: 200px; ">
           <div><button id="addMenu" >添加一级菜单</button></div>


           <div>
               <ul id="treeDemo" class="ztree"></ul>
           </div>

           <button onclick="xz()">获取选中信息</button>
       </div>

    </body>
</html>


