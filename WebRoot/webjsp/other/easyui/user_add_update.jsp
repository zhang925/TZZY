<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../../common/tags.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户信息修改</title>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui  --%>
	<%@include file="../../common/easyuiLink.jsp"%>
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
<div >
	<br/>
	<form id="ff" method="post">
		<table >
			<tr>
				<td align="right">*用户名(长度1-20之间)：</td>
				<td>
					<input  id="username" name="username" readonly="readonly" value="${user.username }" class="easyui-validatebox"  data-options="required:true,validType:'minLength[1,20]'"  />
					<input id="uid" name="uid" value="${user.uid }" type="hidden" />
					<input id="passwordmd5" name="passwordmd5" value="${user.passwordmd5 }" type="hidden" />
					<input id="createtime" name="createtime" value="${user.createtime }" type="hidden" />
					<input id="state" name="state" value="${user.state }" type="hidden" />
					<input id="msg" name="msg" value="update" type="hidden" />
				</td>
			</tr>
			<tr>
				<td align="right">*用户昵称(长度1-20之间)：</td>
				<td>
					<input  id="name" name="name" value="${user.name }" class="easyui-validatebox"  data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">*密码(长度1-20之间)：</td>
				<td>
					<input id="password" name="password" value="${user.password }" class="easyui-validatebox" data-options="required:true,validType:'minLength[1,20]'"  />
				</td>
			</tr>
			<tr>
				<td align="right">*性别：</td>
				<td>
					<input  name="sex"  type="radio" value="男" <c:if test="${user.sex=='男' ||  user.sex==null || user.sex=='' }">checked="checked"</c:if> />男&nbsp;
					<input  name="sex"  type="radio" value="女" <c:if test="${user.sex=='女'}">checked="checked"</c:if> />女&nbsp;
					<input  name="sex"  type="radio" value="保密" <c:if test="${user.sex=='保密'}">checked="checked"</c:if>  />保密
				</td>
			</tr>
			<tr>
				<td align="right">出生日期：</td>
				<td>
					<%-- editable="false" --%>
					<input id="borntime" name="borntime" value="${user.borntime}"   class="easyui-datebox" />
				</td>
			</tr>
			<tr>
				<td align="right">手机：</td>
				<td>
					<input id="phone" name="phone" value="${user.phone}"  class="easyui-numberbox" />
				</td>
			</tr>
			<tr>
				<td align="right">QQ号：</td>
				<td>
					<input id="qq" name="qq"  value="${user.qq}" class="easyui-numberbox" />
				</td>
			</tr>
			<tr>
				<td align="right">微信号：</td>
				<td>
					<input id="weixin" name="weixin" value="${user.weixin}"  />
				</td>
			</tr>
			<tr>
				<td align="right">微博：</td>
				<td>
					<input id="weibo" name="weibo" value="${user.weibo}" />
				</td>
			</tr>
			<tr>
				<td align="right">邮箱：</td>
				<td>
					<input id="email" name="email" value="${user.email}" />
				</td>
			</tr>
			<!--			<tr>-->
			<!--				<td align="right">头像：</td>-->
			<!--				<td>-->
			<!--					<input id="photoid" name="photoid" type="file" value="${user.photoid}" />-->
			<!--				</td>-->
			<!--			</tr>-->
			<tr id="checktr" align="center" style="display: none;">
				<td colspan="2" ><span style="color: red;" id="checkinfo" ></span> </td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
    $(function(){
        //如果是查看，所有的编辑框不能编辑
		if("${load}"=="detail"){
            // readonly //disabled // removeAttr
            $("#ff input,select").attr("readonly","readonly");//这样导致 父页面 也跟着 带了属性。我们在子页面 form 的id 加以区分
		}

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

        $("#save").click(function(){
            save();
        });//click事件结束

    });//加载事件结束

	function save() {
	    var username = $("#username").val();
	    var uid = $("#uid").val();
	    if(uid!=null && uid!=""){//修改
            dosave();
		}else{//添加
            if(username){
                $.ajax({
                    url: 'userController/checkUser.do',
                    async : false,
                    cache : false,
                    type:"POST",
                    dataType:"json",
                    data:{ "username":username },
                    success:function(data) {
                        if(data.msg=="yes"){//允许注册
                            dosave();
                        }else{
                            $.messager.alert('警告','用户已经存在！','warning');
                        }
                    }
                });
            }
		}

    }


    function dosave(){
        $('#ff').form('submit', {
            url:'userController/addOrUpdate.do',
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
				$.messager.alert('提示','保存成功！','info');
				$('#mydialog').dialog('close');//关闭父页面窗口
				$('#t_user').datagrid('load',{});//刷新父页面数据
            },
            error:function(data){
                $.messager.alert('警告','异常！','warning');
            }
        }); //表单提交结束
    }

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


</body>

</html>