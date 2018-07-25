<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>登陆 </title>
<!-- 普通的 -->

<!--手机端需要添加-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<!--基础样式-->
<link rel="stylesheet" type="text/css" href="css/iconfont.css">
<link rel="stylesheet" type="text/css" href="css/mobile_base.css">
<!--页面样式-->
<link rel="stylesheet" type="text/css" href="css/me.css">
<!--js-->
<script src="js/jquery-1.8.3.min.js"></script> 
<script src="js/app_mian.js"></script>
</head>
<body>
<!-- 代码部分begin -->
<div class="login_reg all_center_box">
  <ul>
    <li> <i class="icon iconfont" >&#xe620;</i>
      <input id="username" name="username" type="text" class="input_text_user ClearInput " placeholder="用户名" required  >
    </li>
    <li><i class="icon iconfont " >&#xe606;</i>
      <input id="pwd" name="pwd" type="password" class="input_text_password mima_dd " placeholder="密码" >
     </li>
  </ul>
  <span id="checkinfo" style="color: red;font-size:12px; font-weight:bolder;display: none;"></span>
  <a href="#"  id="denglu_but" onclick="login();" class="denglu_but" >登陆</a>
  <p align="center"> 
  	<br/>
<a href="#" onclick='toLogin()'>
<img  src="<%=basePath %>image/qqimg/qq_login1.png" alt="使用QQ登录" title="使用QQ登录" style="width:20px;height: 20px; " ></a>
  &nbsp;&nbsp;
  <a href="forgetPwd.jsp" target="_bland" class="f12 c999"   >忘记密码</a>
  </p> 
</div>
<!-- 代码部分end -->
</body>
<script type="text/javascript">
/*登陆的方法*/
function login(){
	//获取用户名
	var username=$("#username").val();
	var pwd=$("#pwd").val();
	var f1=true;
	var f2=true;
	if(username==null || username==''){
		$("#checkinfo").text("*用户名不能为空！");
		$("#checkinfo").show();
		f1=false;
		return false;
	}else{
		$("#checkusername").text("");
		$("#checkusername").hide();
	}
	if(pwd==null || pwd==''){
		$("#checkinfo").text("*密码不能为空！");
		$("#checkinfo").show();
		f2=false;
		return false;
	}else{
		$("#checkinfo").text("");
		$("#checkinfo").hide();
	}
	if(f1==true && f2==true){
		$("#denglu_but").html("登录中……");
		urls = "<%=basePath %>";
		urls = urls + "userController/login.do";
		$.ajax({
			url:urls,
			type:"POST",
			async:false,
			dataType:"text",
			data:{"username":username,"password":pwd},
			success:function(data){
				if(data==1){
					//登录成功
					$("#checkinfo").text("");
					$("#checkinfo").hide();
					//location.reload();
					parent.location.reload();
				}else{
					//登录失败
					$("#checkinfo").text("*用户名或密码错误！");
					$("#checkinfo").show();
					$("#denglu_but").html("登录");
				}
			},
			error:function(data){
				$("#checkinfo").text("*系统异常！");
				$("#denglu_but").html("登录");
			}
		});
	}
}
//按 回车 登录

document.onkeydown=function mykeyDown(e){  
      //compatible IE and firefox because there is not event in firefox  
       e = e||event;  
       if(e.keyCode == 13) {login();}   
       return;  
}

</script>
</html>
