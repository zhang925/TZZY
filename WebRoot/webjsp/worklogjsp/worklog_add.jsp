<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加worklog</title>
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
	<br/>
		<table align="center" width="490px;" id="addstable">
			<tr>
				<td colspan="2" style="font-size: 16px;color: blue;">WorkLog记录</td>
			</tr>
			<tr>
				<td>
					<input id="id" name="id" type="hidden" value="${worklog.id }" />
					<input id="upuid" name="upuid" type="hidden" value="${worklog.uid.uid }" />
					<input id="createusername" name="createusername" type="hidden" value="${worklog.createusername }" />
					<input id="createtime" name="createtime" type="hidden" value="${worklog.createtime }" />
					<input id="uptime" name="uptime" type="hidden" value="${worklog.uptime }" />
					<input id="msg" name="msg" type="hidden" value="${msg }" />
					标题:
				</td>
				<td>
					<textarea id="title" name="title" rows="1" cols="50" class="easyui-validatebox" required="true">${worklog.title }</textarea>
				</td>
			</tr>
			<tr>
				<td>内容:</td>
				<td>
					<textarea  id="content" name="content" rows="5" cols="50" class="easyui-validatebox" required="true">${worklog.content }</textarea>
				</td>
			</tr>
			
		</table>
		<br/><br/>
	</form>
	<script type="text/javascript">
		 function save(){
			$('#ff').form('submit', {    
					    url:'worklogController/saveworklog.do', 
					    onSubmit: function(){ 
					        if(!$("#ff").form('validate')){return false;}else{}
					    },    
					    success:function(data){
					    	 if(data==1){
					        	$.messager.alert('提示','保存成功！','info');
					        	$('#mydialog').dialog('close');//关闭父页面窗口
								$('#t_worklog').datagrid('load',{});//刷新父页面数据
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