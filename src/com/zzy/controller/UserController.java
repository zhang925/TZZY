package com.zzy.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzy.model.ResultOk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;


import com.zzy.model.User;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.util_Md5;
import com.zzy.util.util_RandomCode;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制层(UserController)
 * @author zzy
 *
 */
@Controller
@RequestMapping(value = "/userController")
public class UserController {
	
	@Autowired 
	//即使配置了userService这个bin@Autowired也得加上不然为null
	private UserService userService;
	@Autowired
	private UtilService utilService;
	/**
	 * 登录方法
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/login.do")
	public void login(HttpServletResponse  response,HttpServletRequest request){
		
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			User user = new User();
			user = userService.getUserForLogin(username, password);
			if(user!=null){
				 HttpSession session = request.getSession();
				 //判断是否登录过用户，并且没有退出，若是则清空
				 if(session.getAttribute("user")!=null){
					 session.removeAttribute("user");
				 }
			     session.setAttribute("user", user);
				out.println("1");
			}else{
				out.println("2");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 后台easyUI显示用户列表
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/userlist.do")
	public void userlist(HttpServletResponse  response,HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String state = request.getParameter("state");
		
		String hql = "from User where state!='del' order by createtime desc ";
		String counthql = " select count(*) from User where state!='del' ";
		
		
		if(util_Empty.strEmpty(state)){// 1 条件state优先
			hql = "from User where state='"+state+"' order by createtime desc ";
			counthql = " select count(*) from User where state='"+state+"'";
		}
		
		/**要追加的条件*/
		String condition="";
		
		if(util_Empty.strEmpty(uid)){// 2
			condition = condition + " and uid='"+uid+"'";
		}
		if(util_Empty.strEmpty(name)){// 3
			condition = condition + " and name like '%"+name+"%'";
		}
		if(util_Empty.strEmpty(username)){// 4
			condition = condition + " and username like '%"+username+"%'";
		}
		condition = condition + " order by createtime desc";
		
		
		int total=userService.getTotalNum( counthql + condition, new Object[]{});
		
		List<User> list = new ArrayList<User>();
		int p=1,r=10;
		if(util_Empty.strEmpty(page)){
			p=Integer.valueOf(page);
		}
		if(util_Empty.strEmpty(rows)){
			r=Integer.valueOf(rows);
		}
		
		
		list=userService.getUserPage(hql+condition, new Object[]{},p,r);
		
