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

        function loadTreeData() {
            var datas;
            $.ajax({
                url:"<%=basePath%>api/menu/tree",
                type: "POST",
                ContentType: "application/json; charset=utf-8",
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
                autoCheckTrigger: true,//设置自动关联勾选时是否触发 beforeCheck / onCheck 事件回调函数。
                chkStyle: "checkbox"
            },
            /* data: {
                  simpleData: {
                      enable:true,        //是否使用简单数据模式
                      idKey: "id",        //节点数据中保存唯一标识的属性名称
                      pIdKey: "p_id",     //节点数据中保存其父节点唯一标识的属性名称
                      rootPId: ""         //用于修正根节点父节点的数据，即pIdKey指定的属性值
                  }
              },*/
        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
        var zNodes =  loadTreeData();
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


