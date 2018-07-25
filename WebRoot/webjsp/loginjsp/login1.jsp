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
<!-- 明码和暗码切换 -->

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
      <input id="username" name="username" type="text" class="input_text_user ClearInput " placeholder="用户名" required  ><a href="javascript:" class="clear_input">x</a>
    </li>
    <li> <i class="icon iconfont " >&#xe606;</i>
      <input id="pwd01" name="pwd01" type="password" class="input_text_password mima_dd " placeholder="密码" >
      <input id="pwd02" name="pwd02" type="text" class="input_text_password mima_wz" style="display:none;" placeholder="密码" >
      <a class="eyes_box " data-show="1" href="javascript:void(0);"><i class="icon iconfont" >&#xe624;</i></a> 
     </li>
  </ul>
<!--  <span id="checkusername" style="color: red;font-size:12px; font-weight:bolder; display: none;" ></span>-->
  <span id="checkpwd" style="color: red;font-size:12px; font-weight:bolder;display: none;"></span>
  <a href="#" onclick="login();" class="denglu_but" >登陆</a>
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
	var pwd="";
	//获取明文密码
	var pwd01=$("#pwd01").val();
	//获取暗文密码
	var pwd02=$("#pwd02").val();
	var f1=true;
	var f2=true;
	if(username==null || username==''){
		$("#checkpwd").text("*用户名不能为空！");
		$("#checkpwd").show();
		f1=false;
		return false;
	}else{
		$("#checkusername").text("");
		$("#checkusername").hide();
	}
	if((pwd01==null || pwd01=='') && (pwd02==null || pwd02=='')){
		$("#checkpwd").text("*密码不能为空！");
		$("#checkpwd").show();
		f2=false;
		return false;
	}else{
		$("#checkpwd").text("");
		$("#checkpwd").hide();
		//获取密码
		if(pwd01==null || pwd01==''){
			pwd=pwd02;
		}else{
			pwd=pwd01;
		}
	}
	if(f1==true && f2==true){
		//alert("用户名："+username+"密码："+pwd);
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
					$("#checkpwd").text("");
					$("#checkpwd").hide();
					//location.reload();
					parent.location.reload();
				}else{
					//登录失败
					$("#checkpwd").text("*用户名或密码错误！");
					$("#checkpwd").show();
				}
			},
			error:function(data){
				$("#checkpwd").text("*系统异常！");
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
