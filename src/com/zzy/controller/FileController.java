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
import java.text.SimpleDateFormat;
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
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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




	/**
	 *  上传,支持多文件
	 *
	 * <form id="uploadFileForm" action="#" enctype="multipart/form-data" >
	 <input type="file" id="file" name="file" />
	 </form>
	 *   var formData = new FormData($("#faward" )[0]);
	 $.ajax({
	 url:"${path}/file/upload",
	 type:"POST",
	 cache:false,
	 async:false,
	 processData: false,
	 contentType: false,
	 data:formData,
	 dataType:"json",
	 success:function(data){},
	 error:function(data){}
	 });
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(@RequestParam("file") CommonsMultipartFile files[], HttpServletRequest request, HttpServletResponse response){
		String resMsg = "";
		try {
			if(files!=null && files.length>0){
				for(CommonsMultipartFile file : files){
					//String fileName = file.getOriginalFilename();
					//String path="/Users/loukai/easylife/files/"+new Date().getTime()+file.getOriginalFilename();
					//服务器的真是路径，发布项目的 根路径 F：aa/bb
					String basePath = request.getSession().getServletContext().getRealPath("/");
					//按照时间 建立一个文件 每天一个文件夹
					SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");//格式化 文件夹 名字
					String timestr =  sd.format(new Date());
					SimpleDateFormat sd2 = new SimpleDateFormat("yyyyMMddHHmmss");//格式化 文件 名字的前缀
					//因为 Spring MVC 对静态 资源的控制原因，这里的file 上传到，"WEB-INF/skin/"下面
					String originname = file.getOriginalFilename();//文件的原名字
					String addPath="uploadfile/"+timestr+"/"+sd2.format(new Date())+originname;
					String path = basePath + addPath;
					File newFile=new File(path);
					if(!newFile.exists()){
						newFile.mkdirs();
					}
					file.transferTo(newFile);//通过CommonsMultipartFile的方法直接写文件
					response.setCharacterEncoding("UTF8");
					resMsg = addPath + ";" +resMsg;
				}

			}
			if(resMsg.length()>1){//去掉最后一个分号
				resMsg=resMsg.substring(0,resMsg.length()-1);
			}
			response.getWriter().write(resMsg);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
			resMsg = "error";
		}
	}


	@RequestMapping(value = "down")
	@ResponseBody
	public void down(String fileid,HttpServletRequest request, HttpServletResponse response){
		String src = filesService.getFilesModelByFID(fileid).getFilesrc();
		downFileBySrc( request,  response,  src);
	}

	/**
	 *  文件下载 根据文件地址 下载
	 * @param request
	 * @param response
	 * @param src //文件路径
	 */
	public void  downFileBySrc(HttpServletRequest request, HttpServletResponse response, String src){
		String downLoadPath = "";// 下载地址，是真实的 盘符 HttpServletResponse
		// storeName 类似  skin/uploadfile/noticefile/20180604/20180604114710QQ截图.png
		String basePath = request.getSession().getServletContext().getRealPath("/");// F:/platform/
		downLoadPath =  basePath +"WEB-INF/" +src ;
		String fileName = src.substring((src.lastIndexOf("/")+1),src.length());//获得 20180604114710QQ截图.png
		String storeName =  fileName.substring(14,fileName.length());//下载后要显示的文件名字 QQ截图.png
		try{
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			//获取项目根目录
			//String ctxPath = request.getSession().getServletContext().getRealPath("");
			//获取下载文件
			//String downLoadPath = ctxPath+"/uploadFile/"+ storeName;
			//这里暂时认为文件已经确定
			//获取文件的长度

			File file = new File(downLoadPath);
			if (!file.exists()) {//文件不存在
				try {
					response.sendError(404, "服务器文件已经丢失，下载失败!");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//return response;

			}
			long fileLength = file.length();
			//设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(storeName.getBytes("utf-8"), "ISO8859-1"));
			//设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			//获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			//输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			//关闭流
			bis.close();
			bos.close();

		}catch (Exception e){
			e.printStackTrace();
		}
		//return  response;
	}


	/**
	 *
	 * 根据文件路径删除文件
	 * @param request
	 * @param response
	 * @param src
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HttpServletRequest request, HttpServletResponse response, String src){
		response.setCharacterEncoding("utf8");
		String basePath = request.getSession().getServletContext().getRealPath("/");
		if(StringUtils.isEmpty(src)){
			return "src  necessary !";
		}
		//本项目中，文件放到 WEB-INF skin 下
		String fileName = basePath + "WEB-INF/"+ src ;
		File file = new File(fileName);
		if(!file.exists()){//文件不存在
			return "file is not exists!";
		}else{
			// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
			if (file.isFile()) {
				if (file.delete()) {
					return "success";
				} else {
					return "system exception !";
				}
			}else{
				return " the deleting is not a file ! ";
			}
		}

	}
	
}
