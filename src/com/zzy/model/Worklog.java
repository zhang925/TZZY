package com.zzy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "t_worklog", schema = "")
@SuppressWarnings("serial")
/**
 * 记事本实体
 */
public class Worklog implements Serializable{
	/**ID*/
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**创建人实体*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uid")
	private User uid;
	/**创建人姓名*/
	private String createusername;
	/**记事本title*/
	private String title;
	/**记事本content*/
	private String content;
	/**创建时间*/
	private String createtime;
	/**修改时间*/
	private String uptime;
	/**获取记事本ID*/
	public Integer getId() {
		return id;
	}
	/**设置记事本ID*/
	public void setId(Integer id) {
		this.id = id;
	}
	/**获取User实体*/
	public User getUid() {
		return uid;
	}
	/**设置User实体*/
	public void setUid(User uid) {
		this.uid = uid;
	}
	/**获取创建人姓名*/
	public String getCreateusername() {
		return createusername;
	}
	/**设置创建人姓名*/
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}
	/**获取标题*/
	public String getTitle() {
		return title;
	}
	/**设置标题*/
	public void setTitle(String title) {
		this.title = title;
	}
	/**获取内容*/
	public String getContent() {
		return content;
	}
	/**设置内容*/
	public void setContent(String content) {
		this.content = content;
	}
	
	/**获取创建时间*/
	public String getCreatetime() {
		return createtime;
	}
	/**设置创建时间*/
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**获取修改时间*/
	public String getUptime() {
		return uptime;
	}
	/**设置修改时间*/
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	
	
}
