package com.zzy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_nenu", schema = "")
@SuppressWarnings("serial")
/**
 * 菜单 实体
 */
public class Menu implements Serializable{
	private java.lang.String id;
	/**创建人实体*/
	private User user;
	/**唯一标示可与id相同  这里为了和前端保持一致才做的修改 */
	private java.lang.String menuid;
	/**菜单名字 */
	private java.lang.String menuname;
	/**  类型 */
	private java.lang.String menutype;
	/**  等级 */
	private java.lang.String menulevel;
	/**菜单图标 */
	private java.lang.String icon;
	/** 菜单地址 */
	private java.lang.String url;
	/** 上级菜单 */
	private Menu  parent;
	/** 下级菜单 */
	private List<Menu> child;
	/** 排序 order 是数据库关键字，这里加个s*/
	private java.lang.Double orders;
	/**创建时间*/
	private java.util.Date createtime;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "menuid", nullable = true)
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	@Column(name = "menuname", nullable = true)
	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	@Column(name = "menutype", nullable = true)
	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	@Column(name = "menulevel", nullable = true)
	public String getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(String menulevel) {
		this.menulevel = menulevel;
	}

	@Column(name = "icon", nullable = true)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "url", nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid",referencedColumnName = "id")
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent")
	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	@Column(name = "orders", nullable = true)
	public Double getOrders() {
		return orders;
	}

	public void setOrders(Double orders) {
		this.orders = orders;
	}

	@Column(name = "createtime", nullable = true)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
