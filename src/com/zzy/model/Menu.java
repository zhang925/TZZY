package com.zzy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_nenu", schema = "")
@SuppressWarnings("serial")
/**
 * 菜单 实体
 */
public class Menu implements Serializable{
	/**ID*/
	@Id  
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	/**创建人实体*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uid")
	private User uid;
	/**创建人姓名*/
	private String createusername;

	
}
