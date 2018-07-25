package com.zzy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
/**
 * 中文转换为汉字简拼（拼音首字母）
 * @author long
 *
 */
public class util_Pinyin {
       
	 // 判断字符串是否包含有中文   
	    public static boolean isChinese(String str) {    
	        String regex = "[\\u4e00-\\u9fa5]";    
	        Pattern pattern = Pattern.compile(regex);    
	        Matcher matcher = pattern.matcher(str);    
	        return matcher.find();    
	    }    
	 
    /**  
     * 汉字转换位汉语拼音，英文字符不变  
     * @param chines 汉字  
     * @return 拼音  
     */     
	    public static String getPinYinHeadChar(String str)
	    {
	       String convert = "";
	       for (int j = 0; j < str.length(); j++)
	       {
	        char word = str.charAt(j);
	        // 提取汉字的首字母
	        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
	        if (pinyinArray != null)
	        {
	         convert += pinyinArray[0].charAt(0);
	        }
	        else
	        {
	         convert += word;
	        }
	       }
	       return convert;
	    }
    
	/*测试入口主方�?
    public static void main(String[] args) throws Exception {  
        System.out.print(getPinYinHeadChar("招商银行--ZS"));
    }  */
}
