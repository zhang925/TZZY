package com.zzy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 生日表
 * @author zzy
 *
 */
@Entity
@Table(name = "t_borninfo", schema = "")
@SuppressWarnings("serial")
public class BornInfo implements Serializable{
	@Id  
	@Column(name = "id", nullable = false)
	/**String类型ID但是还是数字*/
	private String id;
	/**姓名*/
	private String name;
	/**QQ号*/
	private String QQ;
	/**手机号*/
	private String phone;
	/**出生日期(阳历)*/
	private String ylborntime;
	/**出生日期(阴历)*/
	private String nlborntime;
	/**年龄*/
	private String age;
	/**其他说明*/
	private String other;
	/**分类根据QQ分类*/
	private String type;
	/**级别1-10数字越大越优先*/
	private String level;
	/**创建时间*/
	private String createtime;
	/**最后一次修改时间*/
	private String lastuptime;
	/**状态*/
	private String state;
	
	/**获取生日ID*/
	public String getId() {
		return id;
	}
	/**设置生日ID*/
	public void setId(String id) {
		this.id = id;
	}
	/**获取姓名*/
	public String getName() {
		return name;
	}
	/**设置姓名*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取QQ号*/
	public String getQQ() {
		return QQ;
	}
	/**设置手机号*/
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	/**获取手机号*/
	public String getPhone() {
		return phone;
	}
	/**设置手机号*/
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**获取阳历出生日期*/
	public String getYlborntime() {
		return ylborntime;
	}
	/**设置阳历出生日期*/
	public void setYlborntime(String ylborntime) {
		this.ylborntime = ylborntime;
	}
	/**获取农历出生日期*/
	public String getNlborntime() {
		return nlborntime;
	}
	/**设置农历出生日期*/
	public void setNlborntime(String nlborntime) {
		this.nlborntime = nlborntime;
	}
	/**获取年龄*/
	public String getAge() {
		return age;
	}
	/**设置年龄*/
	public void setAge(String age) {
		this.age = age;
	}
	/**获取其他说明*/
	public String getOther() {
		return other;
	}
	/**设置其他说明*/
	public void setOther(String other) {
		this.other = other;
	}
	/**获取分类*/
	public String getType() {
		return type;
	}
	/**设置分类*/
	public void setType(String type) {
		this.type = type;
	}
	/**获取级别*/
	public String getLevel() {
		return level;
	}
	/**设置级别*/
	public void setLevel(String level) {
		this.level = level;
	}
	/**获取状态*/
	public String getState() {
		return state;
	}
	/**设置状态*/
	public void setState(String state) {
		this.state = state;
	}
	/**获取创建时间*/
	public String getCreatetime() {
		return createtime;
	}
	/**设置创建时间*/
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**获取最后一次修改时间*/
	public String getLastuptime() {
		return lastuptime;
	}
	/**设置最后一次修改时间*/
	public void setLastuptime(String lastuptime) {
		this.lastuptime = lastuptime;
	}
	
}
