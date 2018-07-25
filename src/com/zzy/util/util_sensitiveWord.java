package com.zzy.util;

public class util_sensitiveWord {
	/**
	 * 处理字符转义
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unused")
	private static  String getSensitiveCharacter(String value){
		if (value == null || "".equals(value)) {
			return value;
		}
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replace("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replace("script", "");
		return value;
	}
	
}
