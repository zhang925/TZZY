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
 * 2016年12月9日15:04:42
 * 通讯录
 * @author zzy
 * 用户的通讯录【上限】
 */
@Entity
@Table(name = "t_txl", schema = "")
@SuppressWarnings("serial")
public class Txl implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**通讯录id*/
	private Integer id;
	/**创建人*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="createuserid")
	private User createuserid;
	
	/**修改人*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uptuserid")
	private User uptuserid;
	
	/**所属分组*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="txlgroupid")
	private TxlGroup txlgroupid;
	/**通讯人姓名*/
	private String name;
	/**邮箱*/
	private String email;
	/**电话*/
	private String phone;
	/**其他信息*/
	private String other;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCteatetime() {
		return cteatetime;
	}
	public void setCteatetime(String cteatetime) {
		this.cteatetime = cteatetime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public TxlGroup getTxlgroupid() {
		return txlgroupid;
	}
	public void setTxlgroupid(TxlGroup txlgroupid) {
		this.txlgroupid = txlgroupid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getUpttime() {
		return upttime;
	}
	public void setUpttime(String upttime) {
		this.upttime = upttime;
	}
	
	
}
