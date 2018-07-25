package com.zzy.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseDomain implements Serializable {
	 private static final long serialVersionUID = 4861363281663993175L;  
	    /** 
	     * toString  
	     * 打印示例：synchronizedtest.Account@768965fb[name=张三, ,amount=500.0, ] 
	     */  
	    @Override  
	    public String toString() {  
	        ToStringBuilder builder = new ToStringBuilder(this);  
	        Field[] fields = this.getClass().getDeclaredFields();  
	        try {  
	            for (Field f : fields) {  
	                f.setAccessible(true);  
	                builder.append(f.getName(), f.get(this)).append("\n");  
	            }  
	        } catch (Exception e) { // Suppress  
	            builder.append("toString builder encounter an error");  
	        }  
	        return builder.toString();  
	    }  
	
	   /* 
	    @Override  
	    public String toString() {  
	        return ToStringBuilder.reflectionToString(this,  
	                ToStringStyle.MULTI_LINE_STYLE);  
	    } */
}
