package com.zzy.controller.district;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.LinkLoopException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zzy.dao.BaseDao;
import com.zzy.model.district.District;
import com.zzy.service.DistrictService;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;


@Controller
@RequestMapping(value = "/districtController")
public class DistrictController{ 
	@Autowired
	private UserService userService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private UtilService utilService;
	/**
	 * 查看District列表
	 * @return
	 */
	@RequestMapping(value="districtlist.do")
	public void Districtlist(HttpServletRequest request,HttpServletResponse response){
		
		String p = request.getParameter("page");
		String r = request.getParameter("rows");
		int page=1,rows = 10;
		if(util_Empty.strEmpty(p)){
			page = Integer.valueOf(p);
		}
		if(util_Empty.strEmpty(r)){
			rows = Integer.valueOf(r);
		}
		/*
		List<District> list = new ArrayList<District>();
		String hql="from District where 1=1 ";
		
		String citycode = request.getParameter("citycode");
		String xzid = request.getParameter("xzid");
		String name = request.getParameter("name");
		String where = "";
		if(util_Empty.strEmpty(xzid)){
			where = where + " and xzid like '%"+xzid+"%' ";
		}
		if(util_Empty.strEmpty(name)){
			where = where + " and name like '%"+name+"%' ";
		}
		if(util_Empty.strEmpty(citycode)){
			where = where + " and citycode like '%"+citycode+"%' ";
		}
		hql = hql+where;
		list=districtService.getDistrictPage(hql,new Object[]{}, page, rows);
		int total=districtService.getTotalNum("select count(*) from District where 1=1"+where, new Object[]{});
		*/
		
		
		
		
		
		
		//以上注释是hql的非 	criteria 查询 这里尝试	criteria 查询 [Query]
		Session session = utilService.gethibernatesession();
		List<District> list = new ArrayList<District>();
		/*
		String hql="from District where id in (:idlist)"; 
		Query query = session.createQuery(hql);
		Object a[] ={1,2,3};
		query.setParameterList("idlist", a); 
		list = query.list();
		session.flush();
		session.close();
		*/
		//DetachedCriteria 
		Criteria criteria = session.createCriteria(District.class); 
		String citycode = request.getParameter("citycode");
		String xzid = request.getParameter("xzid");
		String name = request.getParameter("name");
		if(util_Empty.strEmpty(xzid)){
			criteria.add(Restrictions.like("xzid", "%"+xzid+"%"));
		}
		if(util_Empty.strEmpty(name)){
			criteria.add(Restrictions.like("name", name));
		}
		if(util_Empty.strEmpty(citycode)){
			criteria.add(Restrictions.like("citycode", citycode));
		}
		int total = criteria.list().size();
		int strartnum = (page-1)*rows;
		criteria.setFirstResult(strartnum);//开始的行号  
		criteria.setMaxResults(rows);//每页多少行
		list = criteria.list();
		utilService.close(session);
		util_Json.jsonForEasyUI(list, total, response);
		
		
	}
	
	
	/**添加District实体*/
	@RequestMapping(value="saveDistrict.do")
	public void saveDistrict(District District,String msg,String upuid,HttpServletRequest request,HttpServletResponse response){
		/*District cc = new District();
		cc = District;
		int i=1;
		
		if("upt".equals(msg)){
			User u = new User();
			u = userService.getUserByUID(upuid);//修改时候user实体不能传递只能用uid接收
			cc.setUid(u);
			i = DistrictService.updateDistrict(cc);
		}else{
			HttpSession session = request.getSession();
			User u = new User();
			u =(User) session.getAttribute("user");
			cc.setUid(u);//创建人实体
			cc.setCreatetime(util_Date.dateToStr1(new Date(),"yyyy:MM:dd HH:mm:ss "));//创建时间
			i=DistrictService.saveDistrict(cc);
			
		}
		util_Json.jsonprint(i+"", response);*/
		
	}
	
	
	/**根据id删除District*/
	@RequestMapping(value="delDistrict.do")
	public void delDistrict(String id,String msg,HttpServletRequest request,HttpServletResponse response){
		int i = districtService.delDistrictByID(Integer.valueOf(id));
		//msg single(单个删除)more(多个删除)
		String message = "fail";
		if(i==1){
			message="success";
		}
		Map<String,String> m = new HashMap<String,String>();
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}
	
	
	/**根据ID查看或者修改
	 * District
	 * 实体
	 * 
	 * */
	
	@RequestMapping(value="goAddOrUptDistrict.do")
	public String goAddOrUptDistrict(int id,HttpServletRequest request,HttpServletResponse response){
		District district=districtService.getDistrictModel(id);
		request.setAttribute("district", district);
		request.setAttribute("type", request.getParameter("type"));
		
		return "/webjsp/districtjsp/district_addorupt.jsp";
	}
	
	
	/**获取当天经典语录(或者是最新的)*/
	
	@RequestMapping(value="getDistrictNow.do")
	public void getDistrictNow(HttpServletRequest request,HttpServletResponse response){
		District District= new District();
		String hql="from District  order by createtime desc";
		Object param[] = new Object[]{};
		List<District> lc = new ArrayList<District>();
		lc = districtService.getAllDistrict(hql, param);
		if(util_Empty.listEmpty(lc)){
			District = lc.get(0);
		}
		Map<Object,Object> map= new HashMap<Object,Object>();
		map.put("District", District);
		util_Json.jsonPrintModel(map, response);
	}
	
	
	

