package com.zzy.util.TimerDask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TestTimer {  
    public static void main(String[] args) {  
    	/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
    	//要取到今天的[24点]时间
        String now2400 = sdf.format(new Date());*/
        
    	Timer04();
    }  
    
    /** 
     * 普通thread 
     * 这是最常见的，创建一个thread，然后让它在while循环里一直运行着， 
     * 通过sleep方法来达到定时任务的效果。这样可以快速简单的实现，代码如下： 
     * @author GT 
     * 
     */  
    public static void Timer01() {  
        final long timeInterval = 1000; //[单位毫秒] 
        Runnable runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                	//执行任务
                	Date date = new Date();
                	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
                    System.out.println(sdf.format(date));
                    
                	try {  
                        Thread.sleep(timeInterval);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        };  
        Thread thread = new Thread(runnable);  
        thread.start();  
    }  
    
    
    /** 
     *  
     * 于第一种方式相比，优势 1>当启动和去取消任务时可以控制 2>第一次执行任务时可以指定你想要的delay时间 
     *  
     * 在实现时，Timer类可以调度任务，TimerTask则是通过在run()方法里实现具体任务。 Timer实例可以调度多任务，它是线程安全的。 
     * 当Timer的构造器被调用时，它创建了一个线程，这个线程可以用来调度任务。 下面是代码： 
     *  
     * @author GT 
     *  
     */  
        public static  void Timer02() {  
            TimerTask task = new TimerTask() {  
                public void run() {  
                	//执行任务
                	Date date = new Date();
                	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
                    System.out.println(sdf.format(date));
                }  
            };  
            Timer timer = new Timer();  
            long delay = 0; //第一次多少时间执行 
            long intevalPeriod = 1*1000;//以后多少时间执行 单位 毫秒
            timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
        } 
     
    
    
    /** 
     * ScheduledExecutorService是从Java SE5的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式。  
     * 相比于上两个方法，它有以下好处： 
     * 1>相比于Timer的单线程，它是通过线程池的方式来执行任务的  
     * 2>可以很灵活的去设定第一次执行任务delay时间 
     * 3>提供了良好的约定，以便设定执行的时间间隔 
     * 下面是实现代码，我们通过ScheduledExecutorService#scheduleAtFixedRate展示这个例子，通过代码里参数的控制，首次执行加了delay时间。 
     */  
   
    public static  void Timer03(){  
        Runnable runnable = new Runnable() {  
        	public void run() {  
            	//执行任务
        		Date date = new Date();
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
                System.out.println(sdf.format(date));
            }  
        };  
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  [ 单位 TimeUnit.可设置]
        service.scheduleAtFixedRate(runnable, 5, 2, TimeUnit.SECONDS);  
       }  
   
    /**
     * 指定某个时间执行
     */
    public static  void Timer04() {
        final Timer t = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task is processing.");// 此处可以插入自己想运行的代码片段
                t.cancel();//停止任务
            }
        };
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
    	try {
			date = sdf.parse("2016-12-05 16:27:50");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	calendar.setTime(date);
        t.schedule(task, calendar.getTime());
        
    }
    
    
    
    
    static int count = 0;
    public static void Timer05() {  
        TimerTask task = new TimerTask() {  
            @Override  
            public void run() {  
                ++count;  
                System.out.println("执行了-->" + count+"次");
                Date date = new Date();
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
                System.out.println(sdf.format(date));
            }  
        };  
    	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
    	Calendar calendar = Calendar.getInstance();  
        int year = calendar.get(Calendar.YEAR);  
        int month = calendar.get(Calendar.MONTH) + 1;  
        int day = calendar.get(Calendar.DAY_OF_MONTH);  
        System.out.println(year+"--"+month+"--"+day);
        /*** 定制每日00：24：00执行方法 ***/  
        calendar.set(year, month, day, 14, 52, 40);
        Date date = calendar.getTime();  
        Timer timer = new Timer();  
        timer.schedule(task, date);  
    }  
    
    public static void Timer06() { 
    	
    }
    
    
}  