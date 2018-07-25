package com.zzy.util.paixu;
/**
 * 直接插入排序（DirectInsertSort）:
 * 直接插入排序的思路很简单，
 * 就是依次将带排序的数据元素按其关键字排序的大小插入前面的有序序列。
 * @author 011
 *
 */
public class DirectInsertSort {
	  public static void directInsertSort(int[] data){  
	        for(int i=1; i<data.length; i++){  
	            for(int j=0; j<i; j++){  
	                if(data[i] < data[j]){  
	                    //保存插入元素  
	                    int temp = 0;  
	                    temp = data[i];  
	                    //将要插入元素位置后的元素依次往后移  
	                    for(int m=i; m>j; m--){  
	                        data[m] = data[m-1];  
	                    }  
	                    //插入元素  
	                    data[j] = temp;  
	                }  
	            }             
	        }  
	    }  
	      
	    public static void main(String[] args){  
	        int data[] = {22,34,12,32,50,67,43,32};  
	        System.out.print("排序前：");  
	        for(int i=0; i<data.length; i++){  
	            System.out.print(data[i]+" ");  
	        }  
	        System.out.println();  
	        directInsertSort(data);  
	        System.out.print("排序后：");  
	        for(int i=0; i<data.length; i++){  
	            System.out.print(data[i]+" ");  
	        }  
	    }  
	  
}
