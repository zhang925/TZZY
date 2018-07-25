package com.zzy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;
/**
 * String类型和其他类型的转换
 * @author zzy
 *
 */
public class util_StrChange {
	/**
	 * String 类型转换成Clob
	 * @param str
	 * @return
	 */
	public static Clob strToClob(String str){
		Clob clob = null;
		if(str==null||"".equals(str)){
			return null;
		}else{
			try {
				clob = new javax.sql.rowset.serial.SerialClob(str.toCharArray());
			} catch (SerialException e) {
				System.out.println("String和Clob转换出现错误!");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("String和Clob转换出现错误!");
				e.printStackTrace();
			} 
			return clob;
		}
	} 
	/**
	 * Clob  类型转换成 String
	 * @param clob
	 * @return
	 * @throws Exception
	 */
	public static String ClobToString(Clob clob) { 
		String reString = ""; 
		if(clob!=null){
			Reader is = null;
			try {
				is = clob.getCharacterStream();
			} catch (SQLException e) {
				System.out.println("getCharacterStream异常！");
				e.printStackTrace();
			}// 得到流 
			BufferedReader br = new BufferedReader(is); 
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e1) {
				System.out.println("readLine()异常！");
				e1.printStackTrace();
			} 
			StringBuffer sb = new StringBuffer(); 
			while (s != null) {// 执行循环将字符串全部取出付值给 StringBuffer由StringBuffer转成STRING 
			sb.append(s); 
			try {
				s = br.readLine();
			} catch (IOException e) {
				System.out.println("readLine()异常！");
				e.printStackTrace();
			} 
			} 
			reString = sb.toString(); 
			 
		}
		return reString;
	} 
}
