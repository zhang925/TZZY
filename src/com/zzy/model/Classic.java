package com.zzy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zzy.model.User;

/**
 * 经典语录
 * @author zzy
 *
 */
@Entity
@Table(name = "t_classic", schema = "")
@SuppressWarnings("serial")
public class Classic implements Serializable {
	/**ID*/
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uid")
	/**创建人*/
	private User uid;
	/**标题*/
	private String title;
	/**内容*/
	private String content;
	/**级别*/
	private String level;
	/**创建时间*/
	private String createtime;
	/**状态*/
	private String state;
	/**获取ID*/
	public Integer getId() {
		return id;
	}
	/**设置ID*/
	public void setId(Integer id) {
		this.id = id;
	}
	/**获取创建人*/
	public User getUid() {
		return uid;
	}
	/**设置创建人*/
	public void setUid(User uid) {
		this.uid = uid;
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
	/**获取级别*/
	public String getLevel() {
		return level;
	}
	/**设置级别*/
	public void setLevel(String level) {
		this.level = level;
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
	
}
