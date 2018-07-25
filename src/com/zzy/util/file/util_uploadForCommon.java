package com.zzy.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class util_uploadForCommon {
	
	/**
	 * 
	 * @param file  例：File file = new File("F:/shayatou.jpg");
	 * @param savePath 例：	/resources/upload/touxiang/shayatou.jpg
	 * @param response
	 * @param request
	 */
	public static void uploadCommon(File file,String savePath,HttpServletResponse response,HttpServletRequest request){
		byte[] buffer=new byte[10240];
		//读取文件
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//保存文件并设置保存目录路径
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(savePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!file.exists()) {  
			file.mkdirs();  
        }else{
        	//String filesize = file.length()/1024+"KB";
    		int length = 0;
			try {
				length = fis.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
    		while(length>0){
    			//每次写入length长度内容
    			try {
					fos.write(buffer,0, length);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    		try {
				fis.close();
				fos.flush();
	    		fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    		
    		
        }  
	
	}
}
