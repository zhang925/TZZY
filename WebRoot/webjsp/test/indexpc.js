$(function () {//加载事件开始
    //判断当前登陆设备

    /*$.ajax({
        url:"testController/test",
        type:"POST",
        dataType:"json",
        async:false,
        success:function (data) {
            $("#loginDevice").text("当前访问设备："+data.loginDevice);
        }
    });*/

    function isUserAgentPC() {
        var userAgent = navigator.userAgent.toLowerCase();
        var osPC = ["mac os","window","ubuntu","linux"];
        var osPhone = ["android","iphone","ipad"];//android 系统也有关键字 linux
        var checkUserAgentPC = true;
        for(var j = 0;j<osPhone.length;j++){
            if(userAgent.indexOf(osPhone[j])!=-1){//是手机登陆的
                checkUserAgentPC = false;
            }
        }
        return checkUserAgentPC;
    }

    if(isUserAgentPC()){
        $("#loginDevice").text("当前访问设备：PC ");
        //location.href="indexpc.jsp";
    }else{
        $("#loginDevice").text("当前访问设备：手机 ");
    }



});//加载事件结束