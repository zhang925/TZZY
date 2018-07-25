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
 * 
 * @author zzy
 *
 */
@Entity
@Table(name = "t_zmailrelationship", schema = "")
@SuppressWarnings("serial")
public class ZmailRelationShip  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="zmailid")
	/**邮件*/
	private Zmail zmailid;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uid")
	/**收件人*/
	private User uid;
	/**是否已读[no未读yes已读]*/
	private String readstate;
	/**创建时间*/
	private String cteatetime;
	/**阅读时间*/
	private String readtime;
	/**状态*/
	private String state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Zmail getZmailid() {
		return zmailid;
	}
	public void setZmailid(Zmail zmailid) {
		this.zmailid = zmailid;
	}
	public User getUid() {
		return uid;
	}
	public void setUid(User uid) {
		this.uid = uid;
	}
	public String getReadstate() {
		return readstate;
	}
	public void setReadstate(String readstate) {
		this.readstate = readstate;
	}
	public String getCteatetime() {
		return cteatetime;
	}
	public void setCteatetime(String cteatetime) {
		this.cteatetime = cteatetime;
	}
	public String getReadtime() {
		return readtime;
	}
	public void setReadtime(String readtime) {
		this.readtime = readtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
