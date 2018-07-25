package com.zzy.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class util_loginInfo {
	
	public void getloginInfo(HttpServletRequest request, HttpServletResponse response){
		//获取浏览器信息
		Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = (String)e.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " ： " + value);
        } 
        request.getHeaders("User-Agent");//浏览器 
		//host ： localhost:8099
		//connection ： keep-alive
		//cache-control ： max-age=0
		//upgrade-insecure-requests ： 1
		//user-agent ： Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.3 Safari/537.36
		//accept ： text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		//accept-encoding ： gzip, deflate, sdch, br
		//accept-language ： zh-CN,zh;q=0.8
        //获取操作系统信息
        String osName = System.getProperty("os.name" );
		String osVersion=System.getProperty("os.version");
		//登录人的IP
		request.getRemoteAddr();
	}
	
	//获得客户端真实IP地址的方法一：
    public String getRemortIP(HttpServletRequest request) {  
        if (request.getHeader("x-forwarded-for") == null) {  
            return request.getRemoteAddr();  
        }  
        return request.getHeader("x-forwarded-for");  
    }  
    
    
	//获得客户端真实IP地址的方法二：
	 public static String getIP(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for");
	        if (!checkIP(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (!checkIP(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (!checkIP(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        return ip;
	    }
	    private static boolean checkIP(String ip) {
	        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
	                || ip.split(".").length != 4) {
	            return false;
	        }
	        return true;
	    }
	    
	    
	    
	    
	    
	    /** 
	     * 获取访问用户的客户端IP（适用于公网与局域网）. 
	     */  
	    public static final String getIpAddr(final HttpServletRequest request)  
	            throws Exception {  
	        if (request == null) {  
	            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));  
	        }  
	        String ipString = request.getHeader("x-forwarded-for");
	        if(ipString!=null){
	        	if ("unknown".equalsIgnoreCase(ipString)) {  //StringUtils.isBlank(ipString)
		            ipString = request.getHeader("Proxy-Client-IP");  
		        }  
		        if ("unknown".equalsIgnoreCase(ipString)) {  
		            ipString = request.getHeader("WL-Proxy-Client-IP");  
		        }  
		        if ("unknown".equalsIgnoreCase(ipString)) {  
		            ipString = request.getRemoteAddr();  
		        }  
		        // 多个路由时，取第一个非unknown的ip  
		        final String[] arr = ipString.split(",");  
		        for (final String str : arr) {  
		            if (str!=null && !"unknown".equalsIgnoreCase(str)) {  
		                ipString = str;  
		                break;  
		            }  
		        }
		        
	        }
	        
	       
	        return ipString;  
	    }  


	    public String getIpAddrsss(HttpServletRequest request) {      
	           String ip = request.getHeader("x-forwarded-for");      
	          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	              ip = request.getHeader("Proxy-Client-IP");      
	          }      
	          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	              ip = request.getHeader("WL-Proxy-Client-IP");      
	           }      
	         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	               ip = request.getRemoteAddr();      
	          }      
	         return ip;      
	    }
	    
	    
}
