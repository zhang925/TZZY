package com.zzy.util;

/**正则表达式*/
public class Util_Regular {
    /**
     * 判断传过来的字符是不是数字
     * @param object
     * @return
     */
    public static boolean isNumber(Object object){
        if(object==null){
            return  false;
        }
        return object.toString().matches("^\\d+$");
    }

    public void test(){

//        1	var num=22.127456; alert( num.toFixed(2));
//
//        2	var num=22.127456; alert( Math.round(num*100)/100);
//
//        3    onload = function(){
//            var a = “23.456322″;
//            var aNew;
//            var re = /([0-9]+\.[0-9]{2})[0-9]*/;
//            aNew = a.replace(re,”$1″);
//            alert(aNew);
//        }
    }
}
