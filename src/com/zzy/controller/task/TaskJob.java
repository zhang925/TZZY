package com.zzy.controller.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;  
@Service("taskJob")
public class TaskJob {  
    public void job1() {  
    	Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println(sdf.format(date)+"：开始执行任务!");
        System.out.println("Spring task定时任务调度进行中...");  
        Date date2 = new Date();
        System.out.println(sdf.format(date2)+"：执行任务完毕!"); 
    }  

	
    public void ssss() {  
    	Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println(sdf.format(date)+"：开始执行任务!");
        System.out.println("Spring task 普通pojo方式-调度进行中...");  
       
        Date date2 = new Date();
        System.out.println(sdf.format(date2)+"：执行任务完毕!"); 
    } 
	
}  