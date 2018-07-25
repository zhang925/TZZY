<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../js/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户信息添加</title>
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
			});
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
		<table >
			<tr>
				<td align="center" colspan="2">
					<a href="../../TZZY/index.jsp">返回首页</a>
				</td>
			</tr>
			<tr>
				<td align="right">*用户名(长度1-20之间)：</td>
				<td>
					<input placeholder="作为登录名"  id="username" name="username" class="easyui-validatebox"  data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">*用户昵称(长度1-20之间)：</td>
				<td>
					<input  id="name" name="name" class="easyui-validatebox"  data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">*密码(长度1-20之间)：</td>
				<td>
					<input id="password" name="password"  class="easyui-validatebox" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">*确认密码：</td>
				<td>
					<input id="repassword" name="repassword"   class="easyui-validatebox" validType="equals['#password']" data-options="required:true"  />
				</td>
			</tr>
			<tr>
				<td align="right">*性别：</td>
				<td>
					<input id="sex" name="sex"  type="radio" value="男" checked="checked" />男&nbsp;
					<input id="sex" name="sex"  type="radio" value="女"  />女&nbsp;
					<input id="sex" name="sex"  type="radio" value="保密"  />保密
				</td>
			</tr>
			<tr>
				<td align="right">出生日期：</td>
				<td>
					<%-- editable="false" --%>
					<input id="borntime" name="borntime"   class="easyui-datebox" />
				</td>
			</tr>
			<tr>
				<td align="right">手机：</td>
				<td>
					<input id="phone" name="phone"   class="easyui-numberbox" />
				</td>
			</tr>
			<tr>
				<td align="right">QQ号：</td>
				<td>
					<input id="qq" name="qq"   class="easyui-numberbox" />
				</td>
			</tr>
			<tr>
				<td align="right">微信号：</td>
				<td>
					<input id="weixin" name="weixin"/>
				</td>
			</tr>
			<tr>
				<td align="right">微博：</td>
				<td>
					<input id="weibo" name="weibo"/>
				</td>
			</tr>
			<tr>
				<td align="right">邮箱：</td>
				<td>
					<input id="email" name="email"/>
				</td>
			</tr>
			<tr>
				<td align="right">头像：</td>
				<td>
					<input id="photoid" name="photoid" type="file" />
				</td>
			</tr>
			<tr id="checktr" align="center" style="display: none;">
				<td colspan="2" ><span style="color: red;" id="checkinfo" ></span> </td>
			</tr>
			
			<tr align="center">
				<td colspan="2">
					<a id="save" onclick="save();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">保存</a>
					&nbsp;&nbsp;  
					<a id="reset"  onclick="reset();" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">重置</a>  
				</td>
			</tr>
		</table>
	 </form>
<script type="text/javascript">
	 function save(){
			$('#ff').form('submit', {    
					    url:'userController/adduser.do', 
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
								$('#t_user').datagrid('load',{});//刷新父页面数据
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