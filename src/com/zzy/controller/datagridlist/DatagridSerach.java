package com.zzy.controller.datagridlist;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.zzy.util.util_Empty;

/**
 * 2016年11月21日14:56:19 <br/>
 * Datagrid 的 Search [具体写法]
 * @author zzy
 *
 */
public class DatagridSerach {
	/**
	 * City类 List 的查询条件
	 * @param request
	 * @param criteria
	 */
	public static void searchCity(HttpServletRequest request,Criteria criteria){
		//状态不是del的
		criteria.add(Restrictions.or(Restrictions.ne("state", "del"),Restrictions.isNull("state")));
		//城市的省份行政编码
		String provincecode = request.getParameter("provincecode");
		//城市的编码
		String code = request.getParameter("code");
		//城市的名称
		String name = request.getParameter("name");
		if(util_Empty.strEmpty(provincecode)){
			criteria.add(Restrictions.like("provincecode", "%"+provincecode+"%"));
		}
		if(util_Empty.strEmpty(code)){
			criteria.add(Restrictions.like("code", "%"+code+"%"));
		}
		if(util_Empty.strEmpty(name)){
			criteria.add(Restrictions.like("name", "%"+name+"%"));
		}
	}

	public static void searchArea(HttpServletRequest request, Criteria criteria) {
		//状态不是del的
		criteria.add(Restrictions.or(Restrictions.ne("state", "del"),Restrictions.isNull("state")));
		//城市的省份行政编码
		String citycode = request.getParameter("citycode");
		//城市的编码
		String code = request.getParameter("code");
		//城市的名称
		String name = request.getParameter("name");
		if(util_Empty.strEmpty(citycode)){
			criteria.add(Restrictions.like("citycode", "%"+citycode+"%"));
		}
		if(util_Empty.strEmpty(code)){
			criteria.add(Restrictions.like("code", "%"+code+"%"));
		}
		if(util_Empty.strEmpty(name)){
			criteria.add(Restrictions.like("name", "%"+name+"%"));
		}
		
	}
	
	public static void searchSysConfigure(HttpServletRequest request, Criteria criteria) {
		//状态不是del的
		criteria.add(Restrictions.or(Restrictions.ne("state", "del"),Restrictions.isNull("state")));
		/*
		DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Beauty.class, "b").;//加载某个类取名为b
		DetachedCriteria customerCriteria = beautyCriteria.createAlias("customers", c");//取别名为c"
		*/		
		String name = request.getParameter("name");
		if(util_Empty.strEmpty(name)){
			criteria.add(Restrictions.like("name", "%"+name+"%"));
		}
		String uname = request.getParameter("uid");//这里获取的是用户的名字
		if(util_Empty.strEmpty(uname)){
			criteria.createAlias("createuser", "u");
			criteria.add(Restrictions.like("u.name", "%"+uname+"%"));
		}
		
	}
	
	
	public static void searchTxl(HttpServletRequest request, Criteria criteria) {
		//状态不是del的
		criteria.add(Restrictions.or(Restrictions.ne("state", "del"),Restrictions.isNull("state")));
		//String citycode = request.getParameter("citycode");
	}
	
	public static void searchTxlGroup(HttpServletRequest request, Criteria criteria) {
		//状态不是del的
		criteria.add(Restrictions.or(Restrictions.ne("state", "del"),Restrictions.isNull("state")));
		//String citycode = request.getParameter("citycode");
	}

	public static void searchCategory(HttpServletRequest request, Criteria criteria) {
		//状态不是del的
		//criteria.add(Restrictions.or(Restrictions.ne("state", "del"),Restrictions.isNull("state")));
		//String citycode = request.getParameter("citycode");
	}

	
	
	
}
