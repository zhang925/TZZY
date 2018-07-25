package com.zzy.model;

import java.util.Date;
import java.util.Map;


public class test  {
	
	public static void main(String[] args) {
		tempModel t = new tempModel("名字2",25,12.00,new Date());
		tempModel t2 = new tempModel("名字2",25,12.00);
		try {
			Map map = UtilModel.getClassAllVal(t2,"s");
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
	}
	
}
