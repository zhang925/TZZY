package com.zzy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.zzy.model.User;
import com.zzy.model.district.City;

/**
 * 工具 service 一般的Controller通用 方法
 * @author zzy
 *
 */
public interface UtilService {
	/**
	 * 获取MySQL系统自动生成的ID
	 * (32位 String 类型)
	 * @return String
	 */
	public String getSysID();
	/**
	 * 用 DetachedCriteria  criteria 或者 Query 查询 <br/>
	 * 获取 hibernate的sesison<br/>
	 * 这种查询比较安全 
	 *	
	 */
	public Session gethibernatesession();
	
	/**
	 * 分页,获取页数[可初始化条数,默认1]
	 * 
	 */
	public Integer getPage(int initpage,HttpServletRequest request);
	/**
	 * 分页,获取每页显示条数[可初始化条数,默认10]
	 */
	public Integer getRows(int initrows,HttpServletRequest request);
	/**
	 * 获取list的大小
	 * @param list
	 * @return
	 */
	public int gettotal(List list);
	/**
	 * 刷新并且关闭HibernateSession
	 * Spring框架自带关闭功能，可以不关闭
	 */
	public void close(Session session);
	/**
	 * 通过Criteria 获取总记录数
	 */
	public int gettotal(Criteria criteria);
	/**
	 * 获取当前登录人[实体]
	 */
	public User getLoginUser(HttpServletRequest request);
	/**
	 * sql查询返回List<Object[]>
	 * 每一个Object[]代表一条数据
	 */
	public List<Object[]>  getListBySql(String sql);
	/**
	 * sql 执行语句无返回值
	 */
	public void executeSql(String sql);
	/**
	 * 通过配置名称获取配置值
	 * @return String 配置值
	 */
	public String getSysConfigureByName(String name);
}
