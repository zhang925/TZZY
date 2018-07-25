<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../js/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>城市</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui  --%>
	<%@include file="../../js/easyuiLink.jsp"%>
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
<script type="text/javascript">
		$(function(){
			var load = "${load}";
			if(load=="see"){//查看
				$("[tt='sysconfigure']").attr("disabled","disabled");
			}else if(load=="upt"){//修改
				$("[name='firsttr']").hide();
			}else{//添加
				$("[name='firsttr']").hide();
			}
		
		
		/* 
			//长度的限制方法名可以自定义(minLength)调用	validType:'minLength[5,10]'
			$.extend($.fn.validatebox.defaults.rules, {    
		    	minLength: {    
			       	validator: function(value, param){    
			        		return value.length >= param[0] && value.length <= param[1];    
						},    
						message: '长度为 {0}到{1}之间'   
					  }    
				});  
			//二次密码验证
			//确认密码是否一致
			$.extend($.fn.validatebox.defaults.rules, {    
		   		 equals: {    
		        validator: function(value,param){    
		            return value == $(param[0]).val();    
		        },    
		        message: '两次输入不一致'   
		    	}    
			});
			//数值的判断
			$("#age").numberbox({
				min:0,
				max:150,
				//required:true,
				//missingMessage:"不能为空",
				precision:0
			}); */
			
			
		});//加载事件结束
		
		//清空内容
		/* function reset(){
				//$("#age").val("");
				//$("#borntime").val("");
				$("#checktr").hide;
				$("#checkinfo").text("");
				//清空内容1
				//$("#ff").find("input[name!=sex]").val("");
				//清空内容2 $("#ff").get(0) jquery对象转换成document对象
				$("#ff").get(0).reset();
				
		} */
	</script>
	<div>
	<br/>
	<form id="ff" method="post">
		<table >
			<tr name="firsttr">
				<td align="right">ID:</td>
				<td>
					<input  id="load" name="load" value="${load }" style="display: none;"   />
					<input  name="createuseruid" type="hidden"  value="${sysconfigure.createuser.uid }" tt="sysconfigure"  />
					<input  name="updateuseruid" type="hidden"  value="${sysconfigure.createuser.uid }" tt="sysconfigure"  />
					<input  id="id" name="id"  tt="sysconfigure"  value="${sysconfigure.id }"   />
				</td>
			</tr>
			<tr>
				<td align="right">*配置名称<br/>[英文字符]：</td>
				<td>
					<input  id="name" name="name" value="${sysconfigure.name }" tt="sysconfigure" class="easyui-validatebox"  data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">*配置值：</td>
				<td>
					<input id="value" name="value" value="${sysconfigure.value }" tt="sysconfigure"  class="easyui-validatebox" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">*配置说明：</td>
				<td>
					<textarea  id="statement" name="statement" rows="3" cols="30" tt="sysconfigure" class="easyui-validatebox" data-options="required:true">${sysconfigure.statement}</textarea>
				</td>
			</tr>
			
			<tr name="firsttr">
				<td align="right">创建人：</td>
				<td>
					<input  id="createuser"   value="${sysconfigure.createuser.name }" tt="sysconfigure"  />
				</td>
			</tr>
			<tr name="firsttr">
				<td align="right">创建时间：</td>
				<td>
					<input id="createtime" name="createtime" value="${sysconfigure.createtime }" tt="sysconfigure"    />
				</td>
			</tr>
			<tr name="firsttr">
				<td align="right">修改人：</td>
				<td>
					<input  id="updateuser"  value="${sysconfigure.updateuser.name }" tt="sysconfigure"  />
				</td>
			</tr>
			<tr name="firsttr">
				<td align="right">修改时间：</td>
				<td>
					<input id="updatetime" name="updatetime" value="${sysconfigure.updatetime }" tt="sysconfigure"    />
				</td>
			</tr>
			<tr name="firsttr">
				<td align="right">状态：</td>
				<td>
					<input id="state" name="state" value="${sysconfigure.state }" tt="sysconfigure"   />
				</td>
			</tr>
			
			<tr id="checktr" align="center" style="display: none;">
				<td colspan="2" ><span style="color: red;" id="checkinfo" ></span> </td>
			</tr>
			
		</table>
	 </form>
<script type="text/javascript">
	 function save(){
			$('#ff').form('submit', {    
					    url:'sysConfigureController/save.do', 
					    onSubmit: function(){    
					        // do some check  // return false to prevent submit;  
					        if(!$("#ff").form('validate')){
				    			$("#checktr").show();
				    			$("#checkinfo").text("验证未通过！");
				    			return false;
			    			}else{
			    				$("#checktr").hide;
				    			$("#checkinfo").text("");
			    			}
					    },    
					    success:function(data){
					        if(data==0){//warning question info error
					        	$.messager.alert('警告','该用户名已经注册！','warning');
					        }else if(data==1){
					        	$.messager.alert('提示','保存成功！','info');
					        	$('#mydialog').dialog('close');//关闭父页面窗口
								$('#t_sysconfigure').datagrid('load',{});//刷新父页面数据
					        }else if(data==2){
					        	$.messager.alert('警告','保存失败！','warning');
					        }
					    },
					    error:function(data){
					    	if(data==0){//warning question info error
					        	$.messager.alert('警告','异常,该用户名已经注册！','warning');
					        }else if(data==1){
					        	$.messager.alert('警告','异常,添加成功！','info');
					        }else if(data==2){
					        	$.messager.alert('警告','异常,保存失败！','warning');
					        }
					    }
					}); //表单提交结束 
		}
	 
	 </script>
	</div>
</body>

</html>