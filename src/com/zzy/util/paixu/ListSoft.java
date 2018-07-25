package com.zzy.util.paixu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zzy.model.User;

/**
 * List 排序
 * @author zzy
 *
 */
public class ListSoft {
	
	/**
	 * 给一个list 里面 有个实体 
	 * 根据实体里面的某个可比较大写的字段排序
	 * 这个字段必须是可比较大小
	 * 这里用User类的createtime做例子
	 * 因为字段和类固定不了所以只做示例
	 * @param list<User>
	 * @return list<User>
	 */
	public static  List<User> softListEntity(List<User> list){
		Collections.sort(list, new Comparator<User>() {  
            public int compare(User c1, User c2) { 
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//2016-06-30 12:45:05 小写的mm表示的是分钟  
        		java.util.Date date1 = null;
        		java.util.Date date2 = null;
        		try {
        			date1 = sdf.parse(c1.getCreatetime());
        			date2 = sdf.parse(c2.getCreatetime());
        		} catch (ParseException e) {
        			System.out.println("时间格式不正确！");
        			e.printStackTrace();
        		}
		        if (date2.after(date1)) {//降序
		            return 1;  
		        } else {  
		            return -1;  
		        }  
            }  
		});
		
		
		
		return list;
	}
	
	/**
	 * LinkedHashMap 先进先出有顺序
	 * HashMap 存放读取是随机的
	 * 给一个 map（HashMap）
	 * 根据指定 键 的顺序 排序
	 * 思想 是 把 无序的 HashMap 转成   LinkedHashMap
	 * @param List orderkeylist 排序的键值顺序
	 * @param Map 要排序的map
	 * @return Map
	 */
	public static  Map softListMap(List orderkeylist, Map map){
		Map m = new LinkedHashMap();
		
		// 1.  站位
		for(int i=0;i<orderkeylist.size();i++){
			m.put(orderkeylist.get(i),"");
		}
		//2  有数据的 key 写入值
		Iterator iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
			Map.Entry entry = (Map.Entry) iter.next(); 
			Object key = entry.getKey(); //传进来的Map的key的值
			String tempkey = "";
			if(key!=null){tempkey = key.toString();}
			Object tempval = entry.getValue();//传进来的Map的key对应的键值
			for(int i=0;i<orderkeylist.size();i++){
				if(tempkey.equals(orderkeylist.get(i))){
					m.put(tempkey,tempval);
				}
			}
		}
		//3 最后清除没有 值的 key
		for(int i=0;i<orderkeylist.size();i++){
			if(orderkeylist.get(i)==null ||"".equals(orderkeylist.get(i)) ){
				m.remove(orderkeylist.get(i));
			}
		}
		return m;
	}
}
