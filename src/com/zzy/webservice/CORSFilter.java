package com.zzy.webservice;
import com.alibaba.fastjson.JSONObject;
import com.zzy.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//import com.baomidou.kisso.SSOConfig;

/**
 * 本拦截器只对webservice 有效 即 wwww.aaa.com/api/**的访问拦截
 * 本来打算做个需要拦截就加注解的方式
 * 但是发现几乎所有的请求都需要拦截，
 * 因此这里采用系统白名单的方式，进行免拦截
 * @Time 2018年6月
 * @Author zzy
 */
//@Controller
public class CORSFilter implements Filter{

    @Autowired
    private SystemService systemService;

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req ;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE, PUT");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Headers","Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");
        response.setHeader("Access-Control-Expose-Headers", "*");
        //response.setDateHeader("expries", -1);
        //response.setHeader("Cache-Control", "no-cache");
        //response.setHeader("Pragma", "no-cache");
        if(isAuthorization(request,response)){
            chain.doFilter(req, resp);
        }else{
            response.setCharacterEncoding("utf8");
            JSONObject obj = new JSONObject();
            obj.put("code","403");
            obj.put("msg", "no_power");
            response.getWriter().println(obj.toJSONString());
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }


    /**
     * 在前端的时候有时候不需要登陆就要拿到一些数据
     *  true 表示不需要拦截，false 需要拦截
     * @return
     */
    public boolean isHandle(HttpServletRequest request){
        String requestUrl = request.getRequestURI();//获取当前请求的url
        //requestUrl = requestUrl.replace("/","");
        boolean flag = false;
        //解决 filter 中注入  systemService 失败
        ServletContext sc = request.getSession().getServletContext();
        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
        if(cxt != null && cxt.getBean("systemService") != null){
            systemService = (SystemService) cxt.getBean("systemService");
        }
        if(systemService!=null){
            //从数据库读取白名单
            List<Object[]> list = systemService.getBySql(" select url from sso_white  ");
            if(list!=null && list.size()>0){
                for(Object  object : list){
                    if(requestUrl.contains(object.toString())){
                        flag = true;
                        break;
                    }

                }
            }
        }
        return flag;//不需要拦截
    }



    public boolean isAuthorization(HttpServletRequest request, HttpServletResponse response){
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       /* if(isHandle( request)){//是白名单
            return true;
        }else{
            *//**token 进行验证 *//*
            String token = request.getHeader("Authorization");
            if(token!=null){
                Map<String,Claim> map = JwtToken.verifyToken(token);
                if(map!=null){//有效的token
                    return true;
                }
            }
            return false;
        }*/
        return true;
        // 一下可以进行 cookie tooken 验证
        /*
        String authorization = request.getHeader("Authorization");//token
        //"SESSIONID=947CDE0762299E1241430790C588A7F3; aaa=aaa"
        String SESSIONID  = "";
        if (authorization != null && !"".equals(authorization)) { // cookie 里面是不允许 ; 存在的，所以这里可以放心截取
            String cookies[] = authorization.split(";");
            for (String cookie : cookies){
                String cooki[] = cookie.split("=");
                if(cooki!=null && cooki.length>1){//说明是正常的cookie
                    if(cooki[0].trim().equals("SESSIONID")){
                        SESSIONID = cooki[1];
                    }
                }
            }
        }
        if("".equals(SESSIONID)){
            return false;
        }
        MySessionContext mySessionContext = MySessionContext.getSingleInstance();
        HttpSession session = mySessionContext.getSession(SESSIONID);
        if(session!=null){//说明sesssion没有注销
            Object obj = session.getAttribute("restuser");//这个是webservice用户
            Object obj2 = session.getAttribute("LOCAL_CLINET_USER");//这个是系统用户，两者是同一个用户
            if(obj==null){
                return false;
            }
           *//* TSUser tsUser = (TSUser)obj;
            if(tsUser==null || tsUser.getId()==null){
                return false;
            }else{
                return true;//从以前的登陆信息中获取登陆后的用户
            }*//*
           return true;
        }
        return false;*/
    }
}
