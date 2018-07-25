package com.zzy.word;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

/**
 * 该方法 可以用
 * 
 * @author zzy 只能保证 有一个旧的word
 * 
 *         然后把文档替换
 * 
 *         或者生成 一个新的word
 */
public class wordForWordtemp {
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
				bodyRange.replaceText("${" + entry.getKey() + "}", entry
						.getValue());
			}
			return document;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String arg[]) {
		Map<String, String> contentMap = new HashMap<String, String>();
		contentMap.put("name", "飞翔家族");
		contentMap.put("year", "2015");
		contentMap.put("month", "13");

		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();

		// 末班
		HWPFDocument document = replaceDoc(desktopPath + "\\template.doc",
				contentMap);

		if (document != null) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			try {
				document.write(byteArrayOutputStream);
				// 末班
				OutputStream outputStream = new FileOutputStream(desktopPath
						+ "\\templates.doc");
				outputStream.write(byteArrayOutputStream.toByteArray());
				outputStream.close();
			} catch (IOException e) {
			}
		}
	}
}
