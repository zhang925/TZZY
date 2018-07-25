package com.zzy.controller.zmail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzy.controller.datagridlist.Datagrid;
import com.zzy.model.User;
import com.zzy.model.mail.TxlGroup;
import com.zzy.service.TxlGroupService;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;


@Controller
@RequestMapping(value = "/txlGroupController")
public class TxlGroupController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private TxlGroupService txlGroupService;
	@Autowired
	private UtilService utilService;
	/**
	 * 查看TxlGroup列表
	 * @return
	 */
	@RequestMapping(value="list.do")
	public void list(HttpServletRequest request,HttpServletResponse response){
		//调用DataGrid通用查询方法[需要查询条件-->在类Serach中写查询条件即可]
		Datagrid.list(request,response,TxlGroup.class,utilService);
	}
	 
	
	
	/**
	 * 添加[load=add]
	 * 修改[load=upt]
	 * 实体*/
	@RequestMapping(value="save.do")
	public void save(TxlGroup txlGroup,String load,String TxlGroupid,HttpServletRequest request,HttpServletResponse response){
		int i=2;
		User user = (User) request.getSession().getAttribute("user");
		if("add".equals(load)){//添加
			txlGroup.setCreateuserid(user);
			txlGroup.setCteatetime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd hh:mm:ss"));
			txlGroup.setState("0");
			i=txlGroupService.saveTxlGroup(txlGroup);
		}else if("upt".equals(load)){//修改
			txlGroup.setCreateuserid(userService.getUserByUID(request.getParameter("tempcreateuserid"))); ; 
			txlGroup.setUpttime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd hh:mm:ss"));
			txlGroup.setUptuserid(user);
			i=txlGroupService.updateTxlGroup(txlGroup);
		}
		util_Json.jsonPrintln(i+"", response);
	}
	/**
	 * 查看[load=see]、修改[load=upt]、添加[load=add]的页面跳转
	 */
	@RequestMapping(value="goAddOrUptPage.do")
	public String goAddOrUptPage(String id,HttpServletRequest request,HttpServletResponse response){
		if(util_Empty.strEmpty(id)){
			TxlGroup txlGroup=txlGroupService.getTxlGroupModel(Integer.valueOf(id));
			request.setAttribute("txlGroup", txlGroup);
		}
		request.setAttribute("load", request.getParameter("load"));
		return "/webjsp/txljsp/txlgroup_addorupt.jsp";
	}
	
	/**根据id删除TxlGroup*/
	@RequestMapping(value="del.do")
	public void del(String ids,String msg,HttpServletRequest request,HttpServletResponse response){
		//[msg=single单个删除][msg=more多个删除]
		//[message=fail删除失败][message=success删除成功]
		String message = "fail";
		int i = 2;
		if("single".equals(msg)){
			TxlGroup txlGroup = txlGroupService.getTxlGroupModel(Integer.valueOf(ids));
			txlGroup.setState("del");
			i = txlGroupService.updateTxlGroup(txlGroup);
		}else if("more".equals(msg)){
			for(String id : ids.split(",")){
				TxlGroup txlGroup = txlGroupService.getTxlGroupModel(Integer.valueOf(id));
				txlGroup.setState("del");
				i=txlGroupService.updateTxlGroup(txlGroup);
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
	@RequestMapping(value="/importTxlGroup.do")
	public void importTxlGroup(){
		System.out.println(111);
	}
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/exportTxlGroup.do")
	public void exportTxlGroup(HttpServletRequest request,HttpServletResponse response){
		/*String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from TxlGroup where 1=1 ";
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
		List<TxlGroup> list = TxlGroupService.getAllTxlGroup(hql, param);
		Export.export("经典语录记录","经典语录记录",TxlGroup.class, list,  response);*/
		
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
   		}else if(type=="TxlGroup"){//点击通讯录
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
