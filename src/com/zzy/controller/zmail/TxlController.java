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
import com.zzy.model.mail.Txl;
import com.zzy.model.mail.TxlGroup;
import com.zzy.service.TxlGroupService;
import com.zzy.service.TxlService;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;


@Controller
@RequestMapping(value = "/txlController")
public class TxlController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private TxlService txlService;
	@Autowired
	private UtilService utilService;
	@Autowired
	private TxlGroupService txlGroupService;
	/**
	 * 查看Txl列表
	 * @return
	 */
	@RequestMapping(value="list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		//调用DataGrid通用查询方法[需要查询条件-->在类Serach中写查询条件即可]
		Datagrid.list(request,response,Txl.class,utilService);
	}
	 
	
	/**
	 * 添加[load=add]
	 * 修改[load=upt]
	 * 实体*/
	@RequestMapping(value="save")
	public void save(Txl txl,String load,HttpServletRequest request,HttpServletResponse response){
		int i=2;
		User user = (User) request.getSession().getAttribute("user");
		if("add".equals(load)){//添加
			txl.setCreateuserid(user);
			txl.setCteatetime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd hh:mm:ss"));
			txl.setState("0");
			i=txlService.saveTxl(txl);
		}else if("upt".equals(load)){//修改
			txl.setCreateuserid(userService.getUserByUID(request.getParameter("tempcreateuserid"))); ; 
			txl.setUpttime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd hh:mm:ss"));
			txl.setUptuserid(user);
			i=txlService.updateTxl(txl);
		}
		util_Json.jsonPrintln(i+"", response);
	}
	/**
	 * 查看[load=see]、修改[load=upt]、添加[load=add]的页面跳转
	 */
	@RequestMapping(value="goAddOrUptPage")
	public String goAddOrUptPage(String id,HttpServletRequest request,HttpServletResponse response){
		if(util_Empty.strEmpty(id)){
			Txl txl=txlService.getTxlModel(Integer.valueOf(id));
			request.setAttribute("txl", txl);
		}
		request.setAttribute("load", request.getParameter("load"));
		return "/webjsp/txljsp/txl_addorupt.jsp";
	}
	
	/**根据id删除Txl*/
	@RequestMapping(value="del")
	public void del(String ids,String msg,HttpServletRequest request,HttpServletResponse response){
		//[msg=single单个删除][msg=more多个删除]
		//[message=fail删除失败][message=success删除成功]
		String message = "fail";
		int i = 2;
		if("single".equals(msg)){
			Txl Txl = txlService.getTxlModel(Integer.valueOf(ids));
			Txl.setState("del");
			i = txlService.updateTxl(Txl);
		}else if("more".equals(msg)){
			for(String id : ids.split(",")){
				Txl Txl = txlService.getTxlModel(Integer.valueOf(id));
				Txl.setState("del");
				i=txlService.updateTxl(Txl);
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
	@RequestMapping(value="/importTxl")
	public void importTxl(){
		System.out.println(111);
	}
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/exportTxl")
	public void exportTxl(HttpServletRequest request,HttpServletResponse response){
		/*String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from Txl where 1=1 ";
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
		List<Txl> list = TxlService.getAllTxl(hql, param);
		Export.export("经典语录记录","经典语录记录",Txl.class, list,  response);*/
		
	}
	/**获取当前时间   上 下午 傍晚等*/
	@RequestMapping(value="/getQuantumTime")
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
	@RequestMapping(value="goMailPage")
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
	

	@RequestMapping(value="txlbygroup")
	public void txlbygroup(HttpServletRequest request,HttpServletResponse response){
		//调用DataGrid通用查询方法[需要查询条件-->在类Serach中写查询条件即可]
		List<Txl> list = new ArrayList<Txl>();
		String hql = "from Txl where state!='del' and state is not null and createuserid=?";
		// and txlgroupid=?
		Object param[] = new Object[]{utilService.getLoginUser(request)}; 
		list = txlService.getAllTxl(hql,param );
		Map map = new HashMap();
		map.put("list", list);
		util_Json.jsonPrintModel(map, response);
		//int total = txlService.getTotalNum("select count(*) "+hql, param);
		//util_Json.jsonForEasyUI(list, total , response);
	}
	
	/**
	 * 非easyui版本的
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="txllist")
	public String txllist(HttpServletRequest request,HttpServletResponse response){
		
		
		//通讯录分组信息
		String hql2 = "from TxlGroup where state!='del' and state is not null and createuserid=? ";
		Object param2[] = new Object[]{utilService.getLoginUser(request)}; 
		List<TxlGroup> listtxlgroup = new ArrayList<TxlGroup>();
		listtxlgroup = txlGroupService.getAllTxlGroup(hql2, param2);
		request.setAttribute("listtxlgroup",listtxlgroup);
		
		//通讯录信息
		List<Txl> list = new ArrayList<Txl>();
		String hql = "from Txl where state!='del' and state is not null and createuserid=? ";
		Object param[] = new Object[]{utilService.getLoginUser(request)};
		request.setAttribute("totaltxlnum",txlService.getAllTxl(hql,param ).size());//全部的数量
		//是否分组
		String groupid = request.getParameter("groupid");
		if(groupid!=null && !"".equals(groupid)){
			TxlGroup txlGroup = txlGroupService.getTxlGroupModel(Integer.valueOf(groupid));
			hql = hql + " and txlgroupid=? ";
			param  = new Object[]{utilService.getLoginUser(request),txlGroup};
		}
		list = txlService.getAllTxl(hql,param );
		request.setAttribute("list",list);//全部的
		
		
		return "webjsp/txljsp/txl_list.jsp";
	}
	
	
	
}
