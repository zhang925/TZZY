package com.zzy.model.district;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_province", schema = "")
@SuppressWarnings("serial")
/**
 * 2016年11月03日
 * @author zzy
 *
 */
public class Province implements Serializable {
	/**ID*/
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**省份编码*/
	private String code;
	/**省份名字*/
	private String name;
	
	
	/**获取ID*/
	public Integer getId() {
		return id;
	}
	/**设置ID*/
	public void setId(Integer id) {
		this.id = id;
	}
	/**获取省份编码*/
	public String getCode() {
		return code;
	}
	/**设置省份编码*/
	public void setCode(String code) {
		this.code = code;
	}
	/**获取省份名称*/
	public String getName() {
		return name;
	}
	/**设置省份名称*/
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
