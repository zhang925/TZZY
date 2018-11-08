package com.zzy.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class util_properties {

	
	/**
	 * 根据 Properties 配置文件的		键	读取	值
	 * @param String fileName 配置文件的名字
	 * @param String filePath 配置文件的地址 	null 和 "" 认为是src 目录下
	 * @param String propertyName 要获取值的    键
	 * @return String 
	 */
	public static String getPropertyValueByName(String fileName,String filePath,String propertyName){
		//获取word配置文件   
		  Properties p=new Properties();    
		 InputStream is;
		 String basePath = "";
		//这样也能取到scr的目录
		 //String basePath = request.getSession().getServletContext().getRealPath("/"); +/WEB-INF/classes/
		 if(filePath==null || "".equals(filePath)){
			 if(Thread.currentThread().getContextClassLoader().getResource("/")!=null){
				 basePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath(); 
			 }else{
				 // 说明没有启动项目测试
				 String localPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
				 //这里拼接 一个src 目录下的地址 根据需要自己 拼接
				 basePath = localPath.split("/")[1]+"/"+localPath.split("/")[2]+"/"+localPath.split("/")[3]+"/src";
				 System.out.println(basePath);
				 // 不拼接的话 配置文件 要放到 项目的 根目录 不是src下 而是 src 同级 
				 //readPath = fileName; 
			 }
		 }else{
			 basePath = filePath;
		 }
			try {
				//获取陪着文件
				is = new FileInputStream(basePath+"/"+fileName);
				p.load(is);  
				is.close(); 
			} catch (FileNotFoundException e) {
				System.out.println("未找到"+basePath+"目录下的"+fileName+"配置文件");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO异常");
				e.printStackTrace();
			}
			String value =  p.getProperty(propertyName);
			return  value;
	}
	
	public static void main(String[] args) {
		
		String appKey = getPropertyValueByName( "easemob.properties", "", "AppKey");
		System.out.println(appKey);
	}

}
