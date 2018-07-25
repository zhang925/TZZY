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
 * 2016年12月9日15:04:57
 * 通讯录分组
 * @author zzy
 * 通讯录分组
 */
@Entity
@Table(name = "t_txlgroup", schema = "")
@SuppressWarnings("serial")
public class TxlGroup implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**通讯录分组id*/
	private Integer id;
	/**通讯录分组名称*/
	private String name;
	/**其他*/
	private String other;
	/**创建人*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="createuserid")
	private User createuserid;
	/**修改人*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uptuserid")
	private User uptuserid;
	/**创建时间*/
	private String cteatetime;
	/**修改时间*/
	private String upttime;
	/**状态*/
	private String state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public User getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(User createuserid) {
		this.createuserid = createuserid;
	}
	public User getUptuserid() {
		return uptuserid;
	}
	public void setUptuserid(User uptuserid) {
		this.uptuserid = uptuserid;
	}
	public String getCteatetime() {
		return cteatetime;
	}
	public void setCteatetime(String cteatetime) {
		this.cteatetime = cteatetime;
	}
	public String getUpttime() {
		return upttime;
	}
	public void setUpttime(String upttime) {
		this.upttime = upttime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
