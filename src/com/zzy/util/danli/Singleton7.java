package com.zzy.util.danli;

/**
 * 第七种（双重校验锁）：
 * 这个是第二种方式的升级版，俗称双重检查锁定，详细介绍请查看：
 * http://www.ibm.com/developerworks/cn/java/j-dcl.html
 * 在JDK1.5之后，双重检查锁定才能够正常达到单例效果
 * @author zzy
 *
 */
public class Singleton7 {
    private volatile static Singleton7 singleton;  
     private Singleton7 (){}   
     public static Singleton7 getSingleton() {  
     if (singleton == null) {  
         synchronized (Singleton7.class) {  
         if (singleton == null) {  
             singleton = new Singleton7();  
         }  
         }  
     }  
     return singleton;  
     }  
}
