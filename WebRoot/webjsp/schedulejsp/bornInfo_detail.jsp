<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加/查看/修改(bornInfo)</title>
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
			//长度的限制方法名可以自定义(minLength)调用	validType:'minLength[5,10]'
			$.extend($.fn.validatebox.defaults.rules, {    
		    	minLengthborninfo: {    
			       	validator: function(value, param){    
			        		return value.length >= param[0] && value.length <= param[1];    
						},    
						message: '长度为 {0}到{1}之间'   
					  }    
				});  
			//二次密码验证
			//确认密码是否一致
			/*$.extend($.fn.validatebox.defaults.rules, {    
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
			});*/
		});//加载事件结束
		
		//清空内容
		function reset(){
				//$("#age").val("");
				//$("#borntime").val("");
				$("#checktr").hide;
				$("#checkinfo").text("");
				//清空内容1
				//$("#ff").find("input[name!=sex]").val("");
				//清空内容2 $("#ff").get(0) jquery对象转换成document对象
				$("#ff").get(0).reset();
				
		}
	</script>
	<div>
	<br/>
	<form id="ff" method="post">
		<table id="bornInfoTable">
			<tr>
				<td align="right">*姓名(长度1-20之间)：</td>
				<td>
					<input  id="id" name="id" type="hidden" value="${bornInfo.id }" />
					<input  id="name" name="name" value="${bornInfo.name }" class="easyui-validatebox"  data-options="required:true,validType:'minLengthborninfo[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">QQ号：</td>
				<td>
					<input  id="QQ" name="QQ" value="${bornInfo.QQ }" />
				</td>
			</tr>
			<tr>
				<td align="right">手机号：</td>
				<td>
					<input id="phone" name="phone" value="${bornInfo.phone }"/>
				</td>
			</tr>
			<tr>
				<td align="right">阳历生日：</td>
				<td>
					<input id="ylborntime" name="ylborntime" value="${bornInfo.ylborntime }"   class="easyui-datebox"  />
				</td>
			</tr>
			<tr>
				<td align="right">农历生日：</td>
				<td>
					<input id="nlborntime" name="nlborntime" value="${bornInfo.nlborntime }"   />
				</td>
			</tr>
			<c:if test="${bornInfo!=null && bornInfo.id!=null }">
			<tr>
				<td align="right">年龄：</td>
				<td>
					<input id="age" name="age" value="${bornInfo.age }"  class="easyui-numberbox"  />
				</td>
			</tr>
			</c:if> 
			<tr>
				<td align="right">分类：</td>
				<td>
					<input id="type" name="type" value="${bornInfo.type }" />
				</td>
			</tr>
			<tr>
				<td align="right">级别：</td>
				<td>
					<input id="level" name="level" value="${bornInfo.level }"  />
				</td>
			</tr>
			<c:if test="${bornInfo!=null && bornInfo.id!=null }">
			<tr>
				<td align="right">创建时间：</td>
				<td>
					<input id="createtime" name="createtime" value="${bornInfo.createtime }"  />
				</td>
			</tr>
			<tr>
				<td align="right">修改时间：</td>
				<td>
					<input id="lastuptime" name="lastuptime" value="${bornInfo.lastuptime }"  />
				</td>
			</tr>
			</c:if>
			<tr>
				<td align="right">状态：</td>
				<td>
					<input id="state" name="state" value="${bornInfo.state }"  />
				</td>
			</tr>
			<tr>
				<td align="right">其他说明：</td>
				<td>
					<textarea id="other" name="other" rows="3" cols="30">${bornInfo.other }</textarea>
				</td>
			</tr>
		</table>
	 </form>
<script type="text/javascript">
	$(function(){
		if($("#id").val()!=null && $("#id").val()!='' && '${msg}'=='check'){//为查看
	 			$("#bornInfoTable tr td input,#bornInfoTable tr td textarea").attr("readonly","readonly");
	 		}else{
	 			$("#bornInfoTable tr td input,#bornInfoTable tr td textarea").removeAttr("readonly");
	 			//removeAttr("disabled");
	 		}
	});

	 function save(){
	 		var msg = '';
	 		if($("#id").val()==null || $("#id").val()==''){
	 			msg='add';
	 		}else{
	 			msg='upt';
	 		}
			$('#ff').form('submit', {    
					    url:'bornInfoController/saveOrUptBornInfo.do?msg='+msg, 
					    onSubmit: function(){ 
					        if(!$("#ff").form('validate')){return false;}
					    },    
					    success:function(data){
					        if(data==0){//warning question info error
					        	$.messager.alert('警告','该用户名已经注册！','warning');
					        }else if(data==1){
					        	$.messager.alert('提示','保存成功！','info');
					        	$('#mydialog').dialog('close');//关闭父页面窗口
								$('#t_datagrid').datagrid('load',{});//刷新父页面数据
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