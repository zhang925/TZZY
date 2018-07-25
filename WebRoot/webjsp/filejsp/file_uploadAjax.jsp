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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../js/easyuiLink.jsp"%>
<title>测试不用框架的Ajax上传文件</title>
<script type="text/javascript" src="<%=basePath1%>js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath1%>js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath1%>js/ajaxfileupload.js"></script>



<script type="text/javascript">
	$(function(){
	
	
				$("#sc").click(function(){
				
					    $.ajax({  
					         url : "uploadFileAjax",  
					         type : "POST",  
					         data : $('#uploadForm').serialize(),  
					         success : function(data) {  
					               
					         },  
					         error : function(data) {  
					               
					         }  
					    }); 
				});
			
	
	});

	   

</script>

</head>
<body>






	<form id= "uploadForm" action= "uploadFileAjax" method= "post" enctype ="multipart/form-data">
		<input id="files" name="files" type="file" multiple="multiple" />
		<br/><br/>
		<input  id="sc" type="button" value="上传"  /> 
		
	</form>



	<!--    <a href="uploadFileAjax">uploadFileAjax</a>  -->



   


</body>
</html>
