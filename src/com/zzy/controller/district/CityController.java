package com.zzy.controller.district;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzy.controller.datagridlist.Datagrid;
import com.zzy.dao.BaseDao;
import com.zzy.model.*;
import com.zzy.model.district.City;
import com.zzy.model.district.District;
import com.zzy.service.CityService;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.page.PageZzy;


@Controller
@RequestMapping(value = "/cityController")
public class CityController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private CityService cityService;
	@Autowired
	private UtilService utilService;
	
	/**
	 * 查看City列表
	 * @return
	 */
	@RequestMapping(value="list.do")
	public void list(HttpServletRequest request,HttpServletResponse response){
		//调用DataGrid通用查询方法[需要查询条件-->在类Serach中写查询条件即可]
		Datagrid.list(request,response,City.class,utilService);
	}
	 
	
	/**
	 * 添加[load=add]
	 * 修改[load=upt]
	 * 实体*/
	@RequestMapping(value="save.do")
	public void save(City city,String load,String cityid,HttpServletRequest request,HttpServletResponse response){
		int i=2;
		if("add".equals(load)){//添加
			i=cityService.saveCity(city);
		}else if("upt".equals(load)){//修改
			i=cityService.updateCity(city);
		}
		util_Json.jsonPrintln(i+"", response);
	}
	/**
	 * 查看[load=see]、修改[load=upt]、添加[load=add]的页面跳转
	 */
	@RequestMapping(value="goAddOrUptPage.do")
	public String goAddOrUptPage(String id,HttpServletRequest request,HttpServletResponse response){
		if(util_Empty.strEmpty(id)){
			City city=cityService.getCityModel(Integer.valueOf(id));
			request.setAttribute("city", city);
		}
		request.setAttribute("load", request.getParameter("load"));
		return "/webjsp/districtjsp/city_addorupt.jsp";
	}
	
	/**根据id删除City*/
	@RequestMapping(value="del.do")
	public void del(String ids,String msg,HttpServletRequest request,HttpServletResponse response){
		//[msg=single单个删除][msg=more多个删除]
		//[message=fail删除失败][message=success删除成功]
		String message = "fail";
		int i = 2;
		if("single".equals(msg)){
			City city = cityService.getCityModel(Integer.valueOf(ids));
			city.setState("del");
			i = cityService.updateCity(city);
		}else if("more".equals(msg)){
			for(String id : ids.split(",")){
				City city = cityService.getCityModel(Integer.valueOf(id));
				city.setState("del");
				i=cityService.updateCity(city);
				if(i==2){//删除出现异常
					return ; //停止删除
				}
			}
		}
		if(i==1){
			message = "success";
		}
		Map<String,String> m = new HashMap<String,String>();
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}
	
	
	/**
	 * 导入经典语录
	 */
	@RequestMapping(value="/importCity.do")
	public void importCity(){
		System.out.println(111);
	}
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/exportCity.do")
	public void exportCity(HttpServletRequest request,HttpServletResponse response){
		/*String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from City where 1=1 ";
		Object param[] = new Object[]{};
		if(util_Empty.strEmpty(uid)){
			User u = userService.getUserByUID(uid);
			hql = hql + " and uid=? ";
			param = new Object[]{u};
		}
		if(util_Empty.strEmpty(name)){
			hql = hql + " and name like '%"+name+"%'";
		}
		if(util_Empty.strEmpty(createtime)){
			hql = hql + " and createtime like '%"+createtime+"%'";
		}
		hql=hql+" order by id  desc";
		List<City> list = CityService.getAllCity(hql, param);
		Export.export("经典语录记录","经典语录记录",City.class, list,  response);*/
		
	}
}
