<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>测试省市县联动</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../../plugins_sunny/jquery/jquery-1.11.1.min.js"></script>
  </head>
  <body>
        <button onclick="location.reload()">刷新</button><br/>
    省: <select id="select1" name="select1" onchange="initSecond()"></select>
    市：<select id="select2" name="select2" onchange="initThird()" ><option></option></select>
    县：<select id="select3" name="select3"><option></option></select>
  </body>
</html>

<script type="text/javascript">
    var province = [];//省份的数组
    var city = [];//市的数组
    var area = [];//区的数组
    if(province.length<=0){
        $.ajax({
            url:"../../provinceController/provincelist.do",
            type:"post",
            async:false,
            data:{page:1,rows:100},
            dataType:"json",
            success:function(data){
                province = data.rows;
            }
        });
    }
   if(city.length<=0){
       $.ajax({
           url:"../../cityController/list.do",
           type:"post",
           async:false,
           data:{page:1,rows:500},
           dataType:"json",
           success:function(data){
               city = data.rows;
           }
       });
   }
    if(area.length<=0){
        $.ajax({
            url:"../../areaController/list.do",
            type:"post",
            async:false,
            data:{page:1,rows:5000},
            dataType:"json",
            success:function(data){
                area = data.rows;
            }
        });
    }

    function initFirst() {
        var appendHtml = "";
        province.forEach(function (val,i) {
            appendHtml += '<option id="'+val.id+'" value="'+val.code+'">'+val.name+'</option>'
        });
        $("#select1").append(appendHtml);
        initSecond();
    }
    function initSecond() {
        var provincecode1 = $("#select1").val();
        var thisCityArr = [];
        city.forEach(function (val,i) {
            var provincecode = val.provincecode;
            if(provincecode1==provincecode){
                thisCityArr.push(val)
            }
        });
        var appendHtml = "";
        thisCityArr.forEach(function (val,i) {
            appendHtml += '<option id="'+val.id+'" value="'+val.code+'">'+val.name+'</option>'
        });
        $("#select2,select3").html('');
        $("#select2").append(appendHtml);
        initThird();
    }
    function initThird() {
        var cityCode1 = $("#select2").val();
        var thisAreaArr = [];
        area.forEach(function (val,i) {
            var cityCode = val.citycode;
            if(cityCode1==cityCode){
                thisAreaArr.push(val)
            }
        });
        var appendHtml = "";
        thisAreaArr.forEach(function (val,i) {
            appendHtml += '<option id="'+val.id+'" value="'+val.code+'">'+val.name+'</option>'
        });
        $("#select3").html('');
        $("#select3").append(appendHtml);
    }
    initFirst();
</script>
