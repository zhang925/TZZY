package com.zzy.controller.zmail;


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
import com.zzy.model.district.District;
import com.zzy.model.mail.ZmailRelationShip;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.service.ZmailRelationShipService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.page.PageZzy;


@Controller
@RequestMapping(value = "/zmailRelationShip")
public class ZmailRelationShipController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private ZmailRelationShipService zmailRelationShipService;
	@Autowired
	private UtilService utilService;
	
	/**
	 * 查看zmailRelationShip列表
	 * @return
	 */
	@RequestMapping(value="list.do")
	public void list(HttpServletRequest request,HttpServletResponse response){
		//调用DataGrid通用查询方法[需要查询条件-->在类Serach中写查询条件即可]
		//Datagrid.list(request,response,zmailRelationShip.class,utilService);
	}
	 
	
	/**
	 * 添加[load=add]
	 * 修改[load=upt]
	 * 实体*/
	@RequestMapping(value="save.do")
	public void save(ZmailRelationShip zmailRelationShip,String load,String zmailRelationShipid,HttpServletRequest request,HttpServletResponse response){
		/*int i=2;
		if("add".equals(load)){//添加
			i=zmailRelationShipService.savezmailRelationShip(zmailRelationShip);
		}else if("upt".equals(load)){//修改
			//list和jsp页面id冲突这里改写成zmailRelationShipid
			zmailRelationShip.setId(Integer.valueOf(zmailRelationShipid));
			i=zmailRelationShipService.updatezmailRelationShip(zmailRelationShip);
		}
		util_Json.jsonPrintln(i+"", response);*/
	}
	/**
	 * 查看[load=see]、修改[load=upt]、添加[load=add]的页面跳转
	 */
	@RequestMapping(value="goAddOrUptPage.do")
	public String goAddOrUptPage(String id,HttpServletRequest request,HttpServletResponse response){
		/*if(util_Empty.strEmpty(id)){
			zmailRelationShip zmailRelationShip=zmailRelationShipService.getzmailRelationShipModel(Integer.valueOf(id));
			request.setAttribute("zmailRelationShip", zmailRelationShip);
		}
		request.setAttribute("load", request.getParameter("load"));*/
		return "/webjsp/districtjsp/zmailRelationShip_addorupt.jsp";
	}
	
	/**根据id删除zmailRelationShip*/
	@RequestMapping(value="del.do")
	public void del(String ids,String msg,HttpServletRequest request,HttpServletResponse response){
		/*//[msg=single单个删除][msg=more多个删除]
		//[message=fail删除失败][message=success删除成功]
		String message = "fail";
		int i = 2;
		if("single".equals(msg)){
			zmailRelationShip zmailRelationShip = zmailRelationShipService.getzmailRelationShipModel(Integer.valueOf(ids));
			zmailRelationShip.setState("del");
			i = zmailRelationShipService.updatezmailRelationShip(zmailRelationShip);
		}else if("more".equals(msg)){
			for(String id : ids.split(",")){
				zmailRelationShip zmailRelationShip = zmailRelationShipService.getzmailRelationShipModel(Integer.valueOf(id));
				zmailRelationShip.setState("del");
				i=zmailRelationShipService.updatezmailRelationShip(zmailRelationShip);
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
		util_Json.jsonPrintModel(m, response);*/
	}
	
	
	/**
	 * 导入经典语录
	 */
	@RequestMapping(value="/importzmailRelationShip.do")
	public void importzmailRelationShip(){
		System.out.println(111);
	}
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/exportzmailRelationShip.do")
	public void exportzmailRelationShip(HttpServletRequest request,HttpServletResponse response){
		/*String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from zmailRelationShip where 1=1 ";
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
		List<zmailRelationShip> list = zmailRelationShipService.getAllzmailRelationShip(hql, param);
		Export.export("经典语录记录","经典语录记录",zmailRelationShip.class, list,  response);*/
		
	}
	/**获取当前时间   上 下午 傍晚等*/
	@RequestMapping(value="/getQuantumTime.do")
	public void getQuantumTime(HttpServletRequest request,HttpServletResponse response){
		String quantumTime="";
		quantumTime = util_Date.getTimeQuantum(null);
		Map map = new HashMap();
		map.put("hello", quantumTime);
		util_Json.jsonPrintModel(map, response);
	}
	
	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="goMailPage.do")
	public String goMailPage(String type,HttpServletRequest request,HttpServletResponse response){
		String src="";//默认是写信
		if(!util_Empty.strEmpty(type)){
			type="xx";
		}
		if(type=="xx"){//点击写信
			src="webjsp/mailjsp/mail_addordraft.jsp";
   		}else if(type=="txl"){//点击通讯录
   			src="webjsp/addressbookjsp/addressbook.jsp";
   		}else if(type=="cg"){//点击草稿箱
   			src="webjsp/mailjsp/mail_list.jsp";
   		}else if(type=="收件箱"){//点击收件箱
   			src="webjsp/mailjsp/mail_list.jsp";
   		}else if(type=="已发送"){//点击已发送
   			src="webjsp/mailjsp/mail_list.jsp";
   		}else if(type=="已删除"){//点击已删除
   			src="webjsp/mailjsp/mail_list.jsp";
   		}
		return src;
	}
}
