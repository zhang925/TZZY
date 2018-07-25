package com.zzy.controller.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2016年12月12日14:25:41 
 * @author zzy
 *
 */
public class Job2 {
	
	public void doJob2() {
		Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println(sdf.format(date)+"：开始执行任务!");
        System.out.println("不继承QuartzJobBean方式-调度进行中...");  
       
        Date date2 = new Date();
        System.out.println(sdf.format(date2)+"：执行任务完毕!");
		
	}  
	

		 
}
