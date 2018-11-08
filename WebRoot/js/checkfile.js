/*
使用方式 引用该js就行 前提是type=file的name=file
<script type="text/javascript" src="${path}/skin/js/checkfile.js"></script>
如果特殊需要，在js中添加，例如单文件上传
if(checkFileSymbol(fileName)==false){//验证文件
    $("#file").val("");
     $("#showFileName"+node).html("");
    return false;
}
*/
function checkFileSymbol(fileval,index) {
    if(fileval){
        var fileType = fileval.substring(fileval.lastIndexOf(".")+1,fileval.length);//文件类型
        var fileName = fileval.substring(fileval.lastIndexOf("\\")+1,fileval.lastIndexOf("."));//文件名字
        var  symbolName = [" ","",",","/","\\", "'","\"", "　"," ",":","*","?","#","（","）","（）"];//文件名校验

        //如果验证什么文件，不可以上传，那么就得一个一个枚举，不能上传的文件，远远很多。
        var  symbolType = [
                            "bat","exe","com","ps1","msi","sys",
                            "sh","py","bin",
                            "sql","dll","flt","inl","php","hpp","chmod","js","css","asp","aspx","java","class","html","htm","vue","ttf","json","md"
                         ];//文件类型校验
        if(symbolType.indexOf(fileType)!=-1){//特殊文件
        	layer.close(index);
            parent.layer.msg("不支持的上传该文件类型");
            return false;
        }


        if(fileName){
            for(var i=0;i<fileName.length;i++){
                if(symbolName.indexOf(fileName[i])!=-1){//特殊文件名字
                	console.log(fileName[i]);
                	layer.close(index);
                    parent.layer.msg("文件名字不能含有特殊字符");
                    return false;
                }
            }
        }

        //判断文件大小




    }
    return true;
}


//单个文件不能大于50M
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function fileChange(target,index) {
    var fileSize = 0;
    //var filetypes = [".jpg", ".png",".rar", ".txt", ".zip", ".doc", ".ppt", ".xls", ".pdf", ".docx", ".xlsx"];
    //var filepath = target.value;
    var filemaxsize = 1024 * 20; //20M
    /*if(filepath) {
        var isnext = false;
        var fileend = filepath.substring(filepath.indexOf("."));
        if(filetypes && filetypes.length > 0) {
            for(var i = 0; i < filetypes.length; i++) {
                if(filetypes[i] == fileend) {
                    isnext = true;
                    break;
                }
            }
        }
        if(!isnext) {
            alert("不接受此文件类型！");
            target.value = "";
            return false;
        }
    } else {
        return false;
    }*/
    if(isIE && !target.files) {
        var filePath = target.value;
        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
        if(!fileSystem.FileExists(filePath)) {
            //alert("附件不存在，请重新输入！");
            //parent.layer.msg("附件不存在，请重新输入！");
            return false;
        }
        var file = fileSystem.GetFile(filePath);
        fileSize = file.Size;
    } else {
    	if(target.files.length > 0){
    		fileSize = target.files[0].size;
    	}
        
    }

    var size = fileSize / 1024;
    if(size > filemaxsize) {
        //alert("附件大小不能大于" + filemaxsize / 1024 + "M！");
    	layer.close(index);
        parent.layer.msg("附件大小不能大于" + filemaxsize / 1024 + "M！");
        target.value = "";
        return false;
    }

    /*if(size <= 0) {
        alert("附件大小不能为0M！");
        target.value = "";
        return false;
    }*/
}






$("body").on("input","[name='file'],[name='files']",function () {
    var fileName = $(this).val();
    var index = layer.msg('加载中', {
	  icon: 16,
	  shade: 0.01
	});
    if(fileChange(this,index)==false){//验证文件
        return false;
    }

    if(checkFileSymbol(fileName,index)==false){//验证文件
        $(this).val("");
        return false;
    }
    layer.close(index);
});





