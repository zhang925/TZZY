package com.zzy.util.showpictrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 显示电脑固定盘符下的图片
 * tomcat 能识别 项目下的图片但是 识别不出 例如F:/123.jpg的图片
 * 
 * @author zzy
 *
 */

@Controller
@RequestMapping(value = "/showPictureInComputer")
public class showPictureInComputer {
	
	
	/**
	 * 页面上图片地址应该写上
	 * src="showPictureInComputer/showPictureComputer.do?picturesrc=图片盘符地址"
	 * @param picturesrc 图片盘符地址[F:/123.jpg]
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/showPictureComputer.do")
	public static void showPictureComputer(String picturesrc ,HttpServletRequest request,HttpServletResponse response){
		FileInputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(picturesrc);
			out = response.getOutputStream();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte buffer[] = new byte[1024];
		int len = 0;
		try {
			while((len=in.read(buffer))>0){
				out.write(buffer, 0, len); 
			}
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}

	

