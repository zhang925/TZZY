package com.zzy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "T_FILES", schema = "")
@SuppressWarnings("serial")
/**文件信息类*/
public class Files implements Serializable {
	
	/**序号
	private Integer id;*/
	/**文件的StringID */
	/*@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	@Id
	@Column(name = "fid", nullable = false)
	public String fid;
	/**上传人实体*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uid")
	private User uid;
	/**文件名*/
	private String filename; 
	/**文件大小*/
	private String filesize;
	/**文件类型*/
	private String filetype;
	/**文件地址*/
	private String filesrc;
	/**创建时间*/
	private String createtime;
	/**状态*/
	private String state;
	/**获取ID
	public Integer getId() {
		return id;
	}
	*设置ID
	public void setId(Integer id) {
		this.id = id;
	}*/
	/**获取文件FID*/
	public String getFid() {
		return fid;
	}
	/**设置FID*/
	public void setFid(String fid) {
		this.fid = fid;
	}
	
	/**获取文件名字*/
	public String getFilename() {
		return filename;
	}
	/**设置文件名字*/
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**获取文件大小*/
	public String getFilesize() {
		return filesize;
	}
	/**设置文件大小*/
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	/**获取文件类型*/
	public String getFiletype() {
		return filetype;
	}
	/**设置文件类型*/
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	/**获取文件地址*/
	public String getFilesrc() {
		return filesrc;
	}
	/**设置文件地址*/
	public void setFilesrc(String filesrc) {
		this.filesrc = filesrc;
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
	
	
	public User getUid() {
		return uid;
	}
	public void setUid(User uid) {
		this.uid = uid;
	}
	
}
