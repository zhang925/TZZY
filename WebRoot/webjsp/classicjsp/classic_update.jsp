<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改classic</title>
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
	<form id="ff" action="" method="post">
	
	 <input id="msg" name="msg" type="hidden" value="${msg }" />
	<input id="id" name="id" type="hidden" value="${classic.id }" />
	<input id="upuid" name="upuid" type="hidden" value="${classic.uid.uid }" /> 
	
	<br/>
		<table align="center" width="490px;" id="addstable">
			<tr>
				<td colspan="2" style="font-size: 16px;color: blue;">classic记录</td>
			</tr>
			<tr>
				<td>
					标题:
				</td>
				<td>
					<textarea id="title" name="title" rows="1" cols="50" class="easyui-validatebox" required="true">${classic.title }</textarea>
				</td>
			</tr>
			<tr>
				<td>内容:</td>
				<td>
					<textarea  id="content" name="content" rows="5" cols="50" class="easyui-validatebox" required="true">${classic.content }</textarea>
				</td>
			</tr>
			
			<tr>
				<td>级别:</td>
				<td>
					<input type="text" id="level" name="level" value="${classic.level }" />
				</td>
			</tr>
			
			<tr>
				<td>创建时间:</td>
				<td>
					<input type="text" id="createtime" name="createtime" value="${classic.createtime }" />
				</td>
			</tr>
			
			<tr>
				<td>状态:</td>
				<td>
					<input type="text" id="state" name="state" value="${classic.state }" />
				</td>
			</tr>
			
			
		</table>
		<br/><br/>
	</form>
	<script type="text/javascript">
		function updateClassic(){
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