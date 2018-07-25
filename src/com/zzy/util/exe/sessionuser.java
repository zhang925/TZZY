package com.zzy.util.exe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.model.User;

/**
 * 对session的处理
 * @author zzy
 */
public class sessionuser {
	
	/**
	 * 为了防止session冲突，一个浏览器等两个账户，
	 * 把用户的session进行处理
	 */
	public void setusersession(User user, HttpServletRequest request){
		request.getSession().setAttribute("user"+user.getUid(), user);
	}
	
	/**
	 * 获取用户usersession
	 * @param request
	 * @return
	 */
	public User getusersession(User user, HttpServletRequest request){
		//这里出现了问题不能用。。。。。。。。。。。。
		return (User)request.getSession().getAttribute("user"+user.getUid());
	}
}
