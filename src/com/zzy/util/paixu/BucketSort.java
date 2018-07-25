package com.zzy.util.paixu;
/**
 * 9.桶式排序（BucketSort）：
 * 桶式排序不是基于比较的排序方法，
 * 这种排序需要满足一下两个特征：
 * a.待排序列的所在值处于一个可枚举的范围之内；
 * b.待排序列所在的这个可枚举范围不应该太大，否则排序的开销太大。
 * @author 011
 *
 */
public class BucketSort {
	 public static void bucketSort(int[] data, int min, int max){  
	        //计数数组  
	        int temp[] = new int[max];  
	        for(int i=0; i<data.length; i++){  
	            temp[data[i]]++;  
	        }  
	        for(int j=0; j<temp.length; j++){  
	            if(temp[j]>0){  
	                for(int m=0; m<temp[j]; m++){  
	                    data[min] = j;  
	                    min++;  
	                }                 
	            }  
	        }         
	    } 
	public static void main(String[] args){  
	        int data[] = {3,5,2,6,4,7,3,9,1};  
	        System.out.print("排序前：");  
	        for(int i=0; i<data.length; i++){  
	            System.out.print(data[i]+" ");  
	        }  
	        System.out.println();  
	        bucketSort(data, 0, 10);  
	        System.out.print("排序后：");  
	        for(int i=0; i<data.length; i++){  
	            System.out.print(data[i]+" ");  
	        }  
	    }  
}
