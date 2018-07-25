package com.zzy.util.paixu;


/**
 * 直接排序法
 *  1.直接选择排序（DirectSelectSort）：
 *  其关键就是对n个数据要进行n-1趟比较，
 *  每趟比较的目的就是选择出本趟比较中最小的数据，
 *  并将选择出的数据放在本趟中的第一位。
 * @author 011
 *
 */
public class DirectSelectSort {
	public static void main(String[] args) {
		int data[] = {4,1,3,2,6,5};
			int minData = 0;  
	        int index = 0;  
	        //进行n-1趟比较  
	        for(int i=0; i<data.length-1; i++){  
	            minData = data[i];  
	            index = i;  
	            for(int j=i+1; j<data.length; j++){  
	                if(minData > data[j]){  
	                    minData = data[j];  
	                    index = j;  
	                }  
	            }  
	            //一趟比较完后，交换一次  
	            data[index] = data[i];  
	            data[i] = minData;  
	        }  
	        
	        System.out.println("排序后:");
			for(int i=0;i<data.length;i++){
				System.out.print(data[i]+" ");
			}
			
	}        
}
