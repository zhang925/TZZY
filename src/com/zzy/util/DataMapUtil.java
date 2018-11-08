package com.zzy.util;

import java.lang.reflect.Method;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zzy.model.tempModel;
public class DataMapUtil {
    
    private static Map<String, Object> dataMap = new HashMap<String, Object>();
       
    /**
     * 将实体 对象转换成Map
     * @param obj 对象类
     * @return
     */
    public static Map<String,Object> setObjToMap(Object obj){
        Class c;
        try {
            c = Class.forName(obj.getClass().getName());
            Method[] methods = c.getMethods();
            for(int i=0,l=methods.length;i<l;i++){
                String method = methods[i].getName();
                System.out.println("The method is:" + method);
                if(method.startsWith("get")){
                    Object value = methods[i].invoke(obj);
                    if(value != null){
                        if(value.getClass().getClassLoader() != null){  //处理自定义的对象类型
                            setObjToMap(value);
                        }
                        String key = method.substring(3);
                        key = key.substring(0, 1).toLowerCase() + key.substring(1);
                        if("java.util.Date".equals(value.getClass().getName())){
                        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    		dataMap.put(key, dataMap.put(key, sdf.format((Date)value)));
                        }else if("java.sql.Clob".equals(value.getClass().getName())){
                        	dataMap.put(key, Util_StrToClob.clobToString((Clob)value));
                        }else{
                        	 dataMap.put(key, value);
                        }//其他数据类型再说[clob]
                       
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }
    
    public static void main(String args[]){
    	tempModel t = new tempModel("名字2",25,12.00,new Date());
		tempModel t2 = new tempModel("名字2",25,12.00);
    	Map<String, Object> dataMap = setObjToMap(t);
    	System.out.println(dataMap);
    }
}