package com.zzy.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzy.model.district.District;
import com.zzy.service.UtilService;

@Controller
@RequestMapping(value = "/userController")
public class utilController {
	@Autowired
	private UtilService utilService;
	
	/**
	 * hibernate 的 criteria 查询 [Query] 查询 
	 * 
	 * in  的 解决办法
	 */
	public void testIN查询(){
		Session session = utilService.gethibernatesession();
		String hql="from District where id in (:idlist)"; 
		Query query = session.createQuery(hql);
		Object a[] ={1,2,3};
		query.setParameterList("idlist", a); 
		List<District> list = new ArrayList<District>();
		list = query.list();
		session.flush();
		session.close();
	}
}
