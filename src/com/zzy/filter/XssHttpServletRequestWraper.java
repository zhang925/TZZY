package com.zzy.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import javax.servlet.Filter;  
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class XssHttpServletRequestWraper extends HttpServletRequestWrapper implements Filter  {

	public XssHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		return clearXss(super.getParameter(name));
	}
	
	@Override
	public String getHeader(String name) {
		return clearXss(super.getHeader(name));
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		String[] newValues = new String[values.length];
		
		for(int i =0; i< values.length; i++){
			newValues[i] = clearXss(values[i]);
		}
		
		return newValues;
	}

	/**
	 * 处理字符转义
	 * @param value
	 * @return
	 */
	private  String clearXss(String value){
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
	//@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	

	//@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	//@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
