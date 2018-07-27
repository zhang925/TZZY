package com.zzy.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
/**
 * 分类 实体
 */
@Entity
@Table(name = "t_category", schema = "")
@DynamicUpdate(true)// 在插入和修改数据的时候,语句中只包括要插入或者修改的字段。
@DynamicInsert(true)
// @Column 推荐放到 get方法上不建议放到属性上，无效率快慢问题
// 放到属性上，属性中含有 _ hql 语句会过滤掉，这样导致报错
public class Category implements Serializable{


	private java.lang.String id;

	private User user;

	/** 类型名称 */
	private java.lang.String name;

	/** 类型编码 */
	private java.lang.String code;

	/** 等级 */
	private java.lang.String level;

	/** 排序 */
	private java.lang.Double orders;


	/** 上级 */
	private Category parent;

	/** 下级分类 */

	private List<Category> children;


	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**创建人实体*/
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", nullable = true, length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", nullable = true, length = 32)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "level", nullable = true, length = 32)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_code",referencedColumnName = "code")
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent")
	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	@Column(name = "orders", nullable = true, length = 32)
	public Double getOrders() {
		return orders;
	}

	public void setOrders(Double orders) {
		this.orders = orders;
	}
}
