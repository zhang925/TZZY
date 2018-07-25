package com.zzy.model;

import java.util.Vector;

/**
 * @author Administrator
 * 在线聊天用
 */
public class UserInfo {

	private static UserInfo user = new UserInfo();

	@SuppressWarnings("rawtypes")
	private Vector vector = null;

	// 利用private调用构造函数，防止被外界产生新的instance对象
	@SuppressWarnings("rawtypes")
	private UserInfo() {
		this.vector = new Vector();
	}

	// 外界使用的instance对象
	public static UserInfo getInstance() {
		return user;
	}

	// 增加用户
	@SuppressWarnings("unchecked")
	public boolean addUser(String user) {
		if (user != null) {
			this.vector.add(user);
			return true;
		} else {
			return false;
		}
	}

	// 获取用户列表
	@SuppressWarnings("rawtypes")
	public Vector getList() {
		return vector;
	}

	// 移除用户
	public void removeUser(String user) {
		if (user != null) {
			vector.removeElement(user);
		}
	}
}
