package com.zzy.model;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UtilModel {
	public  void getObjectValuesss(Object object) throws Exception {
	  //我们项目的所有实体类都继承BaseDomain （所有实体基类：该类只是串行化一下）
	  //不需要的自己去掉即可
		//class java.lang.String
		/*class java.util.Date
		int
		class java.lang.Integer
		double
		class java.lang.Double
		interface java.sql.Clob
		float
		class java.lang.Float
		class java.lang.Boolean
		class java.lang.Boolean*/
	  if (object != null) {//if (object!=null )  ----begin
	   // 拿到该类
	   Class<?> clz = object.getClass();
	   // 获取实体类的所有属性，返回Field数组
	   Field[] fields = clz.getDeclaredFields();
	   for (Field field : fields) {// --for() begin
	    System.out.println(field.getGenericType());//打印该类的所有属性类型
	    // 如果类型是String
	    if (field.getGenericType().toString().equals(
	      "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
	     // 拿到该属性的gettet方法
	     Method m = (Method) object.getClass().getMethod(
	       "get" + getMethodName(field.getName()));
	     String val = (String) m.invoke(object);// 调用getter方法获取属性值
	     if (val != null) {
	      System.out.println("String type:" + val);
	     }
	    }
	    // 如果类型是Integer
	    if (field.getGenericType().toString().equals(
	      "class java.lang.Integer")) {
	     Method m = (Method) object.getClass().getMethod(
	       "get" + getMethodName(field.getName()));
	     Integer val = (Integer) m.invoke(object);
	     if (val != null) {
	      System.out.println("Integer type:" + val);
	     }
	    }
	    // 如果类型是Double
	    if (field.getGenericType().toString().equals(
	      "class java.lang.Double")) {
	     Method m = (Method) object.getClass().getMethod(
	       "get" + getMethodName(field.getName()));
	     Double val = (Double) m.invoke(object);
	     if (val != null) {
	      System.out.println("Double type:" + val);
	     }
	    }
	    // 如果类型是Boolean 是封装类
	    if (field.getGenericType().toString().equals(
	      "class java.lang.Boolean")) {
	     Method m = (Method) object.getClass().getMethod(
	       field.getName());
	     Boolean val = (Boolean) m.invoke(object);
	     if (val != null) {
	      System.out.println("Boolean type:" + val);
	     }
	    }
	    // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
	    // 反射找不到getter的具体名
	    if (field.getGenericType().toString().equals("boolean")) {
	     Method m = (Method) object.getClass().getMethod(
	       field.getName());
	     Boolean val = (Boolean) m.invoke(object);
	     if (val != null) {
	      System.out.println("boolean type:" + val);
	     }
	    }
	    // 如果类型是Date
	    if (field.getGenericType().toString().equals(
	      "class java.util.Date")) {
	     Method m = (Method) object.getClass().getMethod(
	       "get" + getMethodName(field.getName()));
	     Date val = (Date) m.invoke(object);
	     if (val != null) {
	      System.out.println("Date type:" + val);
	     }
	    }
	    // 如果类型是Short
	    if (field.getGenericType().toString().equals(
	      "class java.lang.Short")) {
	     Method m = (Method) object.getClass().getMethod(
	       "get" + getMethodName(field.getName()));
	     Short val = (Short) m.invoke(object);
	     if (val != null) {
	      System.out.println("Short type:" + val);
	     }
	    }
	    // 如果还需要其他的类型请自己做扩展
	   }//for() --end
	  }//if (object!=null )  ----end
	 }
	 // 把一个字符串的第一个字母大写、效率是最高的、
	 private static String getMethodName(String fildeName) throws Exception{
		  byte[] items = fildeName.getBytes();
		  items[0] = (byte) ((char) items[0] - 'a' + 'A');
		  return new String(items);
	 }
	 
	 public void getmodelSx(){
		try {
		   Class clazz = Class.forName("com.zzy.model.test");//根据类名获得其对应的Class对象 写上你想要的类名就是了 注意是全名 如果有包的话要加上 比如java.Lang.String
		   Field[] fields = clazz.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
		   for(Field f : fields) {
			   System.out.println(
					   f.getName()+"："+f.getType().getName()
					   +"：");//打印每个属性的类型名字
		   }
		  } catch(Exception e) {
		   e.printStackTrace();
		  }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	  * 获取一个类的所有属性和值
	  * @param object[实体类]
	  * @throws Exception
	  */
	 public static void ObjectValue(Object object) throws Exception {
		  if (object != null) {
		   Class<?> clz = object.getClass();// 拿到该类
		   // 获取实体类的所有属性，返回Field数组
		   Field[] fields = clz.getDeclaredFields();
			   for (Field field : fields) {// --for() begin
				 //System.out.println(field.getGenericType());//打印该类的所有属性类型
			     Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
			     Object val =  m.invoke(object);// 调用getter方法获取属性值
			     if (val != null) {
			    	 System.out.println(field.getName()+"---" + val);
			     }
			   }
		  }
	}
	 
	 
	 /**
	  * 获取一个类的所有属性和值
	  * @param object[实体类]
	  * @param type 只要不是null或者空认为是查有值的字段，否则表示查所有的
	  */
	 public static Map getClassAllVal(Object object,String type){
		 Map map = new HashMap();
		  if (object != null) {
		   Class<?> clz = object.getClass();// 拿到该实体类
		   Field[] fields = clz.getDeclaredFields();
			   for (Field field : fields) {
					try {
						Method method = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
						Object val =  method.invoke(object);// 调用getter方法获取属性值
						System.out.println(field.getGenericType());
						if(type==null || "".equals(type)){//查所有的
							map.put(field.getName(), val);
						}else{
							if (val != null) {
								map.put(field.getName(), val);
						    }
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			   }
		  }
		  return map;
	}
}
