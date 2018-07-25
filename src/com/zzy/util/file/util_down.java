package com.zzy.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

public class util_down {
	
	/**
	 *	下载的通用方法 
	 * @param src 要下载的文件路径包括要下载的文件的名字
	 * @param downname 下载时的默认文件名字
	 * @param type	下载时生成文件的格式[后缀doc,png,txt等]
	 * @param response
	 * @param request
	 */
	
	public static void downForCommon(String src,String downname,String type,HttpServletResponse response,HttpServletRequest request) {
	
		InputStream inStream = null;
			try {
				//这里下载的是服务器本地文件例如  resources/123.doc
				inStream = new FileInputStream(src);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				//文件不存在
				return ;
			}// 文件的存放路径
	        // 设置输出的格式
	        response.reset();
	        response.setContentType("bin");
		try {
			downname = new String(downname.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	       response.addHeader("Content-Disposition", "attachment; filename=\""+ downname +"."+type+ "\"");
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
	
	
	
	
	
	
	
	/**
	 * 下载文件
	 * @param path(下载文件的路径和文件名)
	 * @param response
	 * @return
	 */
	public static HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            @SuppressWarnings("unused")
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

	/**
	 *  下载本地文件
	 * @param path(文件地址)
	 * @param filename(文件名)
	 * @param response
	 * @return boolean
	 */
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

    /**
     * 下载网络文件
     * @param response
     * @throws MalformedURLException
     */
    public static void downloadNet(HttpServletResponse response) throws MalformedURLException {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        URL url = new URL("windine.blogdriver.com/logo.gif");

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("c:/abc.gif");

            byte[] buffer = new byte[1204];
            @SuppressWarnings("unused")
			int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
 
    /**
     * 下载
     * 支持在线打开文件的一种方式
     * @param filePath(路径加上文件名字)
     * @param response
     * @param isOnLine(是否在线打开)
     */
    public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine){
        File f = new File(filePath);
        if (!f.exists()) {
            try {
				response.sendError(404, "File not found!");
			} catch (IOException e) {
				e.printStackTrace();
			}
            return;
        }
        BufferedInputStream br = null;
		try {
			br = new BufferedInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = null;
			try {
            	u = new URL("file:///" + filePath);
				response.setContentType(u.openConnection().getContentType());
			} catch (IOException e) {
				e.printStackTrace();
			}
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = null;
		try {
        	out = response.getOutputStream();
        	while ((len = br.read(buf)) > 0){
        		out.write(buf, 0, len);
        	}
			br.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}