		util_Json.jsonForEasyUI(list, total, response);
		
	}
	
	/**
	 * 添加用户 或者 修改用户
	 * @param user
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/adduser.do")
	public void adduser(User user,String msg,HttpServletResponse  response,HttpServletRequest request){
		User u  = new User();
		u = user;
		int i = 1;
		if(util_Empty.strEmpty(msg)){
			if("update".equals(msg)){//修改
				List<User> list2 = new ArrayList<User>();
				list2=userService.getAllUser("from User where username=? and uid !=?", new Object[]{u.getUsername(),u.getUid()});
				if(util_Empty.listEmpty(list2)){
					i = 0;
				}else{
					u.setPasswordmd5(util_Md5.MD5(u.getPassword()));
					i = userService.updateUser(u);
				}
			}
		}else{//添加
			//首先判断添加的新用户名是否存在
			List<User> list2 = new ArrayList<User>();
			list2=userService.getAllUser("from User where username=?", new Object[]{u.getUsername()});
			if(list2==null || list2.size()<=0 ){
				String uid = getuid();
				u.setUid(uid);
				u.setPasswordmd5(util_Md5.MD5(u.getPassword()));
				//u.setPhotoid("tupianid");
				u.setCreatetime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd HH:mm:ss"));
				u.setState("0");
				i = userService.saveUser(u);
			}else{
				i = 0;
			}
			
		}
		util_Json.jsonPrintln(i+"", response);
	}
	
	
	/**
	 * 添加用户
	 * @param
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/gocheckuser.do")
	public String gocheckuser(String uid,HttpServletResponse  response,HttpServletRequest request){
		User user = userService.getUserByUID(uid);
		request.setAttribute("user",user );
		return "/webjsp/userjsp/user_check.jsp";
	}
	
	/**
	 * 删除用户 多条 或者 单条 删除
	 * @param uid
	 * @param msg
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/deluser.do")
	public void deluser(String uid,String msg,HttpServletResponse  response,HttpServletRequest request){
		Map<String, String> m = new HashMap<String, String>();
		String message="";
		if(util_Empty.strEmpty(msg)){//msg非空
			if("more".equals(msg)){//多个删除
				String a [] = uid.split(",");//根据,分割
				for(int i=0;i<a.length;i++){//循环删除
					int j = 1;
					//j = userService.delUser(a[i]);//实际上不能真的删除可以吧状态改成del
					/***禁用状态***/
					User u = new User();
					u = userService.getUserByUID(a[i]);
					u.setState("del");
					j = userService.updateUser(u);
					/******/
					if(j==2){//删除失败
						message=message+"第"+(i+1)+"个用户删除异常！";
					}
				}
				if(message.length()<=5){//无异常(长度可以随便写只要不超过异常提示语)
					message = "success";
				}
			}else if("single".equals(msg)){//单个删除
				int i=1;
				//i = userService.delUser(uid);//实际上不能真的删除可以吧状态改成del
				/***禁用状态***/
				User u = new User();
				u = userService.getUserByUID(uid);
				u.setState("del");
				i = userService.updateUser(u);
				/******/
				if(i == 1){//删除成功
					message="success";
				}else{
					message="删除失败！";
				}
			}
		}//msg非空
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}
	
	/**
	 * 修改用户信息前的页面跳转
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/goupuser.do")
	public String goupuser(String uid,HttpServletRequest request){
		User u = userService.getUserByUID(uid);
		request.setAttribute("user", u);
		return "/webjsp/userjsp/user_update.jsp";
	}
	

	@RequestMapping(value="/checkUsername")
	 public void checkUsername(HttpServletRequest request,HttpServletResponse  response){
		 List<User> ulist = new ArrayList<User>();
		 String username =  request.getParameter("username");
		 ulist = userService.getAllUser("from User where username=? ", new Object[]{username});
		 Map<Object,Object> m  = new HashMap<Object,Object>();
		 if(util_Empty.listEmpty(ulist)){
			 m.put("check", "success");
		 }else{
			 m.put("check", "fail");
		 }
		 util_Json.jsonPrintModel(m, response);
	}
	
	@RequestMapping(value="/checkAqyz.do")
	 public void checkAqyz(HttpServletRequest request,HttpServletResponse  response){
		//安全验证
		List<User> ulist = new ArrayList<User>();
		 String aqwt =  request.getParameter("aqwt");
		 String  username=  request.getParameter("username");
		 ulist = userService.getAllUser("from User where username=? and phone = ?", new Object[]{username,aqwt});
		 Map<Object,Object> m  = new HashMap<Object,Object>();
		 if(util_Empty.listEmpty(ulist)){
			 m.put("check", "success");
		 }else{
			 m.put("check", "fail");
		 }
		 util_Json.jsonPrintModel(m, response);
	 }
	
	@RequestMapping(value="/xgmm.do")
	 public void xgmm(HttpServletRequest request,HttpServletResponse  response){
		
		//修改密码
		String password = request.getParameter("password");
		String  username=  request.getParameter("username");//找回密码时候修改密码
		 Map<Object,Object> m  = new HashMap<Object,Object>();
		if(username==null || "".equals(username)){//后台修改密码
			HttpSession  session = request.getSession();
			User u = (User) session.getAttribute("user");
			if(u!=null){
				username = u.getUsername();
			}else{
				//用户没有登录，或者登录超时
				 m.put("check", "fail");
			}
		}
		User u = userService.getgetUserModel("from User where username=? ", new Object[]{username});
		int i = 0;
		if(u!=null){
			u.setPassword(password);
			u.setPasswordmd5(util_Md5.MD5(password));
			i = userService.saveOrUpdateUser(u);
		}
		
		 if(i==1){
			 m.put("check", "success");
		 }else{
			 m.put("check", "fail");
		 }
		 util_Json.jsonPrintModel(m, response);
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 退出
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/exit.do")
	public void exit(HttpServletResponse  response,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("user");
	}
	/**
	 * 通过查询获取UID
	 * @return
	 */
	public String getuid(){
		List<User> list = new ArrayList<User>();
		list = userService.getAllUser("from User", new Object[]{});
		int listsize=0;
		if(util_Empty.listEmpty(list)){
			listsize=list.size();
		}
		int length = 5;//起始长度为5位(从10000开始)
		String size = "8";
		for(int i=1;i<length;i++){
			size=size+"9";
		}
		if(listsize>Integer.valueOf(size)){//大于89999 最高 899 999 999 个数字
			//新的length
			length = ((""+listsize).length()+1);//数据库总位数加1位
		}
		//如果 数据量 超过 9(亿)99 999 999 怎么办 1+2+3+……+999 999 999
		//目前是不可能的  超过 后 int listsize 将不能 使用
		String uid="";
		List<User> list2 = new ArrayList<User>();
		do{
			uid = util_RandomCode.bigNum(length);
			list2 = userService.getAllUser("from User where uid = ?", new Object[]{uid});
		}while(util_Empty.listEmpty(list2));//false的时候停止
		return uid;
	}
	
	
	
	/**
	 * 个人中心显示用户信息
	 */
	@RequestMapping(value="/gouserself.do")
	public String gouserself(String uid,HttpServletResponse  response,HttpServletRequest request){
		User user = userService.getUserByUID(uid);
		//如果写成user会和session里面的信息冲突
		request.setAttribute("userself",user );
		return "/webjsp/userjsp/user_self.jsp";
	}

