package com.zzy.controller;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import com.zzy.model.User;
import com.zzy.util.file.util_down;
import com.zzy.word.wordForFtltemp;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 导出Word
 * 
 * @author zzy
 * 
 */
@Controller
@RequestMapping(value = "/wordController")
public class WordController {

	@RequestMapping(value = "/generateWord.do")
	public void generateWord(HttpServletResponse response,
			HttpServletRequest request) {
		/** 这个是模板的文件名字[已经存在] */
		String filename01 = "word01.doc";
		/** 这个是生成文件的名字[生成的] */
		String filename02 = "word02.doc";

		/*** 替换word内容开始 ****/
		Map<String, String> contentMap = new HashMap<String, String>();
		contentMap.put("name", "飞翔家族");
		contentMap.put("year", "2015");
		contentMap.put("month", "13");
		/*** 替换word内容结束 ****/

		// 模板
		// 模板路径
		String mbpath = "";
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		mbpath = realPath + "resources/word/wordftl/" + filename01;
		HWPFDocument document = replaceDoc(mbpath, contentMap);
		String tempPath = "";
		if (document != null) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			try {
				document.write(byteArrayOutputStream);
				// 临时文件生成路径(真实存在)但是是在服务器里面项目里面是看不到的
				tempPath = realPath + "resources/word/wordtemp/" + filename02;
				OutputStream outputStream = new FileOutputStream(tempPath);
				outputStream.write(byteArrayOutputStream.toByteArray());
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 下载word
		String src = tempPath;
		String downname = "word文档测试";
		util_down.downForCommon(src, downname, "doc", response, request);
	}

	@RequestMapping(value = "/generateWordByFramemaker.do")
	public void generateWordByFramemaker(HttpServletResponse response,
			HttpServletRequest request) {

	}

	@RequestMapping(value = "/getPath.do")
	public void getPath(HttpServletResponse response, HttpServletRequest request) {

		// D:\worksoft\tocatexe\webapps\TZZY\
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		System.out.println("第二个路径：" + realPath);

		// D:/worksoft/tocatexe/webapps/TZZY/WEB-INF/classes/
		String basePath03 = Thread.currentThread().getContextClassLoader()
				.getResource("/").getPath();
		System.out.println("第六个路径：" + basePath03);
	}

	/**
	 * * 读取word模板并替换变量
	 * 
	 * @param templatePath
	 *            模板路径
	 * @param contentMap
	 *            要替换的内容
	 * @return word的Document
	 */
	public static HWPFDocument replaceDoc(String templatePath,
			Map<String, String> contentMap) {
		try {
			// 读取模板
			FileInputStream tempFileInputStream = new FileInputStream(new File(
					templatePath));
			HWPFDocument document = new HWPFDocument(tempFileInputStream);
			// 读取文本内容
			Range bodyRange = document.getRange();
			// 替换内容
			for (Map.Entry<String, String> entry : contentMap.entrySet()) {
				bodyRange.replaceText("${" + entry.getKey() + "}",
						entry.getValue());
			}
			return document;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * public static void main(String arg[]) { //获取word配置文件 Properties p=new
	 * Properties(); InputStream is; //String basePath =
	 * request.getSession().getServletContext().getRealPath("/");
	 * +/WEB-INF/classes/
	 * 
	 * String basePath =
	 * Thread.currentThread().getContextClassLoader().getResource
	 * ("/").getPath();
	 * 
	 * try { //获取陪着文件 is = new
	 * FileInputStream(basePath+"/downloadpath.properties"); p.load(is);
	 * is.close(); } catch (FileNotFoundException e) { e.printStackTrace(); }
	 * catch (IOException e) { e.printStackTrace(); }
	 * 
	 * String downpath = p.getProperty("path"); }
	 */

	/**
	 * ftl 生成word 失败了
	 */
	@RequestMapping(value = "/createWord.do")
	public void createWord(HttpServletResponse response,
			HttpServletRequest request) {
		Configuration configuration = new Configuration();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		getData(dataMap);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setClassForTemplateLoading(this.getClass(), "/"); // FTL文件所存在的位置
		Template t = null;
		try {
			t = configuration.getTemplate("ceshi.ftl"); // 文件名
		} catch (IOException e) {
			e.printStackTrace();
		}
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		File outFile = new File(desktopPath + "/测试frameMaker222.doc");
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getData(Map<String, Object> dataMap) {
		dataMap.put("name", "德玛西亚");
		dataMap.put("year", "2012");
		dataMap.put("month", "2");
		dataMap.put("day", "13");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("number", i);
			map.put("content", "内容" + i);
			list.add(map);
		}

		dataMap.put("listtest", list);
	}

	@RequestMapping(value = "/createWordByItextForUserinfo.do")
	public void createWordByItextForUserinfo(HttpServletResponse response,
			HttpServletRequest request) {
		User user = new User();
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");
		if (user != null) {// 用Itxt生成word信息
			// 这里最好使用英文，现在的时候在改成中文
			creatWordforUserInfo("ceshi", "用户信息", response, request,user);
			String realPath = request.getSession().getServletContext().getRealPath("/");
			realPath = realPath + "/resources/word/wordtemp/ceshi.doc";
			util_down.downForCommon(realPath, "用户信息", "doc", response, request);
		} else {// session 不存在

			return;
		}

	}

	/**
	 * 生成的word 放到 服务器下面 再次发布的时候会丢失
	 * 
	 * @param docname
	 *            生成文件的名字
	 * @param doctitle
	 *            word的标题
	 * 
	 */
	public void creatWordforUserInfo(String docname, String doctitle,
			HttpServletResponse response, HttpServletRequest request,User user) {

		int rownum = 11;//多少行
		int rows = 2;//  每行单元格数量

		int[] withs = { 45, 55 };// 每个表格的宽度

		try {

			/** 创建Document对象（word文档） */
			Rectangle rectPageSize = new Rectangle(PageSize.A4);
			rectPageSize = rectPageSize.rotate();
			// 创建word文档,并设置纸张的大小
			Document doc = new Document(PageSize.A4);
			// 文件暂时生成在 resources / word / wordtemp 目录下 新版本发布的时候自动删除
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			realPath = realPath + "/resources/word/wordtemp/";
			String fileName = realPath + docname + ".doc";

			/** 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中 */
			RtfWriter2.getInstance(doc, new FileOutputStream(fileName));
			doc.open();
			/** 标题字体 */
			RtfFont titleFont = new RtfFont("仿宋_GB2312", 15, Font.BOLD,
					Color.BLACK);
			/** 正文字体 */
			RtfFont contextFont = new RtfFont("仿宋_GB2312", 9, Font.NORMAL,
					Color.BLACK);
			/** 表格设置 */
			Table table = new Table(rows, rownum);
			/** 设置每列所占比例 */
			table.setWidths(withs);
			/** 表格所占页面宽度 */
			table.setWidth(100);
			/** 居中显示 */
			table.setAlignment(Element.ALIGN_CENTER);
			/** 自动填满 */
			table.setAutoFillEmptyCells(true);
			table.setBorderWidth(5); // 边框宽度
			table.setBorderColor(new Color(0, 125, 255)); // 边框颜色
			table.setPadding(12);// 衬距，看效果就知道什么意思了
			table.setSpacing(0);// 即单元格之间的间距
			table.setBorder(5);// 边框
			/** 第一行（标题） */
			String titleString = doctitle;// "记表"
			Paragraph title = new Paragraph(titleString);
			// 设置标题格式对其方式
			title.setAlignment(Element.ALIGN_CENTER);
			title.setFont(titleFont);
			doc.add(title);
			/** 第二行（正文） */
			String contextString = "时间：" + new Date().toLocaleString();
			Paragraph context = new Paragraph(contextString);
			// 正文格式对齐方式
			context.setAlignment(Element.ALIGN_RIGHT);
			context.setFont(contextFont);
			// 与上一段落（标题）的行距
			context.setSpacingBefore(10);
			// 设置第一行空的列数（缩进）
			// context.setFirstLineIndent(20);
			doc.add(context);

			Cell cell = null;
			
			//用户头像
			
			
			Image image = Image.getInstance(realPath+"../../../"+user.getPhotoid());
			
			
			// image.setRotation(-20);//旋转 弧度
			// image.setRotationDegrees(45);// 旋转 角度
			image.scaleAbsolute(100, 100);// 自定义大小
			// image.scalePercent(20);//依照比例缩放
			cell = new Cell(image);
			cell.setRowspan(2);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			//用户名
			cell = new Cell(user.getName());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//cell.setBackgroundColor(new Color(204, 223, 241));
			table.addCell(cell);
			
			//用户昵称
			cell = new Cell(user.getUsername());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new Cell("密码：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new Cell(user.getPassword());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			cell = new Cell("性别：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new Cell(user.getSex());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			cell = new Cell("出生日期：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new Cell(user.getBorntime());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);

			cell = new Cell("手机号：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new Cell(user.getPhone());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new Cell("QQ号：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new Cell(user.getQq());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new Cell("微信号：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new Cell(user.getWeixin());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new Cell("微博：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new Cell(user.getWeibo());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new Cell("用户状态：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new Cell(user.getState());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new Cell("账号申请时间：");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new Cell(user.getCreatetime());
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			doc.add(table);

			/*
			Paragraph c = new Paragraph("哈哈哈哈哈哈哈");
			// 正文格式对齐方式
			c.setAlignment(Element.ALIGN_RIGHT);
			RtfFont contextFont2 = new RtfFont("仿宋_GB2312", 9, Font.NORMAL,
					Color.red);
			c.setFont(contextFont2);
			
			
			// 与上一段落（标题）的行距
			// c.setSpacingBefore(100);
			// 设置第一行空的列数（缩进）
			// context.setFirstLineIndent(20);
			doc.add(c);
			*/
			
			doc.close();

			doc.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
