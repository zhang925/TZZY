package com.zzy.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class WebConstants {

	
	public static final String PAGE_LOGIN="login";
	
	public static final String PAGE_LOGIN_UIA="login_uia";
	
	public static final String EXCEPTION_MESSAGE="exceptionError";
	
	
	public static final String STRUTS_ACTION_METHOD="method";
	
	public static final String PAGECOUNT="page_count";
	
	
	public static final String RESULT_MODEL="model";
	
	public static final String RESULT_LIST="list";
	
	public static final String JSON_STATE="state";
	
	public static final String JSON_SUCCESS="1";
	
	public static final String JSON_EXCEPTION="0";
	
	public static final String MESSAGE="message";
	
	public static final String FILE_PATHS="paths";
	
	public static final String FILES="files";
	
	public static final String FILE_DEFAULT_TYPE_XLS=".xls";
	
	
	public static final String INDEX="index";
	public static final String MAIN="main";
	public static final String PAGE="page";
	public static final String EDIT="edit";
	public static final String EDIT_IN="edit_in";
	public static final String ADD="add";
	public static final String MODIFY="modify";
	
	public static final String PAGE_DOWNLOAD="downLoadPage";
	public static final String VIEW="viewHTML";
	public static final String IN_SIMPLE="in_simple";
	
	
	public static final String PROGRESS_SUBMIT="提交申请";
	public static final String PROGRESS_TRIAL="初审";
	public static final String PROGRESS_REVIEW="专家评审";
	public static final String PROGRESS_POSTPROJECT="结项";
	public static final String PROGRESS_UPLOAD="上传标准文件";
	
	
	public static final String DICT_POSITION="position";//职称
	public static final String DICT_MAJOR="major";//专业
	

	
	//验证手机
	public static boolean isMobileNum(String telNum){
		//Pattern pattern = Pattern.compile("[0-9]*");
		//Matcher m = pattern.matcher(telNum);
		if(telNum.length()>11){
			return false;
		}
		/*if(!m.matches()){
			return false;
		}*/
		return true;
	}
	
	/**
	 * 验证固话
	 * @param fixedPhone
	 * @return
	 */
	public static boolean isFixedPhone(String fixedPhone){
		//Pattern pattern = Pattern.compile("[0-9]*");
		//Matcher m = pattern.matcher(fixedPhone);
		if(fixedPhone.length()>13){
			return false;
		}
		/*if(!m.matches()){
			return false;
		}*/
		return true;
		
	}
	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
	}
	
	
	/**  
     * 我国公民的身份证号码特点如下
     * 1.长度18位
     * 2.第1-17号只能为数字
     * 3.第18位只能是数字或者x
     * 4.第7-14位表示特有人的年月日信息
     * 请实现身份证号码合法性判断的函数，函数返回值：
     * 1.如果身份证合法返回0
     * 2.如果身份证长度不合法返回1
     * 3.如果第1-17位含有非数字的字符返回2
     * 4.如果第18位不是数字也不是x返回3
     * 5.如果身份证号的出生日期非法返回4
     * @since 0.0.1
     */
    public static int isIdCard(String id) {
        String str = "[1-9]{2}[0-9]{4}(19|20)[0-9]{2}"
                + "((0[1-9]{1})|(1[1-2]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1}|(3[0-1]{1})))"
                + "[0-9]{3}[0-9x]{1}";
        Pattern pattern = Pattern.compile(str);
        return pattern.matcher(id).matches() ? 0 : 1;
    }
    
    /**
      * 获取字符串拼音的第一个字母
      * @param chinese
      * @return
      */
     public static String ToFirstChar(String chinese){         
         String pinyinStr = ""; 
         String to = chinese.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+","");
         char[] newChar = to.toCharArray();  //转为单个字符
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
         for (int i = 0; i < newChar.length; i++) {  
             if (newChar[i] > 128) {  
                 try {  
                     pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);  
                 } catch (BadHanyuPinyinOutputFormatCombination e) {  
                     e.printStackTrace();  
                 }  
             }else{  
                 pinyinStr += newChar[i];  
             }  
         }  
         return pinyinStr;  
     }  
 
}
