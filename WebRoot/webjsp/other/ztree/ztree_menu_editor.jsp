<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../../common/tags.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>菜单信息</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- 引入layui  --%>
    <link rel="stylesheet" href="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/css/layui.css">
    <script src="<%=basePath%>plugins_sunny/jquery/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/layui.js"></script>

    <style type="text/css">
        .gray{
            color:#686868!important;
        }
        .cksm{
            background-color: #FFCCFF;
        }
        /*去除表格间隙*/
        table{
            border-collapse:collapse;
        }
        /*表格边框*/
        table tr,table td{
            border: 1px solid #959BAA;
        }

    </style>

</head>
<body>
<div style="padding: 10px;">
    <br/>
    <form id="ff" method="post" action="<%=basePath%>api/menu/save">

        <input  id="load" readonly type="text" name="load" value="${load }"   autocomplete="off" class="layui-input">
        <input  id="menuid" readonly type="text" name="menuid" value="${model.menuid }" required  lay-verify="required"  autocomplete="off" class="layui-input">

        <table >
            <tr>
                <td align="right">父级菜单：</td>
                <td>
                    <input readonly  id="parentid" type="text" name="parent.id" value="${model.parent.id }" required  lay-verify="required"  autocomplete="off" class="layui-input">
                </td>
            </tr>

            <tr>
                <td align="right">菜单名称：</td>
                <td>
                    <input  id="menuname" type="text" name="menuname" value="${model.menuname }" required  lay-verify="required"  autocomplete="off" class="layui-input">
                </td>
            </tr>
            <tr>
                <td align="right">菜单地址：</td>
                <td>
                   <input type="text" id="url" name="url" value="${model.url }" class="layui-input">
                </td>
            </tr>

        </table>


    </form>
</div>
<script type="text/javascript">
    $(function(){
        //如果是查看，所有的编辑框不能编辑
        if("${load}"=="detail"){
            // readonly //disabled // removeAttr
            $("#ff input,select").attr("readonly","readonly");//这样导致 父页面 也跟着 带了属性。我们在子页面 form 的id 加以区分
        }

        var uid = $("#uid").val();
        if(uid!=null && uid!=""){//修改
            $("#username").attr("readonly","readonly");
        }


    });//加载事件结束



    function save() {
        var res = false;

        $("#ff").submit();

        var menuid = $("#menuid").val();
        if(menuid!=null && menuid!=""){//修改

        }
        return res;

    }





</script>


</body>

</html>