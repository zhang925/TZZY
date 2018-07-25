package com.zzy.util;
/**
 * 数字转换
 * @author zzy
 *
 */
public class util_changeyito1 {
	/**
	 * 把   小写汉语数字   转换成    阿拉伯数字
	 * @param String str
	 * @return String
	 */
	public static String hanyuXXtoalabo(String str){
		str = str.replaceAll("零", "0");
		str = str.replaceAll("一", "1");
		str = str.replaceAll("二", "2");
		str = str.replaceAll("三", "3");
		str = str.replaceAll("四", "4");
		str = str.replaceAll("五", "5");
		str = str.replaceAll("六", "6");
		str = str.replaceAll("七", "7");
		str = str.replaceAll("八", "8");
		str = str.replaceAll("九", "9");
		return str;
	}
	/**
	 * 把  大写汉语数字   转换成   阿拉伯数字
	 * @param String str
	 * @return String
	 */
	public static String hanyuDXtoalabo(String str){
		str = str.replaceAll("零", "0");
		str = str.replaceAll("壹", "1");
		str = str.replaceAll("贰", "2");
		str = str.replaceAll("叁", "3");
		str = str.replaceAll("肆", "4");
		str = str.replaceAll("伍", "5");
		str = str.replaceAll("陆", "6");
		str = str.replaceAll("柒", "7");
		str = str.replaceAll("捌", "8");
		str = str.replaceAll("玖", "9");
		//拾 佰 仟 万
		return str;
	}
	/**
	 * 把   阿拉伯数字  转换成  小写汉语数字
	 * @param String str
	 * @return String
	 */
	public static String alabotohanyuXX(String str){
		str = str.replaceAll("0", "零");
		str = str.replaceAll("1", "一");
		str = str.replaceAll("2", "二");
		str = str.replaceAll("3", "三");
		str = str.replaceAll("4", "四");
		str = str.replaceAll("5", "五");
		str = str.replaceAll("6", "六");
		str = str.replaceAll("7", "七");
		str = str.replaceAll("8", "八");
		str = str.replaceAll("9", "九");
		return str;
	}
	/**
	 * 把   阿拉伯数字  转换成  大写汉语数字
	 * @param String str
	 * @return String
	 */
	public static String alabotohanyuDX(String str){
		str = str.replaceAll("0", "零");
		str = str.replaceAll("1", "壹");
		str = str.replaceAll("2", "贰");
		str = str.replaceAll("3", "叁");
		str = str.replaceAll("4", "肆");
		str = str.replaceAll("5", "伍");
		str = str.replaceAll("6", "陆");
		str = str.replaceAll("7", "柒");
		str = str.replaceAll("8", "捌");
		str = str.replaceAll("9", "玖");
		//拾 佰 仟 万
		return str;
	}
}
