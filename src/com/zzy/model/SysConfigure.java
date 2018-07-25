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

/**
 * 系统配置表
 * @author zzy
 *
 */
@Entity
@Table(name = "t_sysconfigure", schema = "")
@SuppressWarnings("serial")
public class SysConfigure implements Serializable{
	/*Id
	Column(name = "id", nullable = false)
		这种写法不会自动生成ID
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/*
	1) IDENTITY：表自增键字段，Oracle不支持这种方式； 
	2) AUTO： JPA自动选择合适的策略，是默认选项； 
	3) SEQUENCE：通过序列产生主键，通过@SequenceGenerator注解指定序列名，MySql不支持这种方式； 
	4) TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
	*/
	/**id*/
	private int id;
	/**创建人*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="createuser")
	private User createuser;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="updateuser")
	/**修改人*/
	private User updateuser;
	/**配置名称[英文字符]*/
	private String name;
	/**配置值*/
	private String value;
	/**配置说明*/
	private String statement;
	/**创建时间*/
	private String createtime;
	/**修改时间*/
	private String updatetime;
	/**状态*/
	private String state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getCreateuser() {
		return createuser;
	}
	public void setCreateuser(User createuser) {
		this.createuser = createuser;
	}
	public User getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(User updateuser) {
		this.updateuser = updateuser;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
