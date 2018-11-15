package com.zzy.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class util_Json {

	/**json输出text字符串只能输出数字*/
	public static void jsonPrintln(String str,HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			System.out.println("获取输出流异常");
			e.printStackTrace();
		}
		out.println(str);
		out.flush();
		out.close();

	}


	/**json输出text字符串条件 true输出str1 false输出str2*/
	public static void jsonPrintln(boolean flag,String str1,String str2,HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			System.out.println("获取输出流异常");
			e.printStackTrace();
		}
		if(flag==true){
			out.println(str1);
		}else{
			out.println(str2);
		}
		out.flush();
		out.close();

	}



	/**SSH json输出Objecct 是 List*/
	@SuppressWarnings("unchecked")
	public static void jsonPrintList(List list,HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			System.out.println("获取输出流异常");
			e.printStackTrace();
		}
		JSONArray ja= JSONArray.fromObject(list);
		out.print(ja.toString());
		out.flush();
		out.close();

	}

	/** json输出Objecct 是 实体 或者是 键-->值*/
	public static void jsonPrintModel(Object object,HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			System.out.println("获取输出流异常");
			e.printStackTrace();
		}
		JSONObject j = JSONObject.fromObject(object);
		out.print(j.toString());
		//System.out.println(j.toString());
		out.flush();
		out.close();

	}



	/**
	 * easyUI转化为Json格式
	 * @param list(分页查询的数据集合)
	 * @param total(总记录数即总条数)
	 */
	public static void jsonForEasyUI(List list,int total,HttpServletResponse response){
		JSONObject jobj = new JSONObject();//new一个JSON
		jobj.accumulate("total",total );//total代表一共有多少数据
		jobj.accumulate("rows", list);//row是代表显示的页的数据
		writeJSON(jobj,response);
	}


	/**
	 * ext 列表 转化为Json格式
	 * @param list(分页查询的数据集合)
	 * @param total(总记录数即总条数)
	 */
	public static void jsonForExt(List list,int total,HttpServletResponse response){
		//page：当前页
		//start：起始行的行号
		//limit：每页数据行数，默认为25；这个参数值就是我们设置的pageSize

		JSONObject jobj = new JSONObject();//new一个JSON
		jobj.accumulate("total",total );//total代表一共有多少数据
		jobj.accumulate("rows", list);//row是代表显示的页的数据
		writeJSON(jobj,response);
	}

	/**
	 * jqgrid 列表数据
	 * @param list
	 * @param total
	 * @param response
	 */
	public static void jsonForJqGrid(List list,int total,int pageSize,HttpServletResponse response){
		JSONObject jobj = new JSONObject();
		int totalPage = (total % pageSize == 0 ) ? (total / pageSize) : ( (total / pageSize) + 1 ) ;
		jobj.accumulate("total",totalPage );//total代表一共有多少页
		jobj.accumulate("records",total );//一共多少条
		jobj.accumulate("rows", list);//row是代表显示的页的数据
		writeJSON(jobj,response);
	}

	public static void jsonForLayerUI(List list,int total,int pageSize,HttpServletResponse response){
		JSONObject jobj = new JSONObject();
		jobj.accumulate("count",total );//total代表一共有多少页
		jobj.accumulate("data", list);//row是代表显示的页的数据
		jobj.accumulate("code", 0);//
		jobj.accumulate("msg", "用户数据");//
		writeJSON(jobj,response);
	}
	/**
	 * 输出 json
	 * @param obj
	 * @param response
	 */
	public static void writeJSON(JSONObject obj,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");//指定为utf-8
		try {
			response.getWriter().write(obj.toString());//转化为JSOn格式
			response.getWriter().close();
		} catch (IOException e) {
			System.out.println("异常！");
			e.printStackTrace();
		}
	}

	public void jsonToString(){

	}

	public static void main(String args[]){

	}


}
