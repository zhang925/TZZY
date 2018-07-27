<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>分类管理</title>

	<%@include file="../../js/tags.jsp"%>


	<style type="text/css">
		body{
			/*#E5BC7A*/
			background-color:#DBE3FB;
		}
		/*去除表格间隙*/
		#addstable{
			border-collapse:collapse;
		}
		/*表格边框*/
		#addstable tr,#addstable td{
			border: 1px solid #959BAA;
		}
	</style>
</head>

<body style="background-color: #E8F2FE;">

<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>






<form id="ff" action="" method="post">

	<input id="msg" name="msg" type="hidden" value="${msg }" />
	<input id="id" name="id" type="hidden" value="${classic.id }" />
	<input id="createtime" name="createtime" type="hidden" value="${classic.createtime }" />
	<input id="upuid" name="upuid" type="hidden" value="${classic.uid.uid }" />

	<br/>
	<table align="center"  id="addstable">
		<tr>
			<td colspan="4" style="font-size: 16px;color: blue;"></td>
		</tr>
		<tr>
			<td id="bt">
				标题:
			</td>
			<td colspan="3">
				<input id="title" style="width: 500px;" name="title" class="easyui-validatebox" required="true" value="${classic.title }">
			</td>
		</tr>

		<tr>
			<td >级别:</td>
			<td >
				<input id="level" name="level" class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{label: '1',value: '1',selected:true},{label: '2',value: '2'},{label: '3',value: '3'}]"
					   panelHeight=75px;
					   style="width: 50px;"/>
			</td>
			<td align="right">状态:</td>
			<td align="left">
				<input id="state" name="state" class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{label: '0',value: '0',selected:true},{label: '1',value: '1'}]"
					   panelHeight=50px;
					   style="width: 50px;"/>
			</td>
		</tr>

		<tr>
			<td>内容:</td>
			<td colspan="3"></td>
		</tr>
	</table>



 </form>

	<script type="text/javascript">

		function save(){
			$('#ff').form('submit', {
				url:'classicController/saveClassic.do',
				onSubmit: function(){
					if(!$("#ff").form('validate')){return false;}else{}
				},
				success:function(data){
					if(data==1){
						$.messager.alert('提示','保存成功！','info');
						$('#mydialog').dialog('close');//关闭父页面窗口
						$('#t_classic').datagrid('load',{});//刷新父页面数据
					}else if(data==2){
						$.messager.alert('警告','保存失败！','warning');
					}
				},
				error:function(data){
					if(data==1){
						$.messager.alert('警告','异常,添加成功！','info');
					}else if(data==2){
						$.messager.alert('警告','异常,保存失败！','warning');
					}
				}
			}); //表单提交结束
		}
	</script>
</body>

</html>