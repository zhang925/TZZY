package com.zzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.FileOutputStream;  
 



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;   
  



import org.apache.commons.fileupload.FileItemIterator;  
import org.apache.commons.fileupload.FileItemStream;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
import org.apache.commons.fileupload.util.Streams;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;













import com.zzy.model.Files;
import com.zzy.service.FilesService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_Json;
import com.zzy.util.util_Md5;
import com.zzy.util.file.util_down;
import com.zzy.util.file.util_uploadForCommon;
/**
 * 文件控制层(FilesController)
 * @author zzy
 *
 */
@Controller
@RequestMapping(value = "/fileController")
public class FileController {
	
	@Autowired 
	private  FilesService filesService;
	@Autowired
	private UtilService utilService;
		
	/**
	 * 后台easyUI显示文件列表
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/fileslist.do")
	public void fileslist(HttpServletResponse  response,HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		String hql = "from Files where state!='del'";
		String counthql = " select count(*) from Files where state!='del' ";
		/**要追加的条件*/
		String condition="";
		
		condition = condition + " order by createtime desc";
		int total=filesService.getTotalNum(counthql+condition, new Object[]{});
		
		List<Files> list = new ArrayList<Files>();
		int p=1,r=10;
		if(util_Empty.strEmpty(page)){
			p=Integer.valueOf(page);
		}
		if(util_Empty.strEmpty(rows)){
			r=Integer.valueOf(rows);
		}
		list=filesService.getFilesPage(hql+condition, new Object[]{},p,r);
		util_Json.jsonForEasyUI(list, total, response);
		
	}
	
	/**
	 * 下载文件.
	 * */
	public static void downloadLocal(String filename,String path,HttpServletResponse response){
        // 下载本地文件
        //String fileName = "Operator.doc".toString(); // 文件的默认保存名
        String fileName = filename.toString(); // 文件的默认保存名
        try {
			fileName =  new String(filename.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			return ;
		}
        // 读到流中
        InputStream inStream = null;
		try {
			//inStream = new FileInputStream("c:/Operator.doc");
			inStream = new FileInputStream(path+filename);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			//文件不存在
			return ;
		}// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[1024000];
        int len;
        try {
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }
     
    }
	
	
	
	@RequestMapping(value="/downfiles.do")
	public  void downfiles(HttpServletRequest request,HttpServletResponse response){
		String fid = request.getParameter("fid");
		Files files = filesService.getFilesModelByFID(fid);
		String filename = files.getFilename();
		String path = files.getFilesrc();
		downloadLocal( filename, path, response);
	}
	
	
	
	
	/**
	 * 测试Ajax上传文件
	 * @param filename
	 * @param path
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/uploadAjax.do")
	public static void uploadAjax(String filename,String path,HttpServletRequest request,HttpServletResponse response){
		
	}

	
	
	
	
	
	

	

	
	
}
