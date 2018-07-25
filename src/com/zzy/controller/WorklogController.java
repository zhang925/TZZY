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
import com.zzy.service.UserService;
import com.zzy.service.WorklogService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;


@Controller
@RequestMapping(value = "/worklogController")
public class WorklogController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private WorklogService worklogService;
	
	
	/**
	 * 查看worklog列表
	 * @return
	 */
	@RequestMapping(value="workloglist.do")
	public void workloglist(HttpServletRequest request,HttpServletResponse response){
		String p = request.getParameter("page");
		String r = request.getParameter("rows");
		int page=1,rows = 10;
		if(util_Empty.strEmpty(p)){
			page = Integer.valueOf(p);
		}
		if(util_Empty.strEmpty(r)){
			rows = Integer.valueOf(r);
		}
		List<Worklog> list = new ArrayList<Worklog>();
		User u = new User();
		HttpSession session = request.getSession();
		u=(User)session.getAttribute("user");
		String hql="from Worklog where 1=1 ";
		
		/**要追加的条件*/
		String condition="";
		int total=0;//总记录数
		Object param[] = new Object[]{};
		
		String counthql = "select count(*) from Worklog where 1=1 ";
		//查询条件
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		
		
		if(util_Empty.strEmpty(createtime)){//自己只能查询时间
			condition = condition + " and createtime like '%"+createtime+"%'";
		}
		if(u!=null && "admin".equals(u.getState())){//管理员
			User u2 = new User();
			if(util_Empty.strEmpty(uid)){//只有管理员才能查询其他人的
				u2 = userService.getUserByUID(uid);
				condition = condition + " and uid = ?";
				param = new Object[]{u2};
			}
			if(util_Empty.strEmpty(name)){
				u2 = userService.getgetUserModel("from User where name like '%"+name+"%'", new Object[]{});
				condition = condition + " and uid = ?";
				param = new Object[]{u2};
			}
			condition=condition+" order by id  desc";
			total=worklogService.getTotalNum(counthql+condition, param);
			list=worklogService.getWorklogPage(hql+condition,param, page, rows);
		}else{
			condition=condition+" and uid = ? ";
			condition=condition+" order by id desc";
			param =new Object[]{u};
			total=worklogService.getTotalNum(counthql+condition, param);
			list=worklogService.getWorklogPage(hql+condition,param, page, rows);
		}
		util_Json.jsonForEasyUI(list, total, response);
		
	}
	
	
	/**添加worklog实体*/
	@RequestMapping(value="saveworklog.do")
	public void saveworklog(Worklog worklog,String msg,String upuid,HttpServletRequest request,HttpServletResponse response){
		Worklog wl = new Worklog();
		wl = worklog;
		int i=0;
		if("upt".equals(msg)){
			wl.setUptime(util_Date.dateToStr1(new Date()," yyyy:MM:dd HH:mm:ss "));
			User u = new User();
			u = userService.getUserByUID(upuid);
			wl.setUid(u);
			i = worklogService.updateWorklog(wl);
		}else{
			HttpSession session = request.getSession();
			User u = new User();
			u =(User) session.getAttribute("user");
			wl.setUid(u);//创建人实体
			wl.setCreateusername(u.getName());//创建人姓名
			wl.setCreatetime(util_Date.dateToStr1(new Date()," yyyy:MM:dd HH:mm:ss "));//创建时间
			i=worklogService.saveWorklog(wl);
		}
		util_Json.jsonPrintln(i+"", response);
		
	}
	/**根据id修改前worklog的跳转*/
	@RequestMapping(value="goworkloguptpage.do")
	public String goworkloguptpage(int id,HttpServletRequest request,HttpServletResponse response){
		Worklog worklog=worklogService.getWorklogModel(id);
		request.setAttribute("worklog", worklog);
		request.setAttribute("msg", "upt");
		return "/webjsp/worklogjsp/worklog_add.jsp";
	}
	
	/**根据id删除worklog*/
	@RequestMapping(value="delworklog.do")
	public void delworklog(String id,String msg,HttpServletRequest request,HttpServletResponse response){
		int i = worklogService.delWorklogByID(Integer.valueOf(id));
		//msg single(单个删除)more(多个删除)
		String message = "fail";
		if(i==1){
			message="success";
		}
		Map<String,String> m = new HashMap<String,String>();
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}
	/**根据ID查看worklog实体*/
	
	@RequestMapping(value="gocheckworklog.do")
	public String gocheckworklog(int id,HttpServletRequest request,HttpServletResponse response){
		Worklog worklog=worklogService.getWorklogModel(id);
		request.setAttribute("worklog", worklog);
		return "/webjsp/worklogjsp/worklog_check.jsp";
	}
	
	

	/**
	 * 导入工作日志
	 */
	@RequestMapping(value="/importworklog.do")
	public void importworklog(){
	System.out.println(111);
	}
	/**
	 * 导出工作日志
	 */
	@RequestMapping(value="/exportworklog.do")
	public void exportworklog(HttpServletRequest request,HttpServletResponse response){
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from Worklog where 1=1 ";
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
		List<Worklog> list = worklogService.getAllWorklog(hql, param);
		Export.export("工作日志记录","工作日志记录",Worklog.class, list,  response);
		
	}
}
