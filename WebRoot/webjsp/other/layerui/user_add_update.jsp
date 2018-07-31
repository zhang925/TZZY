<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../../../common/tags.jsp"%>
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
	<link rel="stylesheet" href="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/css/layui.css">
	<script src="<%=basePath%>plugins_sunny/jquery/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>plugins_sunny/layerui/layui-2.1.5/src/layui.js"></script>

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
<div style="padding: 10px;">
	<br/>
	<form id="ff" method="post" action="userController/addOrUpdate.do">
		<table >
			<tr>
				<td align="right">用户名：</td>
				<td>
					<input type="text" name="username" value="${user.username }" required  lay-verify="required"  autocomplete="off" class="layui-input">
					<input id="uid" name="uid" value="${user.uid }" type="hidden" />
					<input id="passwordmd5" name="passwordmd5" value="${user.passwordmd5 }" type="hidden" />
					<input id="createtime" name="createtime" value="${user.createtime }" type="hidden" />
					<input id="state" name="state" value="${user.state }" type="hidden" />
					<input id="msg" name="msg" value="update" type="hidden" />
				</td>
			</tr>
			<tr>
				<td align="right">用户昵称：</td>
				<td>
					<input type="text" name="name" value="${user.name }" class="layui-input">
				</td>
			</tr>
			<tr>
				<td align="right">密码 ：</td>
				<td class="">
					<input id="password" name="password" value="${user.password }" class="layui-input"   />
				</td>
			</tr>

			<tr>
				<td align="right">性别：</td>
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
					<input id="borntime" name="borntime" value="${user.borntime}"   class="layui-input" />
				</td>
			</tr>
			<tr>
				<td align="right">手机：</td>
				<td>
					<input id="phone" name="phone" value="${user.phone}"  class="layui-input" />
				</td>
			</tr>
			<tr>
				<td align="right">QQ号：</td>
				<td>
					<input id="qq" name="qq"  value="${user.qq}" class="layui-input" />
				</td>
			</tr>
			<tr>
				<td align="right">微信号：</td>
				<td>
					<input id="weixin" name="weixin" value="${user.weixin}"  class="layui-input"  />
				</td>
			</tr>
			<tr>
				<td align="right">微博：</td>
				<td>
					<input id="weibo" name="weibo" value="${user.weibo}" class="layui-input"  />
				</td>
			</tr>
			<tr>
				<td align="right">邮箱：</td>
				<td>
					<input id="email" name="email" value="${user.email}" lay-verify="email" class="layui-input"  />
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

	<%--	<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>--%>

	</form>
</div>
<script type="text/javascript">
    $(function(){
        //如果是查看，所有的编辑框不能编辑
        if("${load}"=="detail"){
            // readonly //disabled // removeAttr
            $("#ff input,select").attr("readonly","readonly");//这样导致 父页面 也跟着 带了属性。我们在子页面 form 的id 加以区分
        }
    });//加载事件结束


    function save() { //这个方法一定要写到 外面不然 父页面 调不到
		$("#ff").submit();
    }



</script>


</body>

</html>