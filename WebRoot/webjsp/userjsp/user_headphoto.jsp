<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改头像</title>
</head>
<body>

<!-- easyui要把script放到body里面 -->

<script type="text/javascript">
 function setImagePreview(){
 	$("#tupian").css("display","block"); 
	var docObj=document.getElementById("doc");  
	var pattern = /(\.*.jpg$)|(\.*.JPG$)|(\.*.png$)|(\.*.PNG$)|(\.*.jpeg$)|(\.*.JPEG$)|(\.*.gif$)|(\.*.GIF$)|(\.*.bmp$)|(\.*.BMP$)|/;     
	if(!pattern.test(docObj.value)){ 
	 		alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
 			docObj.value=null;
	}else if(docObj.files && docObj.files[0]){ 
					var url=window.URL.createObjectURL(docObj.files[0]);
					$("#tupian").empty();
					$("#tupian").append('<img src='+url+' style="width: 100%; height:100%"/>');
				
	}else{
		alert("请选择高版本或新发布的浏览器");
	}

		return true; 
} 

	function a (){
		var temp  = $("#doc").val();
		if(temp!=null && temp!=''){
			return true;
		}else{
			$.messager.alert("提示","请选择一张图片","warning");
			return false;
		}
	}

</script>



<form id="ff" onsubmit="return a();" action="fileUploadController/uploadheadphoto.do" method="post" enctype="multipart/form-data" >

	<div>
		<label> 
			<input id="doc" type="file" name="file"  onchange="javascript:setImagePreview();"  />
		
		</label>
		<div  id="tupian"  style=" display: none;margin: 0 auto;width: 200px;border: 1px solid red;"  >
			<img src="" />
		</div>
		
		
		<br/>
		
	</div>
</form>
</body>
</html>