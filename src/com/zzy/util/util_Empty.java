package com.zzy.util;

import java.util.List;

/**
 * 非空判断
 * @author zzy
 *
 */
public class util_Empty {
	/**
	 * 判断字符串是否为空
	 * 空 返回 false 
	 * @param String
	 * @return boolean
	 */
	public static boolean strEmpty(String str){
		if(null!=str && !"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断List是否为空
	 * 空  返回 false
	 * @param object
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public static boolean listEmpty(List list){
		if(null!=list){
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
