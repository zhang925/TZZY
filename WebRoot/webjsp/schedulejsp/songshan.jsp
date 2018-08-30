<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ include file="../../js/easyuiLink.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<title>嵩山少林寺</title>
</head>
<body>
<div><a href="<%=basePath%>webjsp/schedulejsp/schedule_list.jsp">返回上一页</a></div>
<br/>
<div>
	<span id="jp" style="cursor: pointer;">一些信息</span>
</div>
<br/>
<div>
	<span id="juti" style="cursor: pointer;">行程</span>
</div>

<br/>

<div id="mydialog"></div>
</body>
<script type="text/javascript">
    $(function(){
        $("#jp").click(function(){
            $('#mydialog').dialog({
                title: '一些信息',
                width: 600,
                top:10,
                //height: 300,
                closed: false,
                cache: false,
                content:'<p>少林寺表演、索道、爬山</p>',
                //href: '<img src="../../images/image/qingdao/airInfo.png"></img>',
                modal: true,
                buttons:[{
                    text:'关闭',
                    handler:function(){$('#mydialog').dialog('close'); }
                }]
            });
        });


        $("#juti").click(function(){
            $('#mydialog').dialog({
                title: '游玩记录',
                width: 300,
                top:10,
                left:150,
                //height: 300,
                closed: false,
                cache: false,
                content:"<h2>(计划2018年)<br/><br/>"
                +"(预计2018年8月26日)<br/><br/>"
                +"2018年8月26日早上出发<br/><br/>"
                +"2018年8月26日上午10点到达登封嵩山<br/><br/>"
                +"2018年8月26日晚上回郑州<br/><br/>"
                +"共1天。<br/><br/></h2>",
                modal: true,
                buttons:[{
                    text:'关闭',
                    handler:function(){$('#mydialog').dialog('close'); }
                }]
            });
        });


    });
</script>

</html>