	/**
	 * 导入经典语录
	 * 
	 * 这里暂时不考虑
	 * 
	 * 上传的问题
	 * 
	 */
	@RequestMapping(value="/importDistrict.do")
	public void importDistrict(HttpServletRequest request){
		
		POIFSFileSystem fs;
		HSSFWorkbook wb = null;
		HSSFSheet sheet;
		InputStream is = null;
		try {
			String filesrc= request.getSession().getServletContext().getRealPath("/")
					+"/resources/upload/temp/district1.xls";
			is = new FileInputStream(filesrc);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		//[i = 0 从第一行开始读] 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			//实体
			District temp = new District();
			//第 1 个cell
			HSSFCell cell00 = sheet.getRow(i).getCell(0);
			if(cell00!=null){
				cell00.setCellType(cell00.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell00.getStringCellValue())){
					//System.out.print("第 1 个cell:"+cell00.getStringCellValue());
					temp.setXzid(cell00.getStringCellValue());
				}
			}
			//第 2 个cell
			HSSFCell cell01 = sheet.getRow(i).getCell(1);
			if(cell01!=null){
				cell01.setCellType(cell01.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell01.getStringCellValue())){
					//System.out.print("第 2 个cell:"+cell01.getStringCellValue());
					temp.setName(cell01.getStringCellValue());
				}
			}
		
			//第 3 个cell
			HSSFCell cell02 = sheet.getRow(i).getCell(2);
			if(cell02!=null){
				cell02.setCellType(cell02.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell02.getStringCellValue())){
					//System.out.print("第 3 个cell:"+cell02.getStringCellValue());
					temp.setParentid(cell02.getStringCellValue());
				}
			}
			
			//第 4 个cell
			HSSFCell cell03 = sheet.getRow(i).getCell(3);
			if(cell03!=null){
				cell03.setCellType(cell03.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell03.getStringCellValue())){
					//System.out.print("第 4 个cell:"+cell03.getStringCellValue());
					temp.setShotname(cell03.getStringCellValue());
				}
			}
			//第 5 个cell
			HSSFCell cell04 = sheet.getRow(i).getCell(4);
			if(cell04!=null){
				cell04.setCellType(cell04.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell04.getStringCellValue())){
					//System.out.print("第 5 个cell:"+cell04.getStringCellValue());
					temp.setLeveltype(cell04.getStringCellValue());
				}
			}
			
			//第 6 个cell
			HSSFCell cell05 = sheet.getRow(i).getCell(5);
			if(cell05!=null){
				cell05.setCellType(cell05.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell05.getStringCellValue())){
					//System.out.print("第 6 个cell:"+cell05.getStringCellValue());
					temp.setZipcode(cell05.getStringCellValue());
				}
			}
			
			//第 7 个cell
			HSSFCell cell06 = sheet.getRow(i).getCell(6);
			if(cell06!=null){
				cell06.setCellType(cell06.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell06.getStringCellValue())){
					//System.out.print("第 7 个cell:"+cell06.getStringCellValue());
					temp.setCitycode(cell06.getStringCellValue());
				}
			}
			
			//第 8 个cell
			HSSFCell cell07 = sheet.getRow(i).getCell(7);
			if(cell07!=null){
				cell07.setCellType(cell07.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell07.getStringCellValue())){
					//System.out.print("第 8 个cell:"+cell07.getStringCellValue());
					temp.setMergername(cell07.getStringCellValue());
				}
			}
			
			//第 9 个cell
			HSSFCell cell08 = sheet.getRow(i).getCell(8);
			if(cell08!=null){
				cell08.setCellType(cell08.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell08.getStringCellValue())){
					//System.out.print("第 9 个cell:"+cell08.getStringCellValue());
					temp.setLng(cell08.getStringCellValue());
				}
			}
			
			//第 10 个cell
			HSSFCell cell09 = sheet.getRow(i).getCell(9);
			if(cell09!=null){
				cell09.setCellType(cell09.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell09.getStringCellValue())){
					//System.out.print("第 10 个cell:"+cell09.getStringCellValue());
					temp.setLat(cell09.getStringCellValue());
				}
			}
			
			//第 11个cell
			HSSFCell cell010 = sheet.getRow(i).getCell(10);
			if(cell010!=null){
				cell010.setCellType(cell010.CELL_TYPE_STRING);
				if(util_Empty.strEmpty(cell010.getStringCellValue())){
					//System.out.print("第 11 个cell:"+cell010.getStringCellValue());
					temp.setPinyin(cell010.getStringCellValue());
				}
			}
			districtService.saveDistrict(temp);
			continue;
		}//循环excel结束
		
		
	}
	
	
	
	/**
	 * 导出经典语录
	 */
	@RequestMapping(value="/exportDistrict.do")
	public void exportDistrict(HttpServletRequest request,HttpServletResponse response){
		/*String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String createtime = request.getParameter("createtime");
		String hql="from District where 1=1 ";
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
		List<District> list = DistrictService.getAllDistrict(hql, param);
		Export.export("经典语录记录","经典语录记录",District.class, list,  response);*/
		
	}
	
	
	
	
	/**
	 *  单文件上传的方法，
	 * 主要修改上传文件的地址就行[src01]
	 * @param file	此参数不用管
	 * @param request
	 * @param model	此参数不用管
	 * @return
	 */
	@RequestMapping(value = "/uploadtest.do")
	public void uploadtest(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request, ModelMap model) {
		
		/**这个是拼接的上传地址*/
		String src01 = "/resources/upload/temp";
		/**最终的上传地址*/
		String path = request.getSession().getServletContext().getRealPath(src01);
		/**文件的名字	确切的来说当很多用户访问的时候 不可能定死文件名字 每个用户下 一个文件	*/
		
		String fileName="district.xls";
		File targetFile = new File(path, fileName);
		
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**如果是图片的话加上这个可以在页面显示*/
		//model.addAttribute("fileUrl", src01+ "/"+ fileName);
		
	}
	
}
