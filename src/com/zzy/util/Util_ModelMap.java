package com.zzy.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util_ModelMap {
    /**
     * 把实体转成map形式
     * @param object 有值的实体
     * @return Map 最终的map
     */
    public static Map<String,Object> changeModelToMap(Object object){
        Map<String,Object> map = new HashMap<String,Object>();
        if(object==null){
            return map;
        }
        Field[] allFields = object.getClass().getDeclaredFields();
        for (int i=0;i<allFields.length;i++) {
            Field field = allFields[i];
            String fieldName = field.getName();
            String fieldVal = "";
            if(fieldName=="serialVersionUID"){//考虑序列化 serialVersionUID
                continue;
            }
            String type = field.getGenericType().toString();
            try{
                Method method = (Method)  object.getClass().getMethod(("get" + getMethodName(fieldName)));
                if(type.equals("class java.lang.String")){//只要String类型的
                    fieldVal = (String) method.invoke(object);// 调用getter方法获取属性值
                }else if(type.equals("class java.lang.Integer")){
                    // 类型不一一判断，建议全部使用String 类型
                }else if(type.equals("class java.util.Date")){
                    // 类型不一一判断，建议全部使用String 类型
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            map.put(fieldName,fieldVal);
        }
        return map;
    }

    /**
     * 把map的值，反向赋值到model中
     * @param object 接收 map 值的实体
     * @param map  map值
     * @return Object 最终返回的实体
     */
    public static Object changeMapToModel(Object object, Map<String,Object> map){
        if(object == null){
            return null;
        }
        Field[] allFields = object.getClass().getDeclaredFields();
        for (int i=0;i<allFields.length;i++) {
            Field field = allFields[i];
            String fieldName = field.getName();
            if(fieldName == "serialVersionUID"){//考虑序列化 serialVersionUID
                continue;
            }
            String type = field.getGenericType().toString();
            try{
                if(type.equals("class java.lang.String")){//只要String类型的
                    //循环 map
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if(key.equals(fieldName)){
                            String methodName = "set" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                            Method method =  object.getClass().getDeclaredMethod(methodName, java.lang.String.class);
                            if(value!=null){
                                if(type.equals("class java.lang.String")){//只要String类型的
                                    method.invoke(object, value.toString() );
                                }else if(type.equals("class java.lang.Integer")){
                                    method.invoke(object, Integer.valueOf(value.toString()) );
                                }else if(type.equals("class java.util.Date")){ // 类型不一一判断，建议全部使用String 类型

                                }
                            }
                        }
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return  object;
    }


    private static String getMethodName(String fieldName){
        byte[] items = null;
        try{
            items = fieldName.getBytes();
            items[0] = (byte) ((char) items[0] - 'a' + 'A');
        }catch (Exception e){
            e.printStackTrace();
        }
        return new String(items);
    }

}
