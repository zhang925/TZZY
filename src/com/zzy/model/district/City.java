package com.zzy.model.district;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_city", schema = "")
@SuppressWarnings("serial")
/**
 * 2016年11月03日
 * @author zzy
 * 时间
 */
public class City implements Serializable {
	/**ID*/
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**市区编码*/
	private String code;
	/**市区名字*/
	private String name;
	/**所属省编码*/
	private String provincecode;
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
	/**获取市区编码*/
	public String getCode() {
		return code;
	}
	/**设置市区编码*/
	public void setCode(String code) {
		this.code = code;
	}
	/**获取市区名称*/
	public String getName() {
		return name;
	}
	/**设置市区名称*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取所属省编码*/
	public String getProvincecode() {
		return provincecode;
	}
	/**设置所属省编码*/
	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
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
