package com.zzy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 测试类
 * @author zzy
 */
public class test {
    public static void main(String[] args){
    	String strdate = "2016-11-29 10:10:10";
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//小写的mm表示的是分钟  
		java.util.Date date = null;
		try {
			date = sdf.parse(strdate);
		} catch (ParseException e) {
			System.out.println("输出时间格式不正确！");
			e.printStackTrace();
		}
		
		
		
		String amOrpm = "";
		GregorianCalendar ca = new GregorianCalendar();  
		long millis = date.getTime();
		ca.setTimeInMillis(millis);
		int ampm = ca.get(GregorianCalendar.AM_PM);
		if(ampm == 0){
			amOrpm = "am";
		} else if(ampm == 1) {
			amOrpm = "pm";
		}
		System.out.println(amOrpm);
		
    	
    }
}  