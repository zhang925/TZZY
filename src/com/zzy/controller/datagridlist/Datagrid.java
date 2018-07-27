package com.zzy.controller.datagridlist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.model.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;

import com.zzy.dao.BaseDao;
import com.zzy.model.SysConfigure;
import com.zzy.model.district.Area;
import com.zzy.model.district.City;
import com.zzy.model.mail.Txl;
import com.zzy.model.mail.TxlGroup;
import com.zzy.service.UtilService;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * time: 2016年11月21日14:24:13 <br/>
 * datagrid Criteria 查询的 list	<br/>
 * 1. 首先引用 list 方法 [需要查询 操作 2和3]	<br/>
 * 2. serach方法增加接口判断	<br/>
 * 3. DatagridSerach 类中重写 新的查询条件	<br/>
 * @author zzy
 */
public class Datagrid {


	private static UtilService utilService;


	/**
	 * DataGrid 的通用 Criteria查询
	 * @param request
	 * @param response
	 * @param cla
	 */
	public static void list(HttpServletRequest request,HttpServletResponse response,Class cla,UtilService utilService){
		int page = utilService.getPage(1, request);
		int rows = utilService.getRows(10, request);
		Session session = utilService.gethibernatesession();
		//用来获取list[两个不能相同否则只能查出来list或者是总记录数]
		Criteria criteria = session.createCriteria(cla);
		serach(request,criteria,cla);
		//根据查询条件获取总记录数[因为这记录是需要动态获取查询条件所以不能把Class作为参数传递过去]
		Criteria criteriatotalnum = session.createCriteria(cla);
		serach(request,criteriatotalnum,cla);
		int total = utilService.gettotal(criteriatotalnum);//分页前的list获取总记录数
		List list = new ArrayList();
		criteria.setFirstResult((page-1)*rows);//开始的行号  
		criteria.setMaxResults(rows);//每页多少行
		list = criteria.list();//分页后的list
		//utilService.close(session);	spring 可以不关闭
		util_Json.jsonForEasyUI(list, total , response);
	}

	public static void list(Class cla){

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		//解决 filter 中注入  systemService 失败
		ServletContext sc = request.getSession().getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
		if(cxt != null && cxt.getBean("utilService") != null){
			utilService = (UtilService) cxt.getBean("utilService");
		}
		list( request, response, cla, utilService);
	}
	/**
	 * DataGrid 的通用 DetachedCriteria 查询
	 * @param request
	 * @param response
	 * @param cla [本实体]
	 * @param clalist [本实体关联的实体(可多个)按顺序写]
	 * @param utilService
	 */
	public static void list2(HttpServletRequest request,HttpServletResponse response,Class cla,List<Class> clalist, UtilService utilService){
		int page = utilService.getPage(1, request);
		int rows = utilService.getRows(10, request);
		Session session = utilService.gethibernatesession();
		//用来获取list[两个不能相同否则只能查出来list或者是总记录数]
		Criteria criteria = session.createCriteria(cla);
		criteria.createAlias("uid", "u");
		/*DetachedCriteria dc = DetachedCriteria.forClass(cla);
		dc.createAlias("user", "uid");*/
		
		serach(request,criteria,cla);
		//根据查询条件获取总记录数[因为这记录是需要动态获取查询条件所以不能把Class作为参数传递过去]
		Criteria criteriatotalnum = session.createCriteria(cla);
		serach(request,criteriatotalnum,cla);
		int total = utilService.gettotal(criteriatotalnum);//分页前的list获取总记录数
		List list = new ArrayList();
		criteria.setFirstResult((page-1)*rows);//开始的行号  
		criteria.setMaxResults(rows);//每页多少行
		list = criteria.list();//分页后的list
		utilService.close(session);
		util_Json.jsonForEasyUI(list, total , response);
	}
	
	
	
	
	
	/**
	 * 查询的时候添加查询条件
	 * @param request
	 * @param criteria
	 */
	public static void serach(HttpServletRequest request,Criteria criteria,Class cla){
		Object objcla;
		try {
			objcla = cla.newInstance();
			if(objcla instanceof City){
				DatagridSerach.searchCity(request, criteria);
			}else if(objcla instanceof Area){
				DatagridSerach.searchArea(request, criteria);
			}else if(objcla instanceof SysConfigure){
				DatagridSerach.searchSysConfigure(request, criteria);
			}else if(objcla instanceof Txl){
				DatagridSerach.searchTxl(request, criteria);
			}else if(objcla instanceof TxlGroup){
				DatagridSerach.searchTxlGroup(request, criteria);
			}else if(objcla instanceof Category){
				DatagridSerach.searchCategory(request, criteria);
			}

			
			
			
			
			
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
