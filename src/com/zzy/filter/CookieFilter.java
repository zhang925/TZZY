package com.zzy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFilter implements Filter {  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse resp = (HttpServletResponse) response;  
  
        Cookie[] cookies = req.getCookies();  
        if(cookies != null){
        Cookie cookie = cookies[0];
     // 判断对象是否存在null的情况 
        if(checkObjIsNull(response) || checkObjIsNull(cookie)){ 
        return; 
        } 
        //依次取得cookie中的名称、值、最大生存时间、路径、域和是否为安全协议信息 
        String cookieName = cookie.getName(); 
        String cookieValue = cookie.getValue(); 
        int maxAge = cookie.getMaxAge(); 
        String path = cookie.getPath(); 
        String domain = cookie.getDomain(); 
        boolean isSecure = cookie.getSecure(); 
        
            StringBuffer strBufferCookie = new StringBuffer(); 
            strBufferCookie.append(cookieName + "=" + cookieValue +  ";"); 
             
            if(maxAge >= 0){ 
                strBufferCookie.append("Max-Age=" + cookie.getMaxAge() + ";"); 
            } 
             
            if(!checkObjIsNull(domain)){ 
            strBufferCookie.append("domain=" + domain + ";"); 
            } 
             
            if(!checkObjIsNull(path)){ 
            strBufferCookie.append("path=" + path + ";"); 
            } 
             
            if(isSecure){ 
            strBufferCookie.append("secure;HTTPOnly;"); 
            }else{ 
            strBufferCookie.append("HTTPOnly;"); 
            } 
            resp.addHeader("Set-Cookie",strBufferCookie.toString()); 
        }
            chain.doFilter(req, resp);  
    }  
  
    public void destroy() {  
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
    }  
    
    private static boolean checkObjIsNull(Object obj){ 
        if(obj == null){ 
        return true; 
        } 
        
        return false; 
    }   
    
    
    
    
    
    /**
     * <filter>
		<filter-name>cookieFilter</filter-name>
		<filter-class>rsda.util.CookieFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>cookieFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping> 
     * */
    
    
    
    
}  
