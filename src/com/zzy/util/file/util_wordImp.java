package com.zzy.util.file;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

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
/**
 * itext
 * 导出word
 * @author zzy
 *
 */
public class util_wordImp {
	
	/**
	 * rows 多少行
	 * rownum 每行接个表格
	 * title 标题
	 * 
	 * @param rows
	 * @param rownum
	 */
  public static void impword(int rows,int rownum,String doctitle,String docname) {
	  try {
		/** 创建Document对象（word文档） author:yyli Sep 15, 2010 */
		  Rectangle rectPageSize = new Rectangle(PageSize.A4);
		  rectPageSize = rectPageSize.rotate();
		  // 创建word文档,并设置纸张的大小
		  Document doc = new Document(PageSize.A4);
		  
		  
		  
		  /*桌面路径*/
		  File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		  String desktopPath = desktopDir.getAbsolutePath();
		  System.out.println(desktopPath);
		  
		 // String  fileName=desktopPath+"/测试导出word_"+System.currentTimeMillis()+".doc";
		  
		  //desktopPath+"/"
		  
		  //
		  SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMddhhmm");
 		  
			 String ss = sdf2.format(new Date());//当前年份
		  
		  String  fileName=desktopPath+"/"+"aaa"+ss+".doc";//
		  
		  /** 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中 author:yyli Sep 15, 2010 */
		  RtfWriter2.getInstance(doc, new FileOutputStream(fileName));
		  doc.open();
		  /** 标题字体 author:yyli Sep 15, 2010 */
		  RtfFont titleFont = new RtfFont("仿宋_GB2312", 15, Font.BOLD,
		          Color.BLACK);
		  /** 正文字体 author:yyli Sep 15, 2010 */
		  RtfFont contextFont = new RtfFont("仿宋_GB2312", 9, Font.NORMAL,
		          Color.BLACK);
		  /** 表格设置 author:yyli Sep 15, 2010 */
		  Table table = new Table(rows, rownum);
		  int[] withs = { 15, 35, 15, 35};
		  /** 设置每列所占比例 author:yyli Sep 15, 2010 */
		  table.setWidths(withs);
		  /** 表格所占页面宽度 author:yyli Sep 15, 2010 */
		  table.setWidth(100);
		  /** 居中显示 author:yyli Sep 15, 2010 */
		  table.setAlignment(Element.ALIGN_CENTER);
		  /** 自动填满 author:yyli Sep 15, 2010 */
		  table.setAutoFillEmptyCells(true);
		  table.setBorderWidth(5); // 边框宽度  
		  table.setBorderColor(new Color(0, 125, 255)); // 边框颜色  
		  table.setPadding(12);// 衬距，看效果就知道什么意思了  
		  table.setSpacing(0);// 即单元格之间的间距  
		  table.setBorder(5);// 边框  
		  /** 第一行（标题） author:yyli Sep 15, 2010 */
		  String titleString = doctitle;//"记表"
		  Paragraph title = new Paragraph(titleString);
		  // 设置标题格式对其方式
		  title.setAlignment(Element.ALIGN_CENTER);
		  title.setFont(titleFont);
		  doc.add(title);
		  /** 第二行（正文） author:yyli Sep 15, 2010 */
		  @SuppressWarnings("deprecation")
		  String contextString = "登记人："+"管理员    "+"登记时间："+new Date().toLocaleString();
		  Paragraph context = new Paragraph(contextString);
		  // 正文格式对齐方式
		  context.setAlignment(Element.ALIGN_RIGHT);
		  context.setFont(contextFont);
		  // 与上一段落（标题）的行距
		  context.setSpacingBefore(10);
		  // 设置第一行空的列数（缩进）
		  // context.setFirstLineIndent(20);
		  doc.add(context);
		  
		  
		  
		  /* 
		   插入图片
		   Image png = Image.getInstance("F:/qtww.png"); 
		  doc.add(png);
		  
		  */
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  Cell cell=null;
		  cell=new Cell("企业名称：");
		  cell.setHeader(true);
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		  cell.setBackgroundColor(new Color(204, 223, 241));
		  cell.setMaxLines(10000);
		  table.addCell(cell);
		  
		  
		  cell=new Cell("超神学院");
		  cell.setColspan(3);
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  cell.setBackgroundColor(new Color(204, 223, 241));
		  table.addCell(cell);
		  
		  
		  
		  
		  
		  
		  cell=new Cell("企业地址：");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		  table.addCell(cell);
		   
		  cell=new Cell("阿拉德大陆");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  table.addCell(cell);
		   
		  cell=new Cell("企业代码：");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		  table.addCell(cell);
		   
		  cell=new Cell("123456789");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  table.addCell(cell);
		  
		  
		  cell=new Cell("ce1：");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		  table.addCell(cell);
		   
		  cell=new Cell("11");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  table.addCell(cell);
		   
		  cell=new Cell("ce2");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		  table.addCell(cell);
		   
		  cell=new Cell("222");
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  table.addCell(cell);
		  
		  
		  
		  
		  
		  cell=new Cell("图片");
		  cell.setHeader(true);
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		  table.addCell(cell);
		  
		  Image image = Image.getInstance("F:/qtww.png");
		  
		// image.setRotation(-20);//旋转 弧度
			//image.setRotationDegrees(45);// 旋转 角度
			image.scaleAbsolute(100,100);//自定义大小
			// image.scalePercent(20);//依照比例缩放

		  
		  cell=new Cell(image);
		  //cell=new Cell("放个图片");
		  cell.setColspan(3);
		  
		  cell.setVerticalAlignment(Element.ALIGN_CENTER);
		  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		  table.addCell(cell);
		  doc.add(table);
		  
		  
		  Paragraph c = new Paragraph("哈哈哈哈哈哈哈");
		  // 正文格式对齐方式
		  c.setAlignment(Element.ALIGN_RIGHT);
		  RtfFont contextFont2 = new RtfFont("仿宋_GB2312", 9, Font.NORMAL, Color.red);
		  c.setFont(contextFont2);
		  // 与上一段落（标题）的行距
		 // c.setSpacingBefore(100);
		  // 设置第一行空的列数（缩进）
		  // context.setFirstLineIndent(20);
		  doc.add(c);
		  
		  
		  doc.close();
		  
		  doc.open();
	}  catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  public static void main(String arg[]){
	  
	  
	  
	  Properties p=new Properties();  
	//p需要InputStream对象进行读取文件，而获取InputStream有多种方法：  
	//1、通过绝对路径:  InputStream is=new FileInputStream("");
	//2、通过Class.getResourceAsStream(path);  
	//3、通过ClassLoader.getResourceAsStream(path);
	
	  //直接是不能获取的需要 通过action跳转获取的
	  String srcpath ="";
	 //srcpath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();

	InputStream is;
	try {
		
		is = new FileInputStream(srcpath+"dddd.properties");
		p.load(is);  
		is.close(); 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	 
	String s =  p.getProperty("a");
	  System.out.println(s);
	  
	
	  
	  //src下的路径
	  //Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	  
	  
	  
	  
	 impword( 4,4,"德玛西亚与世长辞。","ceshi");
	  
	 /* Runtime run = Runtime.getRuntime(); 
      arg = new String[3]; 
      arg[0] = "cmd"; 
      arg[1] = "/c"; 
      File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
	  String desktopPath = desktopDir.getAbsolutePath();
	  
      arg[2] = "Explorer.exe /n , /select,"+desktopPath+"\\cs.txt"; 
      try {
		run.exec(arg);
	} catch (IOException e) {
		e.printStackTrace();
	}*/
	  
	  
  }

}