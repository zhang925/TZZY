package com.zzy.controller;


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
import com.zzy.service.SysConfigureService;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.page.PageZzy;


@Controller
@RequestMapping(value = "/sysConfigureController")
public class SysConfigureController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private SysConfigureService sysConfigureService;
	@Autowired
	private UtilService utilService;
	
	/**
	 * 查看SysConfigure列表
	 * @return
	 */
	@RequestMapping(value="list.do")
	public void list(HttpServletRequest request,HttpServletResponse response){
		//调用DataGrid通用查询方法[需要查询条件-->在类Serach中写查询条件即可]
		Datagrid.list(request,response,SysConfigure.class,this.utilService);
	}
	 
	
	/**
	 * 添加[load=add]
	 * 修改[load=upt]
	 * 实体*/
	@RequestMapping(value="save")
	public void save(SysConfigure sysConfigure,String load,String sysConfigureid,HttpServletRequest request,HttpServletResponse response){
		int i=2;
		if("add".equals(load)){//添加
			sysConfigure.setCreatetime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd hh:mm:ss"));
			sysConfigure.setCreateuser((User)request.getSession().getAttribute("user"));
			sysConfigure.setState("0");
			i=sysConfigureService.saveSysConfigure(sysConfigure);
		}else if("upt".equals(load)){//修改
			sysConfigure.setUpdatetime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd hh:mm:ss"));
			sysConfigure.setUpdateuser((User)request.getSession().getAttribute("user"));
			sysConfigure.setCreateuser(userService.getUserByUID(request.getParameter("createuseruid")));
			sysConfigure.setUpdateuser(userService.getUserByUID(request.getParameter("updateuseruid")));
			i=sysConfigureService.updateSysConfigure(sysConfigure);
		}
		util_Json.jsonPrintln(i+"", response);
	}
	/**
	 * 查看[load=see]、修改[load=upt]、添加[load=add]的页面跳转
	 */
	@RequestMapping(value="goAddOrUptPage.do")
	public String goAddOrUptPage(String id,HttpServletRequest request,HttpServletResponse response){
		if(util_Empty.strEmpty(id)){
			SysConfigure sysconfigure=sysConfigureService.getSysConfigureModel(Integer.valueOf(id));
			request.setAttribute("sysconfigure", sysconfigure);
		}
		request.setAttribute("load", request.getParameter("load"));
		return "/webjsp/sysconfigurejsp/sysconfigure_addorupt.jsp";
	}
	
	/**根据id删除SysConfigure*/
	@RequestMapping(value="del.do")
	public void del(String ids,String msg,HttpServletRequest request,HttpServletResponse response){
		//[msg=single单个删除][msg=more多个删除]
		//[message=fail删除失败][message=success删除成功]
		String message = "fail";
		int i = 2;
		if("single".equals(msg)){
			SysConfigure SysConfigure = sysConfigureService.getSysConfigureModel(Integer.valueOf(ids));
			SysConfigure.setState("del");
			i = sysConfigureService.updateSysConfigure(SysConfigure);
		}else if("more".equals(msg)){
			for(String id : ids.split(",")){
				SysConfigure SysConfigure = sysConfigureService.getSysConfigureModel(Integer.valueOf(id));
				SysConfigure.setState("del");
				i=sysConfigureService.updateSysConfigure(SysConfigure);
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
	@RequestMapping(value="/imp.do")
	public void importSysConfigure(){
		System.out.println(111);
	}
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/export.do")
	public void exportSysConfigure(HttpServletRequest request,HttpServletResponse response){
		/*String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from SysConfigure where 1=1 ";
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
		List<SysConfigure> list = SysConfigureService.getAllSysConfigure(hql, param);
		Export.export("经典语录记录","经典语录记录",SysConfigure.class, list,  response);*/
		
	}
}
