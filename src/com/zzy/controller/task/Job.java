package com.zzy.controller.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 2016年12月12日14:00:07
 * 定时器方法三-2的执行类
 * @author zzy
 *
 */
@Component
public class Job {
 
    /**
     * 每天0点执行一次[cron顺序:秒-分-小时]
     * */
    @Scheduled(cron="0 0 0 * * ?") 
    public void executeEveryDayAt0h(){
    	Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println(sdf.format(date)+"：开始执行任务!");
        System.out.println("执行中");
        Date date2 = new Date();
        System.out.println(sdf.format(date2)+"：执行任务完毕!");
    }
    
    
    /**
     * 每隔4秒执行一次
     * */
  public static  int i = 1;
    //@Scheduled(cron="*/4 * * * * ?")
   public void execute2sOnce(){
        //这里写要执行的代码
       Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        System.out.println(sdf.format(date)+"：执行第"+i+"次");
        i++;
    }
    
}