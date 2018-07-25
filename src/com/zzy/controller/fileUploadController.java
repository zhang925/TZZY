package com.zzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zzy.model.User;
import com.zzy.service.BornInfoService;
import com.zzy.service.UserService;

/**
 * 这个是文件上传的通用方法
 * 
 * @author zzy
 *
 */
@Controller
@RequestMapping(value = "/fileUploadController")
public class fileUploadController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * from表单 单文件上传的方法，
	 * 主要修改上传文件的地址就行[src01]
	 * @param file	此参数不用管
	 * @param request
	 * @param model	此参数不用管
	 * @return
	 */
	@RequestMapping(value = "/uploadtest.do")
	public String uploadtest(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request, ModelMap model) {
		
		/**这个是拼接的上传地址*/
		String src01 = "/resources/upload/touxiang";
		/**最终的上传地址*/
		String path = request.getSession().getServletContext().getRealPath(src01);
		/**文件的名字*/
		String fileName="sss.txt";
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
		model.addAttribute("fileUrl", src01+ "/"+ fileName);
		
		return "error.jsp";
		
		/*
		<form action="uploadtest.do" method="post" enctype="multipart/form-data">  
			<input type="file" name="file" /> 
			<input type="submit" value="Submit" />
		</form>  
		 */
	}
	
	
	
	
	/**
	 * 用户 头像上传
	 * 
	 * from表单 单文件上传的方法，
	 * 
	 * 主要修改上传文件的地址就行[src01]
	 * @param file	此参数不用管
	 * @param request
	 * @param model	此参数不用管
	 * @return
	 */
	@RequestMapping(value = "/uploadheadphoto.do")
	public String uploadheadphoto(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request, ModelMap model) {
		
		/**文件的名字*/
		String fileName = file.getOriginalFilename();
		
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		
		/**这个是拼接的上传地址*/
		String src01  = "resources/upload/image/";
		
	
		
		if(u!=null){
			src01 = src01 + u.getUid()+"/headphoto/";
			
			u.setPhotoid(src01+fileName);
			userService.updateUser(u);
			
			//刷新session
			session.removeAttribute("user");
			session.setAttribute("user", u);
			
		}else{
			return "webjsp/userjsp/user_self.jsp";
		}
		
		
		/**最终的上传地址*/
		String path = request.getSession().getServletContext().getRealPath(src01);
		
		
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
		model.addAttribute("fileUrl", src01+ "/"+ fileName);
		return "webjsp/userjsp/user_self.jsp";
	}

	/**
	 * 
	 * 解决 读取 某个盘符下面的图片不显示的问题
	 * 页面上这样写就可以了
	 * <img src="fileUploadController/testFileToTocat.do" width="100px" height="100px"/>
	 * 
	 * @throws IOException 
	 * 
	 */
	
	@RequestMapping(value = "/testFileToTocat.do")
	public void testFileToTocat(HttpServletRequest request,HttpServletResponse  response) throws IOException{
		String type = request.getParameter("type");
		 //request.getRealPath("/")
		FileInputStream in  = null;
		if(type!=null && !"".equals(type)){
			in = new FileInputStream("F:/share.jpg");
		}else{
			in = new FileInputStream("F:/123.jpg");
		}
		  
		  //创建输出流
		  OutputStream out = response.getOutputStream();
		   //创建缓冲区
		     byte buffer[] = new byte[1024];
		     int len = 0;
		              //循环将输入流中的内容读取到缓冲区当中
		     while((len=in.read(buffer))>0){
		              //输出缓冲区的内容到浏览器，实现文件下载
		             out.write(buffer, 0, len);
		           }
		   //关闭输出流
		       out.close();
		           //关闭文件输入流
		           in.close();
		     
		 }
}