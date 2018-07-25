package com.zzy.controller.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 2017年1月16日17:00:20
 * xml配置见applicationContext-timeTask.xml的方法一
 * @author zzy
 *
 */
public class Job1 extends QuartzJobBean {  
	private int timeout;  
	private static int i = 0;  
	//调度工厂实例化后，经过timeout时间开始执行调度  
	public void setTimeout(int timeout) {  
		this.timeout = timeout;  
	}  
	/**要调度的具体任务 */  
	@Override  
	protected void executeInternal(JobExecutionContext context)  
	throws JobExecutionException {  
		Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println(sdf.format(date)+"：开始执行任务!");
        
        System.out.println("继承QuartzJobBean方式-调度进行中...");  
        
        Date date2 = new Date();
        System.out.println(sdf.format(date2)+"：执行任务完毕!");
	}  
}  