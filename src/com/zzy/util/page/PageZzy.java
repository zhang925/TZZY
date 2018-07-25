package com.zzy.util.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具类
 * @author zzy
 *
 */
public class PageZzy {
	
	/**分页的list*/
	private List list;
	/**当前页*/
	private int page=1;
	/**每页条数*/
	private int rows=10;
	/**总记录数*/
	private int total;
	/**总页数*/
	private int totalpage;
	/**是否为第一页*/
	private boolean isfirspage;
	/**是否为最后第一页*/
	private boolean islastpage;
	/**是否有下一页*/
	private boolean haslastpage;
	/**是否有上一页*/
	private boolean hasprepage;
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalpage() {
		return this.total % this.rows == 0 ? this.total / this.rows :  this.total / this.rows + 1;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public boolean isIsfirspage() {
		return isfirspage;
	}
	public void setIsfirspage(boolean isfirspage) {
		this.isfirspage = isfirspage;
	}
	public boolean isIslastpage() {
		return islastpage;
	}
	public void setIslastpage(boolean islastpage) {
		this.islastpage = islastpage;
	}
	public boolean isHaslastpage() {
		return haslastpage;
	}
	public void setHaslastpage(boolean haslastpage) {
		this.haslastpage = haslastpage;
	}
	public boolean isHasprepage() {
		return hasprepage;
	}
	public void setHasprepage(boolean hasprepage) {
		this.hasprepage = hasprepage;
	}
	
	
	
	
	
	
}
