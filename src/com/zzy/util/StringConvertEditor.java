package com.zzy.util;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * 
 * @author 
 *
 */
public class StringConvertEditor extends PropertyEditorSupport {
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.equals("")||StringUtils.hasText(text)) {
			if (text!=null) {
				setValue(HtmlEncode(text));
			} else {
				throw new IllegalArgumentException("String error");
			}
		} else {
			setValue(null);
		}
	}
	
	 public static String HtmlEncode(String str){
	        if ((str == null) || (str.equals("")))
	            return "";
	        StringBuffer buf = new StringBuffer(str.length() + 6);

	        char ch = ' ';
	        for (int i = 0; i < str.length(); ++i)
	        {
	            ch = str.charAt(i);
	            if (ch == '<')
	                buf.append("&lt;");
	            else if (ch == '>')
	                buf.append("&gt;");
	            else if (ch == '\'')
	                buf.append("&#039;");
	            else if (ch == '"')
	                buf.append("&quot;");
	            else if (ch == '&')
	                buf.append("&amp;");
	            else if (ch == '+')
	                buf.append("&#43;");
	            else
	                buf.append(ch);
	        }
	        str = buf.toString();
	        return str;
	    }
	
	 
	 	/***
		 * setValue(HtmlEncode(text)) 的过滤的 还原
		 */
		public static  String restoreHtmlEncode(String oldStr){
			if(oldStr!=null){
				oldStr = oldStr.replaceAll("&lt;", "<");
				oldStr = oldStr.replaceAll("&gt;", ">");
				oldStr = oldStr.replaceAll("&#039;", "\'");
				oldStr = oldStr.replaceAll("&quot;", "\"");
				oldStr = oldStr.replaceAll("&amp;", "&");
				oldStr = oldStr.replaceAll("&#43;", "+");
			}
			return oldStr;
		}
		
		
	public static String sqlValidate(String str) {
        String str2 = str.toLowerCase();//统一转为小写
        String[] SqlStr1 = {
        		"and","exec","execute","insert","select","delete",
        		"update","count","drop","chr","mid","master","truncate",
        		"char","declare","sitename","net user","xp_cmdshell","like",
        		"and","exec","execute","insert","create","drop","table",
        		"from","grant","use","group_concat","column_name",
        		"information_schema.columns","table_schema","union",
        		"where","select","delete","update","order","by","count",
        		"chr","mid","master","truncate","char","declare","or"};//词语
       String[] SqlStr2 = {"*","%2a","'",";","or","-","--","+","%","<",">","-","?",",",";","*/","/r/n","//","/"};//特殊字符
       for (int i = 0; i < SqlStr1.length; i++) {
            if (str2.indexOf(SqlStr1[i])>=0) {
                str = str.replaceAll("(?i)"+SqlStr1[i],"");//正则替换词语，无视大小写
            }
        }
        for (int i = 0; i < SqlStr2.length; i++) {
            if (str2.indexOf(SqlStr2[i]) >= 0) {
                str = str.replace(SqlStr2[i],"");
            }
        }
        return str;
        
    }
	    public static void main(String[] args) {
	    	String Htmlstring="select * from user where a='1' or b>'2' and  c<>'3' ";
	    	String Htmlstring3="*';or---+///%<>-?/;*//r/n*';or---+///%<>-?/;*//r/n*';or---+///%<>-?/;*//r/n*';or---+///%<>-?/;*//r/n";
	    	String Htmlstring4=" * , %2a , ' , ; , or , - , -- , + , % , < , > , - , ? , , , ; , */ , /r/n , // , /  * , %2a , ' , ; , or , - , -- , + , % , < , > , - , ? , , , ; , */ , /r/n , // , /  * , %2a , ' , ; , or , - , -- , + , % , < , > , - , ? , , , ; , */ , /r/n , // , / ";
	    	String Htmlstring2="<>\"&+";
	    	System.out.println(sqlValidate(Htmlstring));
	    	System.out.println(sqlValidate(Htmlstring3));
	    	System.out.println(sqlValidate(Htmlstring4));
	    	System.out.println(HtmlEncode(Htmlstring2));
	    	
		}
}
