package com.zzy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user", schema = "")
@SuppressWarnings("serial")
/**用户信息类*/
public class User implements Serializable{
	/**1 表序号*/
	 //@Id
	 //@GeneratedValue(strategy=GenerationType.IDENTITY)
	 //@Column(name = "id", nullable = false)
	//private int id;
	/**2 用户ID*/
	@Id
	@Column(name = "uid", nullable = false)
	private String uid;
	/**3 登录名*/
	private String username;
	/**4 密码*/
	private String password;
	/**5 密码MD5*/
	private String passwordmd5;
	/**6 姓名*/
	private String name;
	/**7 性别*/
	private String sex;
	/**8 出生时间*/
	private String borntime;
	/**9 手机*/
	private String phone;
	/**10 QQ*/
	private String qq;
	/**11 微信*/
	private String weixin;
	/**12 微博*/
	private String weibo;
	/**13 邮箱*/
	private String email;
	/**14 头像ID*/
	private String photoid;
	/**15 创建时间*/
	private String createtime;
	/**16 状态*/
	private String state;
	/**17登录 状态*/
	private String loginstate;
	/**获取实体类ID*/
	/*public int getId() {
		return id;
	}*/
	/**设置实体类ID*/
	/*public void setId(int id) {
		this.id = id;
	}*/
	/**获取用户ID*/
	public String getUid() {
		return uid;
	}
	/**设置用户ID*/
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**获取用户注册名称*/
	public String getUsername() {
		return username;
	}
	/**设置用户注册名称*/
	public void setUsername(String username) {
		this.username = username;
	}
	/**获取用户密码*/
	public String getPassword() {
		return password;
	}
	/**设置用户密码*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**获取用户密码MD5*/
	public String getPasswordmd5() {
		return passwordmd5;
	}
	/**设置用户密码MD5*/
	public void setPasswordmd5(String passwordmd5) {
		this.passwordmd5 = passwordmd5;
	}
	/**获取用户姓名*/
	public String getName() {
		return name;
	}
	/**设置用户姓名*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**获取性别*/
	public String getSex() {
		return sex;
	}
	/**设置性别*/
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**获取出生日期*/
	public String getBorntime() {
		return borntime;
	}
	/**设置出生日期*/
	public void setBorntime(String borntime) {
		this.borntime = borntime;
	}
	/**获取手机号*/
	public String getPhone() {
		return phone;
	}
	/**设置手机号*/
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**获取QQ号*/
	public String getQq() {
		return qq;
	}
	/**设置QQ号*/
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**获取微信号*/
	public String getWeixin() {
		return weixin;
	}
	/**设置微信号*/
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	/**获取微博号*/
	public String getWeibo() {
		return weibo;
	}
	/**设置微博号*/
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	/**获取邮箱*/
	public String getEmail() {
		return email;
	}
	/**设置邮箱*/
	public void setEmail(String email) {
		this.email = email;
	}
	/**获取头像图片ID*/
	public String getPhotoid() {
		return photoid;
	}
	/**设置头像图片ID*/
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}
	/**获取创建时间*/
	public String getCreatetime() {
		return createtime;
	}
	/**设置创建时间*/
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**获取状态*/
	public String getState() {
		return state;
	}
	/**设置状态*/
	public void setState(String state) {
		this.state = state;
	}
	/**获取登录状态*/
	public String getLoginstate() {
		return loginstate;
	}
	/**设置登录状态*/
	public void setLoginstate(String loginstate) {
		this.loginstate = loginstate;
	}
	
	
}
