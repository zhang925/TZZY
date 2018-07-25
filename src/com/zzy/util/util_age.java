package com.zzy.util;

import java.util.Date;
/**
 * 计算年龄
 * @author zzy
 *
 */
public class util_age {
	/**
	 * 根据输入的阳历计算年龄
	 * @param datestr(yyyy-MM-dd)
	 * 例如：(1992-01-01)
	 * @return String age
	 */
	public static String getage(String datestr){

		String age="";
		int nian1 = Integer.valueOf(datestr.substring(0,4));
		int yue1 = Integer.valueOf(datestr.substring(5, 7)) ;
		int ri1 = Integer.valueOf(datestr.substring(8, 10));
		String nowdate = util_Date.dateToStr1(new Date(), "yyyy-MM-dd");
		int nian2 = Integer.valueOf(nowdate.substring(0,4)) ;
		int yue2 = Integer.valueOf(nowdate.substring(5, 7)) ;
		int ri2 = Integer.valueOf(nowdate.substring(8, 10)) ;
		if(nian2<nian1){//输入的异常(出生日期大于当前时间)年龄为0
			age=""+0;
		}else{//正常输入
			if(yue2<yue1){//生日的月份没到
				age=""+(nian2-nian1-1);
			}else if(yue2==yue1){//生日的月份已经到了
				if(ri2<ri1){//生日 日期没有到
					age=""+(nian2-nian1-1);
				}else{//已经过了生日
					age=""+(nian2-nian1);
				}
			}else if(yue2>yue1){//生日月份到了
				age=""+(nian2-nian1);
			}
		}
		
		return age;
	}
	
	/**
	 * 根据输入的阴历 计算年龄
	 * (只要输入XXX年X月X日的格式即可XXX-X-X)
	 * @param datestr(一九九一年腊月廿二 || 一九九一年十二月廿二 || 一九九一年十二月二十二 || 1991年12月22)
	 * @return String age
	 */
	public static String getageByLunar(String str){
		/****	 	以下是把输入的阴历 取出 年月日	***/
		str = str.replaceAll("廿", "2");
		str = str.replaceAll("卅", "3");
		str = str.replaceAll("初", "");
		str = str.replaceAll("腊", "12");
		str = str.replaceAll("正", "1");
		str = util_changeyito1.hanyuXXtoalabo(str);//得到类似1991年12月22(1991-12-22)
		int nian1 = 0;
		int yue1 = 0;
		int ri1 = 0;
		String s[] = str.split("-");
		if(s.length>1){//1991-12-22
			nian1 = Integer.valueOf(s[0]);
			yue1 = Integer.valueOf(s[1]);
			ri1 = Integer.valueOf(s[2]);
		}
		String s1[] = str.split("年");
		
		if(s1.length>0){//1991年12月22
			nian1 = Integer.valueOf(s1[0]);//年份s1[1]-->12月22
			String yr[] = s1[1].split("月"); 
			yue1 = Integer.valueOf(yr[0]);
			//防止廿十、卅十(转换后是2十,3十,2十2)二十二  
			//2十2//三十  3十//廿二  22//三十  3十
			if(yr[1].length()==3){
				yr[1] = yr[1].replaceAll("十", "");//转换后是2十2-->22
			}else{
				yr[1] = yr[1].replaceAll("十", "0");//转换后是2十-->20
			}
			ri1 = Integer.valueOf(yr[1]);
		}
		/****		以下是   获取系统的年月日		***/
		
		int nian2 = 0 ;
		int yue2 = 0 ;
		int ri2 = 0 ;
		
		//获取当天的农历
		String nowdatenl = util_lunarDate.getLunar();//考虑 ： 三月廿十  、 十二月卅十
		nowdatenl = nowdatenl.replaceAll("廿", "2");
		nowdatenl = nowdatenl.replaceAll("卅", "3");
		nowdatenl = nowdatenl.replaceAll("初", "");
		nowdatenl = util_changeyito1.hanyuXXtoalabo(nowdatenl);
		String s2[] = nowdatenl.split("年");
		if(s2.length>0){//1991年12月22
			nian2 = Integer.valueOf(s2[0]);//年份s1[1]-->12月22
			String yr[] = s2[1].split("月"); 
			yue2 = Integer.valueOf(yr[0]);
			//防止廿十、卅十(转换后是2十,3十)
			if(yr[1].length()==3){
				yr[1] = yr[1].replaceAll("十", "");//转换后是2十2-->22
			}else{
				yr[1] = yr[1].replaceAll("十", "0");//转换后是2十-->20
			}
			ri2 = Integer.valueOf(yr[1]);
		}
		/******		计算年龄	*******/
		String age="";
		if(nian2<nian1){//输入的异常(出生日期大于当前时间)年龄为0
			age=""+0;
		}else{//正常输入
			if(yue2<yue1){//生日的月份没到
				age=""+(nian2-nian1-1);
			}else if(yue2==yue1){//生日的月份已经到了
				if(ri2<ri1){//生日 日期没有到
					age=""+(nian2-nian1-1);
				}else{//已经过了生日
					age=""+(nian2-nian1);
				}
			}else if(yue2>yue1){//生日月份到了
				age=""+(nian2-nian1);
			}
		}
		
		return age;
	}
}
