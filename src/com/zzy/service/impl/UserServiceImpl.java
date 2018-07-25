package com.zzy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.User;
import com.zzy.service.UserService;
/**
 * 用户接口的实现
 * @author zzy
 *
 */
@Transactional
//springMVC和hibernate结合必须加上不然会报
//No Session found for current thread 异常
public class UserServiceImpl implements UserService{
	
	private BaseDao<User> basedao;
	public BaseDao<User> getBasedao() {
		return basedao;
	}
	public void setBasedao(BaseDao<User> basedao) {
		this.basedao = basedao;
	}
	public List<User> getAllUser(String hql,Object param[]) {
		return basedao.find(hql,param );
	}
	public User getUserForLogin(String username, String password) {
		return basedao.get("from User where username=? and password=? ", new Object[]{username,password});
	}
//	public User getUserByID(Integer id) {
//		return basedao.get(User.class, id);
//	}
	public User getUserByUID(String uid) {
		return basedao.get("from User where uid=? ", new Object[]{uid});
	}
	public int getTotalNum( String hql ,Object[] param) {
		//从list的size获取总条数的方法不好 数据多了		容易宕机 
		long k = basedao.count(hql,param);
		return (int) k;
	}
	public List<User> getUserPage(String hql, Object[] param, int page, int rows) {
		return basedao.find(hql, param, page, rows);
	}
	public int delUser(String uid) {
		int i = 1;
		try {
			User o = new User();
			o=this.getUserByUID(uid);
			basedao.delete(o);
		} catch (Exception e) {
			i = 2;
		}
		return i;
	}
	public int saveOrUpdateUser(User user) {
		int i = 1;
		try {
			basedao.saveOrUpdate(user);
		} catch (Exception e) {
			i = 2;
		}
		return i;
	}
	public int saveUser(User user) {
		int i = 1;
		try {
			basedao.save(user);
		} catch (Exception e) {
			i = 2;
		}
		return i;
	}
	public int updateUser(User user) {
		int i = 1;
		try {
			basedao.update(user);
		} catch (Exception e) {
			i = 2;
		}
		return i;
	}
	public User getgetUserModel(String hql, Object[] param) {
		return basedao.get(hql, param);
	}
	public int gettotal(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<Map<String, Object>> gettotal2(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
