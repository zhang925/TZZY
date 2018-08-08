package com.zzy.webservice;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionListener { // implements HttpSessionListener
    public static Map userMap = new HashMap();
    private MySessionContext mys = MySessionContext.getSingleInstance();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        mys.AddSession(httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        mys.DelSession(session);
    }
}