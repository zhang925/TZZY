package com.zzy.util.file;

import java.text.DecimalFormat;

/**
 * 文件大小转换
 * B KB MB GB TB
 * @author zzy
 *
 */
public class util_fileSize {
/*
中文单位		中文简称		英文单位		英文简称	进率（Byte=1）
位			比特			bit				b	0.125
字节			字节			Byte			B	1
千字节		千字节		Kilo Byte		KB	2^10
兆字节		 兆			Mega Byte		MB	2^20
吉字节		吉			Giga Byte   	GB	2^30
太字节		太 			Trillion Byte	TB	2^40
拍字节 		拍 			Peta Byte		PB  2^50
艾字节             	艾			Exa Byte		EB	2^60
泽它字节		泽			Zetta Byte		ZB	2^70
尧它字节		尧			Yotta Byte		YB	2^80
千亿亿亿字节	千亿亿亿字节	Bront Byte		BB	2^90
百万亿亿亿字节	百万亿亿亿字节	Nona Byte		NB	2^100
十亿亿亿亿字节	十亿亿亿亿字节	Dogga Byte		DB	2^110
万亿亿亿亿字节	万亿亿亿亿字节	Corydon Byte	CB	2^120
1KB=1024B，一个汉字 =2B，即1KB=512个汉字占用的空间。
1TB=1024GB 1GB=1024MB 1MB=1024KB 1KB=1024B 1B=8b.
*/
	
	
	/**
	 * 这里只把B转换成KB或者MB(xx.xx)
	 * (1024换算,计算文件大小一般用1024)
	 * @param (long)sizes
	 * @return String
	 */
	public static String bToOther1024(long sizes){
		DecimalFormat df = new DecimalFormat("#.00");
		String s = "";//要返回的文件大小
		float size = sizes;
		if(size>=1024 && (size/1024<=1024)){//转换成KB
			s = size/1024+"KB";
		}
		if(size/1024>1024){//转换成MB
			float ss= size/1024/1024;
			s = df.format(ss) +"MB";
			
		}
		return s;
	
	}
	/**
	 * 这里只把B转换成KB或者MB
	 * (1000换算)
	 * @param (long)sizes
	 * @return String
	 */
	public static String bToOther1000(long sizes){
		String s = "";
		if(sizes>=1000 && (sizes/1000<=1000)){//转换成KB
			s = sizes/1000+"KB";
		}
		if(sizes/1000>1000){//转换成MB
			s = sizes/1000 +"MB";
		}
		return s;
	
	}
}
