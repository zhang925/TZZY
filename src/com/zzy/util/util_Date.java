package com.zzy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Date和String转换
 * @author zzy
 *
 */
public class util_Date {
	/**把String(yyyy-MM-dd)字符转换成Date方法1*/
	public static Date strToDate1(String strdate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		java.util.Date date = null;
		try {
			date = sdf.parse(strdate);
		} catch (ParseException e) {
			System.out.println("输出时间格式不正确！");
			e.printStackTrace();
		}
		return date;
	}
	public static Date strToDate1(String strdate,String format){
		if(format==null || "".equals(format)){
			return strToDate1( strdate);
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);//小写的mm表示的是分钟
		java.util.Date date = null;
		try {
			date = sdf.parse(strdate);
		} catch (ParseException e) {
			System.out.println("输出时间格式不正确！");
			e.printStackTrace();
		}
		return date;
	}
	
	/**把String字符转换成Date方法2(已过时,能用)(yyyy-MM-dd)*/
	@SuppressWarnings("deprecation")
	public static Date strToDate2(String strdate){
		Date date=null;
		try {
			date=new Date(strdate);
		} catch (Exception e) {
			System.out.println("输入时间格式不正确！");
		}
		return date;
	}
	
	/**把Date格式转换成String方法1不设置格式(yyyy-MM-dd)*/
	public static String dateToStr1(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String str=sdf.format(date); 
		return str;
	}
	
	/**把Date格式转换成String方法2设置格式()
	 * yyyy-MM-dd
	 * yyyy-MM-dd HH:mm:ss
	 * yyyy年MM月dd日 HH时mm分ss秒
	 * */
	public static String dateToStr1(Date date,String format){
		String str="";
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			str=sdf.format(date); 
		} catch (Exception e) {
			System.out.println("输入时间格式不正确！");
		}
		return str;
	}
	
	/**
	 * 计算[上午/下午]的方法 <br/>
	 * [若果date为null则是当前时间] <br/>
	 * 上午：am <br/>
	 * 下午：pm <br/>
	 * @param date
	 * */
	public static String getMorningOrAfternoon(Date date){
		String amOrpm = "";
		GregorianCalendar ca = new GregorianCalendar();
		if(date!=null){
			long millis = date.getTime();
			ca.setTimeInMillis(millis);
		}
		int ampm = ca.get(GregorianCalendar.AM_PM);
		if(ampm == 0){
			amOrpm = "am";
		} else if(ampm == 1) {
			amOrpm = "pm";
		}
		return amOrpm;
	}
	
			/**
			 * 时间段划分划分结果为汉字
			*@param date [date为null表示当前时间]
			*/
		@SuppressWarnings("deprecation")
		public static String getTimeQuantum(Date date){
			String TimeQuantum="";
			int hours = 0;
			if(date!=null){
				hours = date.getHours();
			}else{
				Date nowdate = new Date();
				hours = nowdate.getHours();
			}
			if(hours>=3 && hours < 6){//凌晨:3:00--6:00
				TimeQuantum="凌晨";
			}else if(hours>=6 && hours < 8){//早晨:6:00---8:00
				TimeQuantum="上午";
			}else if(hours>=8 && hours < 11){//上午:8:00--11:00
				TimeQuantum="上午";
			}else if(hours>=11 && hours < 13){//中午:11:00--13:00
				TimeQuantum="中午";
			}else if(hours>=13 && hours < 17){//下午:13:00--17:00
				TimeQuantum="下午";
			}else if(hours>=17 && hours < 19){//傍晚:17:00--19:00
				TimeQuantum="傍晚";
			}else if(hours>=19 && hours < 23){//晚上:19:00--23:00
				TimeQuantum="晚上";
			}else if(hours>=0 && hours < 3){//深夜:23:00--3:00
				TimeQuantum="深夜";
			}
			return TimeQuantum;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**计算date的差值*/
	public void sss(){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate = null;
		try {
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date date1 = new Date();
		
		long  between = endDate.getTime() - date1.getTime();
		
		System.out.println(Math.abs(between/(24*3600000)));
		
		if(between > (24* 3600000)){
			
		}
	}
	
	/**
	 * 给出制定时间【date=null为当前时间】计算下[adddate]的时间
	 * @param adddate
	 * @return
	 */
	public static Date getAddDate(Date date,int adddate){
		Calendar cal = Calendar.getInstance(); 
		//获取当前日期  
		if(date == null){
			date = new Date(); 
		}
		//通过日历获取下一天日期  
		cal.setTime(date);  
        cal.add(Calendar.DAY_OF_YEAR, adddate);  
        //通过秒获取下一天日期  
        /*long time = (date.getTime() / 1000) + 60 * 60 * 24;//秒  
        date.setTime(time * 1000);//毫秒  
        */ 
		return cal.getTime();
	}
	public static String getDateYyyyMmDd(){
		Calendar now = Calendar.getInstance();  
		String datestr = now.get(Calendar.YEAR)+"-"+(now.get((Calendar.MONTH)) + 1)+"-"+now.get(Calendar.DAY_OF_MONTH);
		
        System.out.println("年: " + now.get(Calendar.YEAR));  
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));  
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));  
        System.out.println("分: " + now.get(Calendar.MINUTE));  
        System.out.println("秒: " + now.get(Calendar.SECOND));  
        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());  
        System.out.println(now.getTime());  
  
        Date d = new Date();  
        System.out.println(d);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateNowStr = sdf.format(d);  
        System.out.println("格式化后的日期：" + dateNowStr);  
          
        String str = "2012-1-13 17:26:33";  //要跟上面sdf定义的格式一样  
        Date today = null;
		try {
			today = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        System.out.println("字符串转成日期：" + today);  
    	return "";
	}
	
}
