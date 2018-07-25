package com.zzy.mp3;
public class ChStr {
/***************************************************
 *功能：解决输出中文乱码问题，返回值为String
***************************************************/
        public String chStr(String str){
           if(str==null){
                str="";
           }else{
                try{
str=(new String(str.getBytes("iso-8859-1"),"GB2312")).trim();
              }catch(Exception e){
                   e.printStackTrace(System.err);
              }
           }
           return str;
         }
/***************************************************
 *功能：显示文本中的回车换行、空格，返回值为String
***************************************************/
       public String convertStr(String str1){
           if(str1==null){
            str1="";
          }else{
            try{

              str1=str1.replaceAll(" ","&nbsp;");
              str1=str1.replaceAll("\r\n","<br>");
            }catch(Exception e){
                   e.printStackTrace(System.err);
            }
         }
         return str1;
     }
}

