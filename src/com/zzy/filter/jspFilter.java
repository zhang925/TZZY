package com.zzy.filter;  
  
import java.io.IOException;  
  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import com.zzy.model.User;
  
/** 
 * 不允许直接访问jsp 
 * 所有对jsp的直接访问，跳转到首页面 
 * @author zzy 
 * 
 */  
public class jspFilter implements Filter {  
	ServletContext context;
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
       
    	
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
       HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
       String url = httpServletRequest.getRequestURI();
       HttpSession session = httpServletRequest.getSession();
       
        User p = new User();
        p = (User) session.getAttribute("user");
        if((url.endsWith(".do") || url.endsWith(".jsp")) 
        		&& (!url.endsWith("index.jsp")
        		&& !url.endsWith("login.jsp")
        		&& !url.endsWith("login.do")
        		&& !url.endsWith("test.do") 
        		&& !url.endsWith("forgetPwd.jsp")
        		&& !url.endsWith("user_zc.jsp")
        		//这里暂时设置只要与test有关的都不过滤
        		&& !url.contains("test")
        		&& !url.contains("Test")
        		
        		&& !url.contains("userController")
        		
        		
        		&& !url.endsWith("error.jsp")) ){
          if(p==null || p.getUid()==null){
		    	String paths = httpServletRequest.getContextPath();
				String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ paths + "/";
		    	httpServletResponse.sendRedirect(basePath+"index.jsp");
		    	
		    }
        }
        
        chain.doFilter(request, response); 
        
        
        
    }

	public void destroy() {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		context  = filterConfig.getServletContext();
	}  
  
  
}   