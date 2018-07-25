package com.zzy.model.district;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**国内地区汇总*/
@Entity
@Table(name = "t_district", schema = "")
@SuppressWarnings("serial")
/**
 * 2016年11月07日
 * @author zzy
 *
 */
public class District implements Serializable {
	/**ID*/
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**行政编码*/
	private String xzid;
	/**地区名*/
	private String name;
	/**所属上一级地区行政编码*/
	private String parentid;
	/**地区名简称*/
	private String shotname;
	/**级别*/
	private String leveltype;
	/**地区区号*/
	private String zipcode;
	/**地区邮政编码*/
	private String citycode;
	/**地区全称包括省市区[县]*/
	private String mergername;
	/**经度[东经]*/
	private String lng;
	/**纬度[北纬]*/
	private String lat;
	/**地区拼音*/
	private String pinyin;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/**行政编码*/
	public String getXzid() {
		return xzid;
	}
	/**行政编码*/
	public void setXzid(String xzid) {
		this.xzid = xzid;
	}
	/**地区名*/
	public String getName() {
		return name;
	}
	/**地区名*/
	public void setName(String name) {
		this.name = name;
	}
	/**所属上一级地区行政编码*/
	public String getParentid() {
		return parentid;
	}
	/**所属上一级地区行政编码*/
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**地区名简称*/
	public String getShotname() {
		return shotname;
	}
	/**地区名简称*/
	public void setShotname(String shotname) {
		this.shotname = shotname;
	}
	/**级别*/
	public String getLeveltype() {
		return leveltype;
	}
	/**级别*/
	public void setLeveltype(String leveltype) {
		this.leveltype = leveltype;
	}
	/**地区区号*/
	public String getZipcode() {
		return zipcode;
	}
	/**地区区号*/
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**地区邮政编码*/
	public String getCitycode() {
		return citycode;
	}
	/**地区邮政编码*/
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	/**地区全称包括省市区[县]*/
	public String getMergername() {
		return mergername;
	}
	/**地区全称包括省市区[县]*/
	public void setMergername(String mergername) {
		this.mergername = mergername;
	}
	/**经度*/
	public String getLng() {
		return lng;
	}
	/**经度*/
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**纬度*/
	public String getLat() {
		return lat;
	}
	/**纬度*/
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**拼音*/
	public String getPinyin() {
		return pinyin;
	}
	/**拼音*/
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
}
