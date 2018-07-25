
package com.zzy.util.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class util_xmlwrite {
	
	
	
	/**
	 * 字符串形式 	向XML文档中写入数据 
	 * @param filesrc 要写入的XML的地址包括文件名字
	 * D:\MyEclipse\workspace\TZZY\WebRoot\resources\xml\writeTemp.xml
	 * 
	 * @param documentstr 要写入的内容<root>I believe !</root> 
	 */
	public static void writeXml(String documentstr,String filesrc) {
		Document documentStr = null;
		try {
			documentStr = DocumentHelper.parseText(documentstr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		XMLWriter writer = null;
		try {
			writer = new XMLWriter( new FileWriter(filesrc));
			writer.write(documentStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 非字符串 的 写入 XML 需要  组合 Document 对象
	 * 
	 * @param docsrc //要写入的地址包括文件名字
	 * D:\\MyEclipse\\workspace\\TZZY\\WebRoot\\resources\\xml\\writeTemp01.xml
	 * 
	 * @param documents 所要组合的 document 对象 在  getDocument() 有示例
	 */
	public static void weritXML01(String docsrc,Document doc){
		FileWriter out = null;
		try {
			out = new FileWriter(docsrc);
			doc.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * 非字符串 的 写入 XML 组合 Document 对象
	 * @return Document
	 */
	public static  Document getDocument(){
		Document documents = DocumentHelper.createDocument();
		//一级目录
		Element root = documents.addElement("root");
		
		//在以及目录下添加二级目录
		Element author1 = root.addElement("Lynch");
		//为目录添加属性
		author1.addAttribute("Age", "25");
		author1.addAttribute("Country", "China");
		//为目录添加内容
		author1.addText("I am great!");
		
		//添加为二级目录添加次级目录
		Element author01 = author1.addElement("aaaa");
		author01.addText("I believe what belonging will come !");
		
		Element author2 = root.addElement("Legend");
		author2.addAttribute("Age", "25");
		author2.addAttribute("Country", "China");
		author2.addText("I am great!too!");
		return documents;
	}
	
	
}
