package com.zzy.model.mail;

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

import com.zzy.model.User;
/**
 * 2016年12月9日15:05:15
 * 邮件[一封发的邮件可能对应对个人]
 * 2016年12月9日15:04:34
 * @author zzy
 *
 */
@Entity
@Table(name = "t_zmail", schema = "")
@SuppressWarnings("serial")
public class Zmail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	/**创建人*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uid")
	private User uid;
	/**标题*/
	private String title;
	/**内容*/
	private String content;
	/**创建时间*/
	private String cteatetime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUid() {
		return uid;
	}
	public void setUid(User uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCteatetime() {
		return cteatetime;
	}
	public void setCteatetime(String cteatetime) {
		this.cteatetime = cteatetime;
	}
	
	
	
}
