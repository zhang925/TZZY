package com.zzy.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzy.dao.BaseDao;
import com.zzy.model.Worklog;
import com.zzy.service.WorklogService;

/**记事本服务接口的实现*/
@Transactional
public class WorklogServiceImpl implements WorklogService{
	private BaseDao<Worklog> basedao;
	
	public BaseDao<Worklog> getBasedao() {
		return basedao;
	}
	
	public void setBasedao(BaseDao<Worklog> basedao) {
		this.basedao = basedao;
	}
	/**根据实体ID删除Worklog*/
	public Integer delWorklogByID(Integer id) {
		Integer i=1;
		Worklog o = new Worklog();
		try {
			o=getWorklogModel(id);
			basedao.delete(o);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！删除失败！");
		}
		return i;
	}
	/**根据ID获取Worklog*/
	public Worklog getWorklogModel(Integer id) {
		String hql="from Worklog where id=?";
		Object []param=new Object[]{id};
		Worklog o = basedao.get(hql, param);
		return o;
	}

	/**根据实体保存Worklog*/
	public Integer saveWorklog(Worklog worklog) {
		Integer i=1;
		try {
			basedao.save(worklog);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！保存失败！");
		}
		return i;
	}
	/**根据实体更新Worklog*/
	public Integer updateWorklog(Worklog worklog) {
		Integer i=1;
		try {
			basedao.update(worklog);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	
	public Integer saveorupWorklog(Worklog worklog) {
		Integer i=1;
		try {
			basedao.saveOrUpdate(worklog);
		} catch (Exception e) {
			i=2;
			System.out.println("异常！更新失败！");
		}
		return i;
	}
	/**根据hql获取List<Worklog>*/
	public List<Worklog> getAllWorklog(String hql, Object[] param) {
		return basedao.find(hql,param);
	}
	/**根据hql和条件分页获取List<Worklog>*/
	public List<Worklog> getWorklogPage(String hql,Object[] param, Integer page, Integer rows) {
		return basedao.find(hql, param, page, rows);
	}
	/**根据查询条件获取全部信息的条数*/
	public Integer getTotalNum(String hql, Object[] param) {
		long k = basedao.count(hql,param);
		return (int) k;
	}
	/**根据查询条件获取全部信息的页数*/
	public Integer getWorklogTotalPage(Integer num,Integer rows) {
		int totalpage=1;
		if(num >= rows){
			if(num % rows == 0){
				totalpage = num / rows;
			}else{
				totalpage = (num / rows)+1;
			}
		}
		return totalpage;
	}

}
