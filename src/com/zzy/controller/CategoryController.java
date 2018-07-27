package com.zzy.controller;


import com.zzy.controller.datagridlist.Datagrid;
import com.zzy.model.Category;
import com.zzy.model.User;
import com.zzy.service.CategoryService;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
@RequestMapping(value = "/category")
//http://localhost:8080/api/category/list restfull风格
public class CategoryController {
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UtilService utilService;

	@RequestMapping(value="list")
	public String CategoryList(HttpServletRequest request,HttpServletResponse response){
		return "/webjsp/category/category_list.jsp";
	}
	/**
	 * 查看Category列表
	 * @return
	 */
	@RequestMapping(value="gird")
	public void gird(HttpServletRequest request,HttpServletResponse response){
		Datagrid.list(request,response,Category.class,utilService);
	}
	
	
	/**添加Category实体*/
	@RequestMapping(value="opt")
	public void saveCategory(Category category,String msg,HttpServletRequest request,HttpServletResponse response){
		int i=1;
		if("upt".equals(msg)){
			i = categoryService.updateCategory(category);
		}else{
			category.setUser((User) request.getSession().getAttribute("user"));
			i=categoryService.saveCategory(category);
		}
		util_Json.jsonPrintln(i+"", response);
	}
	/**根据id修改前Category的跳转*/
	@RequestMapping(value="jump")
	public String goCategoryPage(String id,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("category", categoryService.getCategoryModel(id));
		request.setAttribute("msg", request.getParameter("msg"));
		return "/webjsp/category/category.jsp";
	}
	
	/**根据id删除Category*/
	@RequestMapping(value="del")
	public void delCategory(String id,String msg,HttpServletRequest request,HttpServletResponse response){
		int i = categoryService.delCategoryByID(id);
		//msg single(单个删除)more(多个删除)
		String message = "fail";
		if(i==1){
			message="success";
		}
		Map<String,String> m = new HashMap<String,String>();
		m.put("message", message);
		util_Json.jsonPrintModel(m, response);
	}


}
