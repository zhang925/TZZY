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
 	
 	.classneed{
 		color: red;
 	}
 
 </style>
</head>
<body>
<script type="text/javascript">
		/*$(function(){
			
		
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
		*/
		
		
		$(function(){
			var type = "${type}";
			if(type=="see"){//查看
				$("[tt='district']").attr("disabled","disabled");
			}else if(type=="upt"){//修改
				$("#id").attr("disabled","disabled");
			}else{//添加
				$("#firsttr").hide();
			}
		});
	</script>
	
	
	<div>
	<br/>
	<form id="ff" method="post">
		<table>
			<tr id="firsttr">
				<td align="right"><span class="classneed">*</span> ID：</td>
				<td>
					<input tt="district" id="id" name="id" class="easyui-validatebox" value="${district.id }" />
				</td>
			</tr>
			
			<tr>
				<td align="right"><span class="classneed">*</span> 行政编码：</td>
				<td>
					<input tt="district" id="xzid" name="xzid" class="easyui-validatebox" value="${district.xzid }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			
			<tr>
				<td align="right"><span class="classneed">*</span> 地区名：</td>
				<td>
					<input tt="district" id="name" name="name" class="easyui-validatebox" value="${district.name }"  data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			
			
			<tr>
				<td align="right"><span class="classneed">*</span> 所属上一级地区行政编码：</td>
				<td>
					<input tt="district" id="parentid" name="parentid" class="easyui-validatebox" value="${district.parentid }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			
			<tr>
				<td align="right"><span class="classneed">*</span> 地区名简称：</td>
				<td>
					<input tt="district" id="shotname" name="shotname" class="easyui-validatebox" value="${district.parentid }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			
			<tr>
				<td align="right"><span class="classneed">*</span> 级别：</td>
				<td>
					<input tt="district" id="leveltype" name="leveltype" class="easyui-validatebox" value="${district.leveltype }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			
			<tr>
				<td align="right">区号：</td>
				<td>
					<input tt="district" id="zipcode" name="zipcode" class="easyui-validatebox" value="${district.zipcode }"  />
				</td>
			</tr>
			
			<tr>
				<td align="right">邮政编码：</td>
				<td>
					<input tt="district" id="citycode" name="citycode" class="easyui-validatebox" value="${district.citycode }"  />
				</td>
			</tr>
			
			<tr>
				<td align="right"><span class="classneed">*</span> 全称：</td>
				<td>
					<input tt="district" id="mergername" name="mergername" class="easyui-validatebox" value="${district.mergername }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			
			<tr>
				<td align="right"><span class="classneed">*</span> 经度：</td>
				<td>
					<input tt="district" id="lng" name="lng" class="easyui-validatebox" value="${district.lng }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right"><span class="classneed">*</span> 纬度：</td>
				<td>
					<input tt="district" id="lat" name="lat" class="easyui-validatebox" value="${district.lat }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right"><span class="classneed">*</span> 拼音：</td>
				<td>
					<input tt="district" id="pinyin" name="pinyin" class="easyui-validatebox" value="${district.pinyin }" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			
		</table>
	 </form>
<script type="text/javascript">
	 /* function save(){
			$('#ff').form('submit', {    
					    url:'adduser.do', 
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
	  */
	 </script>
	</div>
</body>

</html>