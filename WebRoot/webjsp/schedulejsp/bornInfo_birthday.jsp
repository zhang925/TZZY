<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="../../js/easyuiLink.jsp"%>
	<%@include file="../../js/tags.jsp"%>
	
</head>
<body>
<script type="text/javascript">
//通过ajax获取当天是否有人过生日
		$(function(){
			$("#t_datagridtx").datagrid({
				title:'今天过生日列表',
				idField:'id',//标识列
				width:600,
				//height:400,
				url:'bornInfoController/tixing.do',
				rownumbers:true,//行号
				singleSelect:true,//单选
				columns:[[    
							{field:'id',title:'ID',align:'center'},
							{field:'name',title:'姓名',align:'center'},
							{field:'QQ',title:'QQ',align:'center'},
					        {field:'phone',title:'手机号',align:'center'},
					        {field:'ylborntime',title:'阳历出生日期',align:'center'},
					        {field:'nlborntime',title:'农历出生日期',align:'center'},
					     ]],
				loadMsg:'正在加载,请稍后……',
				pagination:true,
				pageSize: 10,
		        pageList: [5,10,15] 
			});
	});
</script>
	<div>
		<%--<span style="color: red;"></span><br/>--%>
		<table id="t_datagridtx"></table>
	</div> 
</body>
</html>