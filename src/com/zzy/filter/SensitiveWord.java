package com.zzy.filter;

import java.util.List;

/***
 * 敏感字 字符 转换过滤
 * @author zzy
 *
 */
public class SensitiveWord {
	
	/**
	 * 敏感字符在这里设置
	 * @return
	 */
	public static String[] getSensitiveCharacter(){
		//敏感字符
		String sensitiveCharacter[]={
			"<",">","'","\""	
		};
		return sensitiveCharacter;
	}
	
	/**
	 * 敏感字在这里设置
	 * @return
	 */
	public static String[] getSensitiveWord(){
		//敏感字符
		String sensitiveWord[]={
			"他妈的","傻逼","TMD"	
		};
		return sensitiveWord;
	}
}
