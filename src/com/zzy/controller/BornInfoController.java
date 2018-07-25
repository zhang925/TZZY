package com.zzy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.zzy.model.BornInfo;
import com.zzy.model.User;
import com.zzy.service.BornInfoService;
import com.zzy.test.test;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.util_RandomCode;
import com.zzy.util.util_age;
import com.zzy.util.util_lunarDate;

@Controller
@RequestMapping(value = "/bornInfoController")
public class BornInfoController {
	
	@Autowired
	private BornInfoService bornInfoService;
	
	
	
	@RequestMapping(value="/birthinfolist.do")
	public void birthinfolist(HttpServletResponse  response,HttpServletRequest request){
		//把今天的时间放到session中
		HttpSession session = request.getSession();
		//获取今天时间
		String yl = util_Date.dateToStr1(new Date(), "yyyy年MM月dd");
		
	 	String lunar = util_lunarDate.getLunar();
	 	lunar = lunar.substring(5, lunar.length());
	 	session.setAttribute("nowdate", yl+"日（"+lunar+"）");
	 	request.setAttribute("nowdate", yl+"日（"+lunar+"）");
		String p = request.getParameter("page");
		String r = request.getParameter("rows");
		int page=1,rows=10;
		if(util_Empty.strEmpty(p)){
			page=Integer.valueOf(p);
		}
		if(util_Empty.strEmpty(r)){
			rows=Integer.valueOf(r);
		}
		String hql="from BornInfo where 1=1 ";
		String counthql = " select count(*) from BornInfo where 1=1 ";
		/**要追加的条件*/
		String condition="";
		
		
		Object[] param = new Object[]{};
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String level = request.getParameter("level");
		
		if(util_Empty.strEmpty(name)){
			condition = condition + "and name like '%"+name+"%'";
		}
		if(util_Empty.strEmpty(type)){
			condition = condition + "and type like '%"+type+"%'";
		}
		if(util_Empty.strEmpty(level)){
			condition = condition + "and level like '%"+level+"%'";
		}
		
		condition = condition + " order by level asc";
		
		List<BornInfo> list = bornInfoService.getBornInfoList(hql+condition, param, page, rows);
		int total = bornInfoService.getTotalNum(counthql+condition, param);
		util_Json.jsonForEasyUI(list, total, response);
	}
	
	/**
	 * 添加或者修改
	 * @param bornInfo
	 * @param msg
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/saveOrUptBornInfo.do")
	public void saveOrUptBornInfo(BornInfo bornInfo,String msg,HttpServletResponse  response,HttpServletRequest request){
		int i = 1;
		BornInfo  b = new BornInfo();
		b = bornInfo;
		/**算年龄开始**/
		String borntime = b.getYlborntime();
		String age = "";
		if(util_Empty.strEmpty(borntime)){
			age = util_age.getage(borntime);
		}
		if(b.getName().equals("grandmother")){
			b.setAge("79");
		}else{
			b.setAge(age);
		}
		
		/**算年龄结束**/
		if(util_Empty.strEmpty(msg)){
			if("add".equals(msg)){//添加
				String id = this.getid();
				b.setId(id);
				b.setCreatetime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd HH:mm:ss"));
				i = bornInfoService.saveBornInfo(b);
			}else if("upt".equals(msg)){//修改
				b.setLastuptime(util_Date.dateToStr1(new Date(), "yyyy-MM-dd HH:mm:ss"));
				i = bornInfoService.updateBornInfo(b);
			}
		}//msg非空
		util_Json.jsonPrintln(i+"", response);
	}
	
	
	/**
	 * 查看BornInfo(或者修改前的跳转)
	 * @param id
	 * @param request
	 */
	@RequestMapping(value="/checkBornInfo.do")
	public String checkBornInfo(String id,String msg,HttpServletRequest request){
		BornInfo  b = new BornInfo();
		b = bornInfoService.getBornInfoModelByID(id);
		request.setAttribute("bornInfo", b);
		request.setAttribute("msg", msg);
		return "/webjsp/schedulejsp/bornInfo_detail.jsp";
	}
	
	/**
	 * 删除BornInfo
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delBornInfo.do")
	public void delBornInfo(String id,String msg,HttpServletRequest request,HttpServletResponse  response){
		//msg single(单行删除) more(多行删除)
		String message="success";
		Map<String,String> m = new HashMap<String,String>();
		BornInfo  b = new BornInfo();
		b = bornInfoService.getBornInfoModelByID(id);
		//int i = bornInfoService.delBornInfo(b);
		//生日禁止删除只能状态改为del
		b.setState("del");
		int i = bornInfoService.updateBornInfo(b);
		if(i==2){
			message = "fail";
		}
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}
	
	/**
	 * 生日提醒
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/tixing.do")
	public void tixing(HttpServletRequest request,HttpServletResponse  response){
		 	String p = request.getParameter("page");
			String r = request.getParameter("rows");
			int page=1,rows=10;
			if(util_Empty.strEmpty(p)){
				page=Integer.valueOf(p);
			}
			if(util_Empty.strEmpty(r)){
				rows=Integer.valueOf(r);
			}
		 	String yl = util_Date.dateToStr1(new Date(), "yyyy-MM-dd");
		 	yl = yl.substring(5, yl.length());
		 	String lunar = util_lunarDate.getLunar();
		 	lunar = lunar.substring(5, lunar.length());
		 	String lunar1 = lunar;
		 	lunar1=lunar1.replaceAll("廿", "二十");
		 	lunar1=lunar1.replaceAll("卅", "三十");
		 	//lunar1(十二月三十)lunar(十二月卅十)
		 	List<BornInfo> list = new ArrayList<BornInfo>();
		 	String counthql = "select count(*) from BornInfo where ylborntime like '%"+yl+"%' or nlborntime like '%"+lunar+"%' or nlborntime like '%"+lunar1+"%' ";
		 	String hql = "from BornInfo where ylborntime like '%"+yl+"%' or nlborntime like '%"+lunar+"%' or nlborntime like '%"+lunar1+"%'  order by level desc";
		 	Object[] param = new Object[]{};
		 	list = bornInfoService.getBornInfoList(hql, param , page, rows);
		 	int total = bornInfoService.getTotalNum(counthql, param);
		 	util_Json.jsonForEasyUI(list, total, response);
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 获取ID
	 * @return
	 */
	public String getid(){
		List<BornInfo> list = new ArrayList<BornInfo>();
		list = bornInfoService.getBornInfoList("from BornInfo ", new Object[]{});
		int listsize=0;
		if(util_Empty.listEmpty(list)){
			listsize=list.size();
		}
		int length = 4;//起始长度为4位(从1000开始)
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
		String id="";
		List<BornInfo> list2 = new ArrayList<BornInfo>();
		do{
			id = util_RandomCode.bigNum(length);
			list2 = bornInfoService.getBornInfoList("from User where id = ?", new Object[]{id});
		}while(util_Empty.listEmpty(list2));//false的时候停止
		return id;
	}
}
