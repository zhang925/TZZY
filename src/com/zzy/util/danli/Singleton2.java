package com.zzy.util.danli;
/**
 * 第二种（懒汉，线程安全）：
 * @author zzy
 * 
 * 这种写法能够在多线程中很好的工作，
 * 而且看起来它也具备很好的lazy loading，
 * 但是，遗憾的是，效率很低，99%情况下不需要同步。
 * [synchronized]
 */
public class Singleton2 {  
	
	
	      private static Singleton2 instance;
	      
	      
	      private Singleton2 (){}
	      
	      public static synchronized Singleton2 getInstance() { 
	    	  
	      if (instance == null) {  
	    	  instance = new Singleton2();  
	       }  
	      return instance;  
	      }  
	 }  