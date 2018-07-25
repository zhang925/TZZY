package com.zzy.util.TimerDask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器的设计
 * @author zzy
 *
 */
public class util_TimerDask {
	
	/**
     * 每天 hh mm ss 执行
     */
    public  void startTaskEveryDay(){
    	 /**设置每天[0点]执行*/
   	 	 int hh=0, mm=0, ss=0;
    	 //下一次执行的时间间隔为一天
    	 final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    	 Calendar calendar = Calendar.getInstance();  
         calendar.set(Calendar.HOUR_OF_DAY, hh); 
         calendar.set(Calendar.MINUTE, mm); 
         calendar.set(Calendar.SECOND, ss);  
         Date date=calendar.getTime(); //第一次执行定时任务的时间  
         //如果第一次执行定时任务的时间 小于当前的时间  
         //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。  
         if (date.before(new Date())) {
             Calendar startDT = Calendar.getInstance();  
             startDT.setTime(date); 
             startDT.add(Calendar.DAY_OF_MONTH, 1);//加一天
             date = startDT.getTime(); 
         }  
         Timer timer = new Timer();  
         TimerTask task = new TimerTask() {
             public void run() {
             	/********************这里执行要执行的代码********************/
            	
             }
         };
         //安排指定的任务在指定的时间开始进行重复的固定延迟执行。  
         timer.schedule(task,date,PERIOD_DAY); 
    }
    
    
    /**
     * 制定某个时间执行【一次】
     */
    public static  void startTaskOnce() {
    	/**设置执行的时间**/
    	Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
    	try {
			date = sdf.parse("2016-12-29 15:01:30");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        final Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	/********************这里执行要执行的代码********************/
            	//执行任务
        		Date date = new Date();
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
                System.out.println(sdf.format(date));
                
              //执行一次则停止任务
            	t.cancel();
            }
        };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        t.schedule(task, calendar.getTime());
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
    
    public static void main(String args[]){
    	startTaskOnce();
    }
}
