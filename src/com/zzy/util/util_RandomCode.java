package com.zzy.util;

import java.math.BigDecimal;


public class util_RandomCode {
	/**大写字母和数字(0~9 A~Z)长度36*/
	public static String[] codesStrAndNum1=new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	/**大写字母和数字(1~9 A~Z)长度35*/
	public static String[] codesStrAndNum1_1=new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	/**大写字母(A~Z)长度26*/
	public static String[] codesStr1=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	/**小写字母和数字(0~9 a~z)长度36*/
	public static String[] codesStrAndNum2=new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	/**小写字母和数字(1~9 a~z)长度35*/
	public static String[] codesStrAndNum2_1=new String[]{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	/**小写字母长度26*/
	public static String[] codesStr2=new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	/**大(A~Z)小(a~z)写字母加数字(0~9) 长度62*/
	public static String[] codesStrAndNum3=new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	/**大(A~Z)小(a~z)写字母加数字(1~9) 长度61*/
	public static String[] codesStrAndNum3_1=new String[]{"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	
	/**0~9的数字 长度10*/
	public static int[] codesNum1 = new int[]{0,1,2,3,4,5,6,7,8,9};
	/**1~9的数字 长度9*/
	public static int[] codesNum1_1 = new int[]{1,2,3,4,5,6,7,8,9};
	
	/**
	 * 生成XXX位随机字符串
	 * (数字加大写字母)
	 * @param int 位数 
	 * @return String
	 */
	public static String strAndNumRandom(int length){
		String rdcode="";
		for (int i = 0; i <length; i++) {
		  int num=(int)(Math.random()*36);
		  rdcode+=codesStrAndNum1[num];
		}
		return rdcode;
	}
	/**
	 * 生成 1~9 位随机 数字(最高9位数字(9亿...))
	 * @param int length (位数)
	 * @return int
	 */
	public static int numRandom(int length){
		String code="";
		int n = 0;
		if(length>=9){
			length=9;
		}
		for(int i = 0;i < length; i++){
			if(i==0){//第一个数不能为0
				int num=(int)(Math.random()*9);
				code=codesNum1_1[num]+"";
			}else{//不是第一个数
				int num=(int)(Math.random()*10);
				code=code + codesNum1[num];
			}
			
		}
		if(code!=null && !"".equals(code)){//防止异常
			n = Integer.valueOf(code);
		}
		return n;
	}
	
	/**
	 * 生成XXX位的数字(返回String)
	 * @param length
	 * @return String
	 */
	public static String bigNum(int length){
		String code="";
		for(int i = 0;i < length; i++){
			if(i==0){//第一个数不能为0
				int num=(int)(Math.random()*9);
				code=codesNum1_1[num]+"";
			}else{//不是第一个数
				int num=(int)(Math.random()*10);
				code=code + codesNum1[num];
			}
			
		}
		return code;
	}
	
	
	public void testBigNum(){
		int a = 999999999;//9亿
		double b = 999999999;//9亿
		float c = 999999999;//9亿
		BigDecimal k0 = new BigDecimal(a);
		BigDecimal k1 = new BigDecimal(b);
		BigDecimal k2 = new BigDecimal(c);
		BigDecimal k3 = k0.multiply(k1).add(k2);
		System.out.println(k3);
		System.out.println(numRandom(9));
	}
	/**
	 * 把 int 0 ~ 127 转成  char
	 * ( 一共 128 个 char)
	 * @return char
	 */
	public static char intToChar(int num){
		if(num<0){
			num = 0;
		}
		if(num > 127){
			num = 127;
		}
		return (char) num;
	}
	/**
	 * 把 char 转成 int
	 * @param ch
	 * @return int
	 */
	public static int charToInt(char ch){
		return ch;
	}
	
	public static String strRadom(){
		
		return "";
	}
	//次方的算法
	//int size = (int) Math.pow(10,length) * 9  - (int) Math.pow(10,length) ;//表示10后面多少个0
	
	
	public static void main(String args[]){
		String size = "8";
		for(int i=1;i<5;i++){
			size=size+"9";
		}
		System.out.println(size);
	}
}
