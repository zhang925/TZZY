<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <title>欢迎页面</title>
    <!--  引入jquery  -->
    <script type="text/javascript" src="plugins_sunny/jquery/jquery-1.9.1.js"></script>
    <!-- 引入 bootstrap js -->
    <%@include file="common/bootstrapLink.jsp"%>
    <!-- 引入 主页 js 和 css -->
    <script type="text/javascript" src="indexsource/js/indexpc.js"></script>
    <link rel="stylesheet" href="indexsource/css/indexpc.css" type="text/css"></link>
     </head>
  <body>

    <div id="container" class="container" >

      <div id="head" class="row">
        <div id="loginDevice" class="col-xs-12 col-sm-12" ></div>
        <div class="col-md-3 col-sm-3" ><a href="webpage/backhome/index.html">后台测试</a></div>
        <div class="col-md-3 col-sm-3" ><a href="webpage/other/statement.jsp" target="_blank">各种js教程</a></div>
        <div class="col-md-3 col-sm-3" ><a href="webpage/phone/index.jsp">手机首页</a></div>
        <div id="zhucedenglu" class="col-md-3 col-sm-3" >
          <button class="btn btn-info btn-xs">登陆</button><small> 没有账号？<a href="">点击注册</a> </small>
        </div>
      </div>

      <div class="row" >
        <div class="col-xs-6 col-sm-3" style="background-color: #dedef8; box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
          顶部1
        </div>
        <div class="col-xs-6 col-sm-3"  style="background-color: #dedef8;box-shadow:   inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
          顶部2
        </div>
        <!-- 浮动闭合处
        <div class="clearfix visible-xs"></div>
        -->
        <div class="col-xs-6 col-sm-3" style="background-color: #dedef8; box-shadow:inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
          顶部44
        </div>
        <div class="col-xs-6 col-sm-3"  style="background-color: #dedef8;box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
          顶部55
        </div>
      </div>

      <div class="row" style="border: 1px solid red;" >
        <div class="col-xs-6 col-sm-12"  >
          中部1
        </div>
      </div>

      <div class="row" style="border: 1px solid red;"  >
        <div class="col-xs-6 col-sm-12"  >
          <a href="webpage/phone/index.jsp">测试手机端</a>
        </div>
      </div>

    </div>



  </body>
</html>
