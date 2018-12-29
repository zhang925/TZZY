<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/tags.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>zTree_menu_index</title>
    <link rel="stylesheet" href="<%=basePath%>plugins_sunny/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>

    <style>

        #editor{
            margin: 0;
            padding: 0;
            width: 0;
            height: 0;
            border-left: 10px solid green;
            border-right: 10px solid red;
            border-top: 10px solid yellow;
            border-bottom: 10px solid blue;
            border-radius: 100px;
            position: absolute;
            left: 0;
            top: 0;
        }
    </style>




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
            if(treeNode){//防止点击空白报错
                console.log("zTreeOnRightClick:")
                console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked)
                console.log("- - - - - - - - - - - - - - - - - - - - - - - - - - -  - --")
                editor(treeNode.id);

            }

        }

        function getMousePos(event) {
            var e = event || window.event;
            var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
            var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
            var x = e.pageX || e.clientX + scrollX;
            var y = e.pageY || e.clientY + scrollY;
            //alert('x: ' + x + '\ny: ' + y);
            return { 'x': x, 'y': y };
        }

        function editor(id,e) {
            var div=document.getElementById("editor");
            var point = getMousePos();
            div.style.left=point.x+"px";
            div.style.top=point.y+"px";
            $("#editor").show();
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

                onRightClick : zTreeOnRightClick,// zTree 上鼠标右键点击之后的事件回调函数

            }

        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        var zNodes =  loadTreeData();
        $(document).ready(function(){
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);

           /* //给 tree 绑定一个 mouseleave 事件
            $("#treeDemo").bind("mouseleave",function () {// span
                $("#editor").hide();
            });*/

        });

        function onclickBody() {//防止，子级别和父级别传播，不做处理
            $("#editor").hide();
        }
        //e = e||event;
        //e.stopPropagation();
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
    }



</script>
</head>
    <body onclick="onclickBody()">

        <div id="editor" style="display: none;z-index: 9999;">
            <div>
                <a class="layui-btn layui-btn-normal" onclick="alert('详情')" >详情</a><br/>
                <a class="layui-btn" onclick="alert('添加')">添加</a> <br/>
                <a class="layui-btn layui-btn-warm" onclick="alert('修改')">修改</a><br/>
                <a class="layui-btn layui-btn-danger" onclick="alert('删除')" >删除</a>
            </div>
        </div>


       <div style="margin:50px;border: 1px solid red;width: 200px; ">
           <div><button id="addMenu" >添加一级菜单</button></div>


           <div>
               <ul id="treeDemo" class="ztree" onmouseleave="onmouseleaves()"></ul>
           </div>

           <button onclick="xz()">获取选中信息</button>
       </div>

    </body>
</html>


