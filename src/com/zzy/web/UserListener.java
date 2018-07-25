package com.zzy.web;

import javax.servlet.http.HttpSessionBindingEvent;

import com.zzy.model.UserInfo;



/**
 * 用于chatroom
 * 
 * @author Administrator
 * 
 */
public class UserListener implements
		javax.servlet.http.HttpSessionBindingListener {

	private String user;
	private UserInfo container = UserInfo.getInstance(); // 获得UserInfo类的对象

	public UserListener() {
		user = "";
	}

	// 设置在线监听人员
	public void setUser(String user) {
		this.user = user;
	}

	// 获取在线监听
	public String getUser() {
		return this.user;
	}

	/*
	 * 当Session有对象加入时执行的方法
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet
	 * .http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("上线用户：" + this.user);

	}

	/*
	 * 当Session有对象移除时执行的方法
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet
	 * .http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent arg0) {

		System.out.println("下线用户：" + this.user);
		if (user != "") {
			container.removeUser(user);
		}
	}
}
