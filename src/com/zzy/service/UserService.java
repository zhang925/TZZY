package com.zzy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zzy.model.User;

/**
 * 用户接口
 * @author zzy
 *
 */
public interface UserService {
	
	/**
	 * 根据HQL和条件
	 * 获取User实体
	 * @param hql
	 * @param param
	 * @return
	 */
	public User getgetUserModel(String hql,Object param[]);
	
	/**根据用户名和密码获取用户实体
	 * @param username
	 * @param password
	 * @return User
	 */
	public User getUserForLogin(String username,String password);
	/**
	 * 根据序号获取用户实体
	 * @param id
	 * @return User
	 */
	//public User getUserByID(Integer id);
	/**
	 * 根据UID获取用户实体
	 * @param uid
	 * @return User
	 */
	public User getUserByUID(String uid);
	/**获取用户信息
	 * 可带 可不带 条件
	 * @param username
	 * @param password
	 * @return List<User>
	 */
	public List<User> getAllUser(String hql,Object param[]);
	/**
	 * 分页获取 用户 列表
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return List<User>
	 */
	public List<User> getUserPage(String hql,Object param[],int page,int rows);
	
	/**
	 * 获取List的条数
	 * "select count(*) from User where ... "
	 * 
	 */
	public int getTotalNum(String hql,Object param[]);

	/**
	 * 添加新用户
	 * 1成功2失败
	 * @param user
	 * @return int
	 */
	public int saveUser(User user);
	/**
	 * 根据Uid删除用户
	 * 1成功2失败
	 * @param uid
	 * @return int
	 */
	public int delUser(String uid);
	/**
	 * 更新
	 * 1成功2失败
	 * @param user
	 * @return int
	 */
	public int updateUser(User user);
	/**
	 * 保存或者更新
	 * 1成功2失败
	 * @param user
	 * @return int
	 */
	public int saveOrUpdateUser(User user);
	
	/**
	 * 
	 */
	public int gettotal(String sql);
	/**
	 * 支持sql查询
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> gettotal2(String sql);
	 
}
