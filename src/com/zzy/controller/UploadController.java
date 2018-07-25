package com.zzy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzy.model.Files;
import com.zzy.model.User;
import com.zzy.service.FilesService;
import com.zzy.service.UtilService;
import com.zzy.util.util_Date;
import com.zzy.util.util_Empty;
import com.zzy.util.util_RandomCode;
import com.zzy.util.file.util_fileSize;

/**
 * 这里是 网上找的插件
 * 
 * 多文件上传
 * 下载的模板
 * @author zzy
 *
 */
@Controller
@RequestMapping(value = "/uploadController")
public class UploadController  {
	@Autowired 
	private FilesService filesService;
	@Autowired
	private UtilService utilService;
	
	//上传文件的保存路径  
    protected String configPath = "upload/widget";  
  
    protected String dirTemp = "upload/widget/temp";  
      
    protected String dirName = "file";  
   
	@RequestMapping(value="/upload")
	protected void upload(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");  
        res.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = res.getWriter();  
        //文件保存目录路径  
        String savePath = "E:/javaupload/";  
        //String savePath = this.getServletContext().getRealPath("/") + configPath;  
        // 临时文件目录   
        //String tempPath = this.getServletContext().getRealPath("/") + dirTemp;
        String path = req.getContextPath();
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
        String tempPath = basePath;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
        String ymd = sdf.format(new Date());  
        savePath += "/" + ymd + "/";  
        //创建文件夹  
        File dirFile = new File(savePath);  
        if (!dirFile.exists()) {  
            dirFile.mkdirs();  
        }  
       
        tempPath += "/" + ymd + "/";  
        //创建临时文件夹  
        File dirTempFile = new File(tempPath);  
        if (!dirTempFile.exists()) {  
            dirTempFile.mkdirs();  
        }  
          
        DiskFileItemFactory  factory = new DiskFileItemFactory();  
        factory.setSizeThreshold(20 * 1024 * 1024); //设定使用内存超过5M时，将产生临时文件并存储于临时目录中。     
        factory.setRepository(new File(tempPath)); //设定存储临时文件的目录。     
        ServletFileUpload upload = new ServletFileUpload(factory);  
        upload.setHeaderEncoding("UTF-8");
        
        try {  
            List items = upload.parseRequest(req);  
            System.out.println("items = " + items);  
            Iterator itr = items.iterator();  
            while (itr.hasNext()){  
                FileItem item = (FileItem) itr.next();  
                String fileName = item.getName();  
                long fileSize = item.getSize();
                String fileExt="";
                if (!item.isFormField()) { 
                	String newFileName = "";
                	//文件的 格式
                    fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
                	
                	//String newFileName = df.format(new Date()) + "_" + new Random().nextInt(100) + "." + fileExt;
                	//判断文件是否存在存在重命名
                    String src = savePath+fileName;
                	File file=new File(src);   
                    if(file.exists()){//判断文件是否存在
                    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
                    	String newFileName1 = "_"+df.format(new Date());
                    	fileName=fileName.substring(0,fileName.lastIndexOf("."))+newFileName1+"."+fileExt;
                    }
                    
                    
                    newFileName = fileName;
                    
                    try{  
                        File uploadedFile = new File(savePath, newFileName);  
                        OutputStream os = new FileOutputStream(uploadedFile);  
                        InputStream is = item.getInputStream();  
                        byte buf[] = new byte[1024];//可以修改 1024 以提高读取速度  
                        int length = 0;    
                        while( (length = is.read(buf)) > 0 ){    
                            os.write(buf, 0, length);    
                        }    
                        //关闭流    
                        os.flush();  
                        os.close();    
                        is.close();    
                        System.out.println("上传成功！路径："+savePath+"/"+newFileName);
                        out.print(savePath+"/"+newFileName); 
                        /************	保存文件信息	*************/
                        Files f = new Files();
                        f.setCreatetime(util_Date.dateToStr1(new Date(), "yyyy:MM:dd HH:mm:ss"));
                        String fid = getfid();
                        f.setFid(fid);
                        f.setFilename(newFileName);
                        f.setFilesize(util_fileSize.bToOther1024(fileSize));
                        f.setFilesrc(savePath);
                        f.setFiletype(fileExt);//文件格式
                        f.setState("0");
                        HttpSession session = req.getSession();
                        User u = new User();
                        u = (User) session.getAttribute("user");
                        f.setUid(u);
                        filesService.saveFiles(f);
                    }catch(Exception e){  
                        e.printStackTrace();  
                    }  
                }else {  
                    String filedName = item.getFieldName();  
                    System.out.println(new String(item.getString().getBytes("iso-8859-1"),"utf-8"));  
                    System.out.println("FieldName："+filedName);  
                    // 获得裁剪部分的坐标和宽高
                    System.out.println("String："+item.getString());  
                }             
            }   
              
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }  
        out.flush();  
        out.close(); 
		
	}
	
	/**
	 * 通过查询获取FID
	 * @return
	 */
	public String getfid(){
		List<Files> list = new ArrayList<Files>();
		list = filesService.getFilesByHql("from Files", new Object[]{});
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
		String fid="";
		List<Files> list2 = new ArrayList<Files>();
		do{
			fid = util_RandomCode.bigNum(length);
			list2 = filesService.getFilesByHql("from Files where fid = ?", new Object[]{fid});
		}while(util_Empty.listEmpty(list2));//false的时候停止
		return fid;
	}
}
