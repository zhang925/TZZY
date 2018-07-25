package com.zzy.controller;


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
import com.zzy.service.ClassicService;
import com.zzy.service.UserService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;


@Controller
@RequestMapping(value = "/classicController")
public class ClassicController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private ClassicService classicService;
	
	
	/**
	 * 查看Classic列表
	 * @return
	 */
	@RequestMapping(value="Classiclist.do")
	public void Classiclist(HttpServletRequest request,HttpServletResponse response){
		String p = request.getParameter("page");
		String r = request.getParameter("rows");
		int page=1,rows = 10;
		if(util_Empty.strEmpty(p)){
			page = Integer.valueOf(p);
		}
		if(util_Empty.strEmpty(r)){
			rows = Integer.valueOf(r);
		}
		List<Classic> list = new ArrayList<Classic>();
		User u = new User();
		HttpSession session = request.getSession();
		u=(User)session.getAttribute("user");
		String hql="from Classic where 1=1 ";
		Object param[] = new Object[]{};
		
		String where = "";
		
		//查询条件
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		if(util_Empty.strEmpty(createtime)){
			where = where + " and createtime like '%"+createtime+"%'";
		}
		//if(u!=null && "admin".equals(u.getState())){//管理员
			User u2 = new User();
			if(util_Empty.strEmpty(uid)){//只有管理员才能查询其他人的
				u2 = userService.getUserByUID(uid);
				where = where + " and uid = ?";
				param = new Object[]{u2};
			}
			if(util_Empty.strEmpty(name)){
				u2 = userService.getgetUserModel("from User where name like '%"+name+"%'", new Object[]{});
				where = where + " and uid = ?";
				param = new Object[]{u2};
			}
			where=where+" order by id  desc";
			list=classicService.getClassicPage(hql+where,param, page, rows);
		/*}else{
			hql=hql+" and uid=? ";
			hql=hql+" order by id desc";
			param =new Object[]{u};
			list=classicService.getClassicPage(hql,param, page, rows);
		}*/
		int total=classicService.getTotalNum("select count(*) from Classic where 1=1"+where, param);
		util_Json.jsonForEasyUI(list, total, response);
		
	}
	
	
	/**添加Classic实体*/
	@RequestMapping(value="saveClassic.do")
	public void saveClassic(Classic classic,String msg,String upuid,HttpServletRequest request,HttpServletResponse response){
		Classic cc = new Classic();
		cc = classic;
		int i=1;
		
		if("upt".equals(msg)){
			User u = new User();
			u = userService.getUserByUID(upuid);//修改时候user实体不能传递只能用uid接收
			cc.setUid(u);
			i = classicService.updateClassic(cc);
		}else{
			HttpSession session = request.getSession();
			User u = new User();
			u =(User) session.getAttribute("user");
			cc.setUid(u);//创建人实体
			cc.setCreatetime(util_Date.dateToStr1(new Date(),"yyyy:MM:dd HH:mm:ss "));//创建时间
			i=classicService.saveClassic(cc);
			
		}
		util_Json.jsonPrintln(i+"", response);
		
	}
	/**根据id修改前Classic的跳转*/
	@RequestMapping(value="goClassicuptpage.do")
	public String goClassicuptpage(int id,HttpServletRequest request,HttpServletResponse response){
		Classic classic=classicService.getClassicModel(id);
	
		request.setAttribute("classic", classic);
		request.setAttribute("msg", "upt");
		return "/webjsp/classicjsp/classic_update.jsp";
	}
	
	/**根据id删除Classic*/
	@RequestMapping(value="delClassic.do")
	public void delClassic(String id,String msg,HttpServletRequest request,HttpServletResponse response){
		int i = classicService.delClassicByID(Integer.valueOf(id));
		//msg single(单个删除)more(多个删除)
		String message = "fail";
		if(i==1){
			message="success";
		}
		Map<String,String> m = new HashMap<String,String>();
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}
	/**根据ID查看Classic实体*/
	
	@RequestMapping(value="gocheckClassic.do")
	public String gocheckClassic(int id,HttpServletRequest request,HttpServletResponse response){
		Classic Classic=classicService.getClassicModel(id);
		request.setAttribute("classic", Classic);
		return "/webjsp/classicjsp/classic_check.jsp";
	}
	
	
	/**获取当天经典语录(或者是最新的)*/
	
	@RequestMapping(value="getClassicNow.do")
	public void getClassicNow(HttpServletRequest request,HttpServletResponse response){
		Classic classic= new Classic();
		String hql="from Classic  order by createtime desc";
		Object param[] = new Object[]{};
		List<Classic> lc = new ArrayList<Classic>();
		lc = classicService.getAllClassic(hql, param);
		if(util_Empty.listEmpty(lc)){
			classic = lc.get(0);
		}
		Map<Object,Object> map= new HashMap<Object,Object>();
		map.put("classic", classic);
		util_Json.jsonPrintModel(map, response);
	}
	
	
	

	/**
	 * 导入经典语录
	 */
	@RequestMapping(value="/importClassic.do")
	public void importClassic(){
	System.out.println(111);
	}
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/exportClassic.do")
	public void exportClassic(HttpServletRequest request,HttpServletResponse response){
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from Classic where 1=1 ";
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
		List<Classic> list = classicService.getAllClassic(hql, param);
		Export.export("经典语录记录","经典语录记录",Classic.class, list,  response);
		
	}
}
