<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>正在跳转……</title>
    <script type="text/javascript" src="plugins_sunny/jquery/jquery-1.9.1.js"></script>
    <script>
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

        function jump() {
            var jumpSrc="indexpc.jsp";
            if(!isUserAgentPC()){
                jumpSrc="webpage/phone/index.jsp";
            }
            $.ajax({
                url: jumpSrc,
                success: function(res){
                    document.write(res);
                }
            });
            //location.href=jumpSrc;
        }
        window.onload = function () {
            jump();
        };

    </script>
</head>
<body>


</body>
</html>
