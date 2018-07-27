package com.zzy.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zzy.dao.BaseDao;
import com.zzy.model.BornInfo;
import com.zzy.model.SysConfigure;
import com.zzy.model.User;
import com.zzy.model.district.City;
import com.zzy.service.UtilService;
import com.zzy.util.util_Empty;

@Transactional
@Service()
public class UtilServiceImpl implements UtilService {
	private BaseDao<BornInfo> basedao;
	
	public BaseDao<BornInfo> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<BornInfo> basedao) {
		this.basedao = basedao;
	}
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	public Session getSession() {  
        //事务必须是开启的，否则获取不到  
        return sessionFactory.getCurrentSession();  
    }  
	
	public String getSysID() {
		Session session = this.getCurrentSession();
		//Transaction tx = session.beginTransaction();//只是查询系统ID
		String sysid = session.createSQLQuery(" SELECT REPLACE(UUID(),'-','')  FROM DUAL; ").uniqueResult().toString();
		//tx.commit();
		//session.close();关闭session就不能获取到ID
		return sysid;
	}

	public Session gethibernatesession() {
		return getSessionFactory().openSession();
	}

	public Integer getPage(int initpage, HttpServletRequest request) {
		String p = request.getParameter("page");
		if(initpage<=0){
			initpage =1;
		}
		if(util_Empty.strEmpty(p)){
			initpage = Integer.valueOf(p);
		}
		return initpage;
	}

	public Integer getRows(int initrows, HttpServletRequest request) {
		String r = request.getParameter("rows");
		if(initrows<=0){
			initrows = 10;
		}
		if(util_Empty.strEmpty(r)){
			initrows = Integer.valueOf(r);
		}
		return initrows;
	}

	@SuppressWarnings("rawtypes")
	public int gettotal(List list) {
		int size = 0;
		if(util_Empty.listEmpty(list)){
			size = list.size();
		}
		return size;
	}

	public void close(Session session) {
		if(session!=null){
			session.flush();
			session.close();
		}
		
	}

	public int gettotal(Criteria criteria) {
		int total = 0;
		if(criteria!=null){
			criteria.setProjection(Projections.rowCount());
			total = Integer.valueOf(criteria.uniqueResult().toString());
		}
		return total;
	}

	public User getLoginUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("user");
	}

	public List<Object[]> getListBySql(String sql) {
		Session session = this.getCurrentSession();
		List<Object[]> list = session.createSQLQuery(sql).list();
		return list;
	}

	public void executeSql(String sql) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);    
        query.executeUpdate();
	}

	public String getSysConfigureByName(String name) {
		String sql = "select value  from t_sysconfigure where name='"+name+"'";
		Session session = this.getCurrentSession();
		List list = session.createSQLQuery(sql).list();
		String str = "";
		if(list!=null && list.size()>0){
			str = list.get(0).toString();
		}
		return str;
	}

}
