package com.zzy.controller.district;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzy.model.*;
import com.zzy.model.district.Province;
import com.zzy.service.ProvinceService;
import com.zzy.service.UserService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.page.PageZzy;


@Controller
@RequestMapping(value = "/provinceController")
public class ProvinceController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private ProvinceService provinceService;
	
	
	/**
	 * 查看Province列表
	 * @return
	 */
	@RequestMapping(value="provincelist.do")
	public void Provincelist(HttpServletRequest request,HttpServletResponse response){
		
		String p = request.getParameter("page");
		String r = request.getParameter("rows");
		int page=1,rows = 10;
		if(util_Empty.strEmpty(p)){
			page = Integer.valueOf(p);
		}
		if(util_Empty.strEmpty(r)){
			rows = Integer.valueOf(r);
		}
		List<Province> list = new ArrayList<Province>();
		String hql="from Province where 1=1 ";
		Object param[] = new Object[]{};
		
		list=provinceService.getProvincePage(hql,param, page, rows);
		int total=provinceService.getTotalNum("select count(*) from Province where 1=1", param);
		util_Json.jsonForEasyUI(list, total, response);
		
	}
	
	
	/**添加Province实体*/
	@RequestMapping(value="saveProvince.do")
	public void saveProvince(Province Province,String msg,String upuid,HttpServletRequest request,HttpServletResponse response){
		/*Province cc = new Province();
		cc = Province;
		int i=1;
		
		if("upt".equals(msg)){
			User u = new User();
			u = userService.getUserByUID(upuid);//修改时候user实体不能传递只能用uid接收
			cc.setUid(u);
			i = ProvinceService.updateProvince(cc);
		}else{
			HttpSession session = request.getSession();
			User u = new User();
			u =(User) session.getAttribute("user");
			cc.setUid(u);//创建人实体
			cc.setCreatetime(util_Date.dateToStr1(new Date(),"yyyy:MM:dd HH:mm:ss "));//创建时间
			i=ProvinceService.saveProvince(cc);
			
		}
		util_Json.jsonPrintln(i+"", response);*/
		
	}
	/**根据id修改前Province的跳转*/
	@RequestMapping(value="goProvinceuptpage.do")
	public String goProvinceuptpage(int id,HttpServletRequest request,HttpServletResponse response){
		Province Province=provinceService.getProvinceModel(id);
	
		request.setAttribute("Province", Province);
		request.setAttribute("msg", "upt");
		return "/webjsp/Provincejsp/Province_update.jsp";
	}
	
	/**根据id删除Province*/
	@RequestMapping(value="delProvince.do")
	public void delProvince(String id,String msg,HttpServletRequest request,HttpServletResponse response){
		int i = provinceService.delProvinceByID(Integer.valueOf(id));
		//msg single(单个删除)more(多个删除)
		String message = "fail";
		if(i==1){
			message="success";
		}
		Map<String,String> m = new HashMap<String,String>();
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}
	/**根据ID查看Province实体*/
	
	@RequestMapping(value="gocheckProvince.do")
	public String gocheckProvince(int id,HttpServletRequest request,HttpServletResponse response){
		Province Province=provinceService.getProvinceModel(id);
		request.setAttribute("Province", Province);
		return "/webjsp/Provincejsp/Province_check.jsp";
	}
	
	
	/**获取当天经典语录(或者是最新的)*/
	
	@RequestMapping(value="getProvinceNow.do")
	public void getProvinceNow(HttpServletRequest request,HttpServletResponse response){
		Province Province= new Province();
		String hql="from Province  order by createtime desc";
		Object param[] = new Object[]{};
		List<Province> lc = new ArrayList<Province>();
		lc = provinceService.getAllProvince(hql, param);
		if(util_Empty.listEmpty(lc)){
			Province = lc.get(0);
		}
		Map<Object,Object> map= new HashMap<Object,Object>();
		map.put("Province", Province);
		util_Json.jsonPrintModel(map, response);
	}
	
	
	

	/**
	 * 导入经典语录
	 */
	@RequestMapping(value="/importProvince.do")
	public void importProvince(){
	System.out.println(111);
	}
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/exportProvince.do")
	public void exportProvince(HttpServletRequest request,HttpServletResponse response){
		/*String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from Province where 1=1 ";
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
		List<Province> list = ProvinceService.getAllProvince(hql, param);
		Export.export("经典语录记录","经典语录记录",Province.class, list,  response);*/
		
	}
}
