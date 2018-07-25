package com.zzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zzy.model.User;
import com.zzy.service.UserService;
import com.zzy.service.UtilService;
import com.zzy.test.Testdingwei;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.file.util_txtfile;

@Controller
@RequestMapping(value = "/testController")
public class TestController {

	@Autowired
	private UserService userService;
	@Autowired
	private UtilService utilService;

	@RequestMapping(value = "/getueditorText")
	public void getueditorText(HttpServletRequest request,
			HttpServletResponse response) {
		// String editor = request.getParameter("editor");
		// 对src 处理：
		/*
		 * if(editor.contains("src=\"../resources/upload/ueditor/")){ editor =
		 * editor.replace("../", ""); }
		 */

	}

	@RequestMapping(value = "/getueditorFile")
	public void getueditorFile(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {

	}

	@RequestMapping(value = "/phoneuserlist")
	public String phoneuserlist(HttpServletRequest request,
			HttpServletResponse response) {
		List<User> list = new ArrayList<User>();
		int page = 1;
		int rows = 5;
		String p = request.getParameter("page");
		if (util_Empty.strEmpty(p)) {
			page = Integer.valueOf(p);
		}
		list = userService.getUserPage("from User ", new Object[] {}, page,
				rows);
		request.setAttribute("list", list);
		String type = request.getParameter("type");

		request.setAttribute("page", page);

		if (type == null || "".equals(type)) {
			return "phone/test/test.jsp";
		} else {
			return "phone/test/pagetest.jsp";
		}

	}

	@RequestMapping(value = "/phoneuserlist2")
	public void phoneuserlist2(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<User> list = new ArrayList<User>();
		int page = 1;
		int rows = 4;
		String p = request.getParameter("curPage");
		if (util_Empty.strEmpty(p)) {
			page = Integer.valueOf(p);
		}
		list = userService.getUserPage("from User ", new Object[] {}, page,
				rows);

		int pageCount = userService.getTotalNum("select count(*) from User",
				new Object[] {});

		pageCount = pageCount % rows == 0 ? pageCount / rows : pageCount / rows
				+ 1;

		Map a = new HashMap();
		a.put("list", list);
		a.put("page", page + 1);
		a.put("pageCount", pageCount);// 总页数
		// 这个需要总页数进行判断比较麻烦

		util_Json.jsonPrintModel(a, response);

	}

	@RequestMapping(value = "/testupload")
	public void testupload() {
		
	}

	@RequestMapping(value = "/test")
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//生成一个空文件 
//		File file = new File("F:/aaa.txt");
//		file.createNewFile();
		
		
		util_txtfile.writeTxtFile("内容 。。。。。。。。。。。", new File("F:/oracle.txt"));
	}
	
	

	    
}
