<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath1%>">
<%@include file="../../js/easyuiLink.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<script type="text/javascript" src="<%=basePath1%>js/ajaxfileupload.js"></script>

</head>
<body>
	<br/>
	<%--
	&nbsp;&nbsp;
	<input id="file" name="file"  />
	<br/>
	<span><img src="" id="loading" style="display: none;">呵呵呵</span>
	<img id="testImg" alt="" src="">  
	<input type="button" onclick="ajaxFileUpload();" value="上传" >
	
	<input class="easyui-filebox" id="file" name="file" style="width:200px" data-options="prompt:'Please choose a file'" >
	<input class="easyui-textbox" type="text" name="keywords" data-options="" style="width:200px">
	<br/>--%>
	<img src="" id="loading" style="display: none;">
    <input type="file" id="file" name="file" />
    <br />
    <input type="button" value="上传" onclick="ajaxFileUpload();">
	<input type="button" value="下载" onclick="f_DL()"/>
</body>
 <script type="text/javascript">
    function ajaxFileUpload(){
		
        $("#loading")
        .ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
        });//文件上传完成将图片隐藏起来

		var urls="fileupload.action";
		alert(urls);
        $.ajaxFileUpload({
                url:urls,//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
                    alert(data.message);
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            });
    }

    function f_DL(){
        location.href="fileAction!download.action?filePath="+"D:\\apache-tomcat-7.0.41\\webapps\\ajaxFileUploadDemo\\upload\\1P5521N4-3.jpg";
    }
    </script>
</html>