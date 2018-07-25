<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>


<style type="text/css">
	#uls li{
		list-style: none;
		float: left;
		padding: 20px;
	}
	
.lightblue{
	background-color: #DBEAF9;
}
.lightgray{
	background-color: #F0F0F0;
}

.blue{
	background-color: lightblue;
}
.linghtred{
	background-color: #F8E2CB;
}



</style>

<script type="text/javascript">
	$(function(){
		//确认帐号变成红色
	
		//开始的时候确认账号(qrzh)显示其他隐藏[安全验证(aqyz)重置密码(czmm)]
		$("#aqyz,#czmm").hide();
		// 1.验证账号
		var  username01  = $("#username").val();
		$("#xyb01").click(function(){
			
			var  username  = $("#username").val();
			//获取用户预留信息
			if(username!=""){
			username01=username;
				$.ajax({
					url:"../../userController/checkUsername.do",
					type:"POST",
					dataType:"json",
					async:false,
					data:{"username":username},
					success:function(data){
						if(data.check=="success"){
							//该账号存在
							$("#aqyz").show();
							$("#qrzh,#czmm").hide();
						}else{
							//该账号不存在
							$("#checkuser").text("*该账号不存在！");	
						}
					},
					error:function(data){
						alert("异常！"+data.check);
					}
				});
			}else{
				$("#checkuser").text("*请输入要找回的账号！");	
			}
		});
		
		// 2.安全验证
		
		$("#xyb02").click(function(){
			var  phone  = $("#phone").val();
			if(phone!=""){
				$.ajax({
					url:"../../userController/checkAqyz.do",
					type:"POST",
					dataType:"json",
					data:{"aqwt":phone,"username":username01},
					success:function(data){
						if(data.check=="success"){
							//验证信息通过！
							$("#usernamexx").text(username01);
							$("#czmm").show();
							$("#qrzh,#aqyz").hide();
						}else{
							//该账号不存在
							$("#checkphone").text("*验证信息错误！");	
						}
					},
					error:function(){
						alert("异常！");
					}
				});
			}else{
				$("#checkphone").text("*请输入问题答案！");	
			}
		});
		// 3.重置密码
		$("#xyb03").click(function(){
			var  password  = $("#password").val();
			var  password01  = $("#password01").val();
			
			
			if(password==""){
				$("#checkpassword").text("*密码不能为空！");	
				return;
			}
			if(password01==""){
				$("#checkpassword").text("*确认密码不能为空！");	
				return;
			}
			
			if(password!=password01){
				$("#checkpassword").text("*两次密码不一致！");	
				return;
			}
				$.ajax({
					url:"../../userController/xgmm.do",
					type:"POST",
					dataType:"json",
					data:{"username":username01,"password":password},
					success:function(data){
						if(data.check=="success"){
							alert("完成重置！");
							location.href="../../index.jsp";
						}else{
							//该账号不存在
							$("#checkpassword").text("*重置失败！");	
						}
					},
					error:function(){
						alert("异常！");
					}
				});
		});
		//按照步骤修改背景颜色
		
	});
</script>

</head>

<body>
<h1>找回密码</h1>

<div>
	<div style="margin: 0 auto; width: 800px;" >
		
			<ul id="uls">
				<li id="li01" class="blue"><h1>1.确认账号</h1></li>
				<li id="li02" class="lightblue"><h1>2.安全验证</h1></li>
				<li id="li03" class="lightgray"><h1>3.重置密码</h1></li>
			</ul>
			<div style="clear: both;"></div>
		<div style="margin-left: 50px;  ">	
			<div id="qrzh">
				<div><h1 style="font-size: 20px;">确认账号</h1></div><br/>
				<div>请输入要找回的账号：<input id="username" name="username" type="text" value="" /></div><br/>
				<div ><span id="checkuser" style="color: red;"></span></div>
				<div id="xyb01" style="cursor: pointer;background-color: blue;color: white;font-weight: bolder;width: 100px;">
					<div id="xyb01" style="margin: 0 auto;width: 57px;">下一步</div>
				</div><br/>
			</div>
			
			<div id="aqyz">
				<div><h1 style="font-size: 20px;">安全验证</h1></div><br/>
				<div>请输入要找回的账号的安全问题答案：安全问题有待添加(这里使用手机号)</div><br/>
				<div><input id="phone" name="phone" type="text" value="" /></div>
				<div ><span id="checkphone" style="color: red;"></span></div><br/>
				<div id="xyb02" style="cursor: pointer;background-color: blue;color: white;font-weight: bolder;width: 100px;">
					<div id="xyb02" style="margin: 0 auto;width: 57px;">下一步</div>
				</div><br/>
			</div>
			
			<div id="czmm">
				<div><h1 style="font-size: 20px;">重置密码</h1></div><br/>
				<div>您要重置的账号是:&nbsp;&nbsp;<span id="usernamexx" style="color: red;"></span></div>
				<div>请输入新的密码：<input id="password" name="password" type="text" value="" /></div>
				<div>请再次输入密码：<input id="password01" name="password01" type="text" value="" /></div><br/>
				<div ><span id="checkpassword" style="color: red;"></span></div><br/>
				<div id="xyb03" style="cursor: pointer;background-color: blue;color: white;font-weight: bolder;width: 100px;">
					<div id="xyb03" style="margin: 0 auto;width: 57px;">完成</div>
				</div><br/>
			</div>
		</div>
	</div>
</div>

</body>
</html>