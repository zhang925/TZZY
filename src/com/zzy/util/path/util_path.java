package com.zzy.util.path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;

public class util_path {
	/**
	 * 
	 * 运行时候的项目路径类似：
	 *  http://localhost:8099/TZZY/
	 */
	public static  String  getrealPath(HttpServletRequest request){
		String paths = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + paths + "/";
		return basePath;
	}
	
	/***
	 * 获取服务器下项目的路径类似：
	 * D:\worksoft\tocatexe\webapps\TZZY\
	 * 一般是jsp等外层路径
	 * @param request
	 * @return
	 */
	public static  String  getTomcatPath(HttpServletRequest request){
		String realPath = request.getSession().getServletContext().getRealPath("/");
		return realPath;
	}
	
	
	/**
	 * 获取桌面路径类似：
	 *  C:\Users\zzy\Desktop
	 * @param request
	 */
	public static String getDeskTopPath(){
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		return desktopPath;
	}
	
	
	/**
	 * 获取classes路径类似：
	 * 一般是java代码目录路径
	 *  D:/worksoft/tocatexe/webapps/TZZY/WEB-INF/classes/
	 *  用服务器项目路径拼接也可以实现，方法[getTomcatPath]
	 *  
	 * @param request
	 */
	public static String getClassesPath(){
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		return desktopPath;
	}
	
	
	
	public void getPath(HttpServletRequest request) {
		
		String paths = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + paths + "/";
		System.out.println("第一个路径：" + basePath);

		// D:\worksoft\tocatexe\webapps\TZZY\
		String realPath = request.getSession().getServletContext().getRealPath(
				"/");
		System.out.println("第二个路径：" + realPath);

		// C:\Users\zzy\Desktop
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		System.out.println("桌面路径：" + desktopPath);

		// D:/worksoft/tocatexe/webapps/TZZY/WEB-INF/classes/
		String basePath03 = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		System.out.println("第六个路径：" + basePath03);
	}
	
	
	
	/**
	 * 根据 Properties 配置文件的		键	读取	值
	 * 项目是启动的 
	 * @param String Profilename 配置文件的名字
	 * @param String proPath 配置文件的地址 	null 和 "" 认为是src 目录下
	 * @param String strkey 要获取值的    键
	 * @return String 
	 */
	public static String getPripertyPath(String Profilename,String proPath,String strkey){
		//获取word配置文件   
		  Properties p=new Properties();    
		 InputStream is;
		 String basePath = "";
		//这样也能取到scr的目录
		 //String basePath = request.getSession().getServletContext().getRealPath("/"); +/WEB-INF/classes/
		 if(proPath==null || "".equals(proPath)){
			 basePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		 }else{
			 basePath = proPath;
		 }
			try {
				//获取陪着文件
				is = new FileInputStream(basePath+"/"+Profilename);
				p.load(is);  
				is.close(); 
			} catch (FileNotFoundException e) {
				System.out.println("未找到"+basePath+"目录下的"+Profilename+"配置文件");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO异常");
				e.printStackTrace();
			}
			 String value =  p.getProperty(strkey);
			return  value;
	}
	
	
}