/*-----------***------	以下为JS例子部分	--------------------------------*/

	@RequestMapping(value="/userlistlayerui")
	public void userlistlayerui(HttpServletResponse  response,HttpServletRequest request){
		String page = request.getParameter("page");//当前页
		String rows = request.getParameter("limit");//：每页数据行数，默认为30；这个参数值就是我们设置的pageSize
		//sidx:uid 排序的字段名字 //sord:desc 升序还是降序
		List<User> list = new ArrayList<User>();
		int p=1,r=5;
		if(util_Empty.strEmpty(page)){
			p=Integer.valueOf(page);
		}
		if(util_Empty.strEmpty(rows)){
			r=Integer.valueOf(rows);
		}
		list = userService.getUserPage("from User order by createtime desc",new Object[]{},p,r);
		int total = userService.getTotalNum( "select count(*) from User ", new Object[]{});
		util_Json.jsonForLayerUI(list,total,r,response);
	}
	@RequestMapping({"/userlisteasyui"})
	public void userlisteasyui(HttpServletResponse response, HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		new ArrayList();
		int p = 1;
		int r = 5;
		if (util_Empty.strEmpty(page)) {
			p = Integer.valueOf(page);
		}

		if (util_Empty.strEmpty(rows)) {
			r = Integer.valueOf(rows);
		}

		List<User> list = this.userService.getUserPage("from User order by createtime desc ", new Object[0], p, r);
		int total = this.userService.getTotalNum("select count(*) from User ", new Object[0]);
		util_Json.jsonForEasyUI(list, total, response);
	}

	@RequestMapping({"/userlistext.do"})
	public void userlistext(HttpServletResponse response, HttpServletRequest request) {
		String page;
		try {
			page = request.getParameter("names");
			page = new String(page.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println(page);
		} catch (Exception var9) {
			;
		}

		page = request.getParameter("page");
		String rows = request.getParameter("limit");
		new ArrayList();
		int p = 1;
		int r = 5;
		if (util_Empty.strEmpty(page)) {
			p = Integer.valueOf(page);
		}

		if (util_Empty.strEmpty(rows)) {
			r = Integer.valueOf(rows);
		}

		List<User> list = this.userService.getUserPage("from User order by createtime desc", new Object[0], p, r);
		int total = this.userService.getTotalNum("select count(*) from User ", new Object[0]);
		util_Json.jsonForExt(list, total, response);
	}

	/**EXtjs返回一个json对象*/
	@RequestMapping({"/getUserById.do"})
	@ResponseBody
	public ResultOk getUserById(String uid) {
		ResultOk resultOk = new ResultOk();
		String msg = "操作成功！";
		User user = this.userService.getUserByUID(uid);
		this.userService.saveOrUpdateUser(user);
		resultOk.setObj(user);
		resultOk.setMsg(msg);
		return resultOk;
	}

	@RequestMapping({"/goInfoLayerUI.do"})
	public String goInfoLayerUI(String uid, HttpServletResponse response, HttpServletRequest request) {
		User user = this.userService.getUserByUID(uid);
		request.setAttribute("user", user);
		request.setAttribute("load", request.getParameter("load"));
		return "webjsp/other/layerui/user_add_update.jsp";
	}

	@RequestMapping({"/goInfoEasyUI.do"})
	public String goInfoEasyUI(String uid, HttpServletResponse response, HttpServletRequest request) {
		User user = this.userService.getUserByUID(uid);
		request.setAttribute("user", user);
		request.setAttribute("load", request.getParameter("load"));
		return "webjsp/other/easyui/user_add_update.jsp";
	}



	@ResponseBody
	@RequestMapping({"/delusers.do"})
	public ResultOk delusers(String uids, HttpServletResponse response, HttpServletRequest request) {
		ResultOk resultOk = new ResultOk();
		String msg = "删除成功！";
		String jqids = request.getParameter("id");
		if (jqids != null && !"".equals(jqids) && !"_empty".equals(jqids)) {
			uids = jqids;
		}

		if (uids != null) {
			String[] var7 = uids.split(",");
			int var8 = var7.length;

			for(int var9 = 0; var9 < var8; ++var9) {
				String uid = var7[var9];
				this.userService.delUser(uid);
			}
		}

		resultOk.setMsg(msg);
		return resultOk;
	}

	@RequestMapping({"/addOrUpdate.do"})
	@ResponseBody
	public ResultOk addOrUpdate(User user, String type , HttpServletRequest request) {
		ResultOk resultOk = new ResultOk();
		String msg = "操作成功！";

		if ("update".equals(type)) {
			msg = "修改成功！";
		} else if ("add".equals(type)) {
			msg = "保存成功！";
		}

		String jqid = request.getParameter("id");
		if (jqid != null && !"".equals(jqid) && !"_empty".equals(jqid)) {//jqgrid专用
			user.setUid(jqid);
		}

		if (StringUtils.hasText(user.getUid())) {//修改
			List<User> list2 = new ArrayList<User>();
			list2=userService.getAllUser("from User where username=? and uid !=?", new Object[]{user.getUsername(),user.getUid()});
			if(list2.isEmpty()){
				user.setPasswordmd5(util_Md5.MD5(user.getPassword()));
				userService.updateUser(user);
			}
		} else {
			//首先判断添加的新用户名是否存在
			List<User> list2 = new ArrayList<User>();
			list2=userService.getAllUser("from User where username=?", new Object[]{user.getUsername()});
			if(list2==null || list2.size()<=0 ){
				user.setUid(getuid());
				user.setPasswordmd5(util_Md5.MD5(user.getPassword()));
				//u.setPhotoid("tupianid");
				user.setCreatetime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd HH:mm:ss"));
				user.setState("0");
				userService.saveUser(user);
			}else{
				msg="账户已经存在";
			}
		}
		resultOk.setMsg(msg);
		return resultOk;
	}


	/**验证用户是否被注册*/
	@RequestMapping({"/checkUser.do"})
	@ResponseBody
	public ResultOk checkUser(String username) {
		ResultOk resultOk = new ResultOk();
		List<User> list = null;
		if(username!=null){
			list = userService.getAllUser("from User where username=?", new Object[]{username});
		}
		if(list.isEmpty()){
			resultOk.setMsg("yes");//允许注册
		}else{
			resultOk.setMsg("no");//不允许注册
		}
		return resultOk;
	}


}
