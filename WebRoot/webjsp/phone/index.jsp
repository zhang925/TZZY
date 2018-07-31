<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>欢迎页面</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <!--  引入jquery  -->
    <script type="text/javascript" src="<%=basePath%>plugins_sunny/jquery/jquery-1.9.1.js"></script>
    <!-- 引入 bootstrap js -->
    <%@include file="../../common/bootstrapLink.jsp"%>
     </head>
  <body>



    <div class="container">
      <div class="row" style="border: 1px solid red; " >
        <!-- 顶部左侧 -->
        <div class="col-xs-6" >顶部左侧</div>
        <!-- 顶部右侧 -->
        <div class="col-xs-6" >
          <div class="col-xs-12"  >

            <a href="webpage/phone/login.html"  style="float: right;">手机端login</a>
          </div>
        </div>

      </div>

      <!-- 中部 -->
      <div class="row"  style="border: 1px solid blue;" >
        <div class="col-xs-12"  >
          <a href="webpage/phone/test.html">手机端test</a>
          <br/>
        </div>
      </div>

      <div class="row"  style="border: 1px solid purple;">
        <div class="col-xs-12"  >
          底部
        </div>
      </div>

    </div>



  </body>
</html>
