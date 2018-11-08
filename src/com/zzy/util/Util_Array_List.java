package com.zzy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**list和 array相互转换*/
public class Util_Array_List {

    /**
     * 数组转换成list
     * @param array
     * @return
     */
    public static List changeArrayToList(Object array[]){
        List list = Arrays.asList(array);
        return list;
    }

    /**
     * 数组转换成list
     * @param list
     * @return
     */
    public static Object [] changeListToArray(List list){
        ///String[] accountArray = (String[])accountList.toArray(new String[accountList.size()]);
        Object[] obj = new Object[list.size()];
        list.toArray(obj);
        return obj;
    }


    public static void main(String args[]){
        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        Object[] obj = changeListToArray(list);
        System.out.println(list);
        for(int i=0;i<obj.length;i++){
            System.out.println(obj[i]);
        }
    }

}
