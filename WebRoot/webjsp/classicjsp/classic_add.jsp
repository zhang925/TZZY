<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加classic</title>

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


	<script type="text/javascript" charset="utf-8" src="webjsp/classicjsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="webjsp/classicjsp/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="webjsp/classicjsp/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    //实例化编辑器 必须有
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('content');
   /*  ue.ready(function() {//编辑器初始化完成再赋值  
          ue.setContent('');  //赋值给UEditor  
      });  */ 
    	
    	
</script>




 <form id="ff" action="" method="post">	
	<%-- 
	<input id="msg" name="msg" type="hidden" value="${msg }" />
	<input id="id" name="id" type="hidden" value="${classic.id }" />
	<input id="createtime" name="createtime" type="hidden" value="${classic.createtime }" />
	<input id="upuid" name="upuid" type="hidden" value="${classic.uid.uid }" />
	--%>
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
				
				<td colspan="3">
				<!--	id 和 name 都是 TextEditor			-->
				<!--<div>
					<iframe width="675px" height="301px" scrolling="auto" src="<%=basePath2 %>myeditor/TextEditor.jsp"></iframe>
				</div>
				-->
<!-- <textarea  id="content" name="content" rows="5" cols="50" class="easyui-validatebox" required="true">${classic.content }</textarea> -->
				
			
					<div>
<!-- 						<iframe width="630px" height="340px" scrolling="auto" src="webjsp/classicjsp/ueditor.jsp"></iframe> -->
					
					
			<div>
			     <script id="content" name="content" type="text/plain" style="width:600px;height:170px;"></script>
			</div>
					
					
					</div>
					
					
			
				
				</td>
				
			</tr>
			
			<tr>
				
		</table>
		<br/><br/>
		
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