
function getCookie(cookie_name) {
    var allcookies = document.cookie;//获取cookiename=val;cookiename=val的形式
    var cookie_pos = allcookies.indexOf(cookie_name); //索引的长度
    var cookies = allcookies.split(";");
    if(cookies){
        for(var i in cookies){
            var cookie = cookies[i]; // 形式为cookiename=val
            var cookiename = cookies[i].split("=")[0];
            if(cookiename.trim() == cookie_name.trim()){//相同
                var value = cookies[i].split("=")[1];
                return unescape(value);
            }
        }
    }

    /*
     *
     * // 如果找到了索引，就代表cookie存在，
    // 反之，就说明不存在。

     * if(cookie_pos != -1) {
        // 把cookie_pos放在值的开始，只要给值加1即可。
        //这里容易出问题，所以请大家参考的时候自己好好研究一下
        cookie_pos += cookie_name.length + 1;
        var cookie_end = allcookies.indexOf(";", cookie_pos);

        if(cookie_end == -1) {
            cookie_end = allcookies.length;
        }
         value = unescape(allcookies.substring(cookie_pos, cookie_end)); //这里就可以得到你想要的cookie的值了。。。
    }*/
    return "";
}

function getCookieVal(offset) {
    var endstr = document.cookie.indexOf(";", offset);
    if(endstr == -1) endstr = document.cookie.length;
    return unescape(document.cookie.substring(offset, endstr));
}
//写入到Cookie

function setCookie(name, value, expires) {//单位 天
    var argv = setCookie.arguments;
    //本例中length = 3
    var argc = setCookie.arguments.length;
    var expires = (argc > 2) ? argv[2] : null;
    var date = new Date();
    if(expires){
        var ms = expires*24*3600*1000;
        date.setTime(date.getTime() + ms);
    }
    var path = (argc > 3) ? argv[3] : null;
    var domain = (argc > 4) ? argv[4] : null;
    var secure = (argc > 5) ? argv[5] : false;
    document.cookie = name + "=" + escape(value) + ((expires == null) ? "" : ("; expires=" + date.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
}

function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
