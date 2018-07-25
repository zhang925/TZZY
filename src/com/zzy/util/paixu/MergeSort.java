package com.zzy.util.paixu;
/**
 * 8.归并排序（MergeSort）:
 * 其基本思想是将两个或两个以上的有序序列的集合合并成一个新的有序序列。
 * 这次我主要学习的还是将两个有序的有序序列合并成一个信的序列。
 * 细化来说，归并排序现将长度为n的无序序列看成是n个长度为1的有序子序列，
 * 首先做两两合并，得到n/2个长度为2的有序子序列，
 * 再做两两合并...不断重复这个过程，最终可以得到一个长度为n的有序序列。
 * @author 011
 *
 */
public class MergeSort {
	 public static void mergeSort(int[] data){  
	        sort(data, 0, data.length - 1);   
	    }  
	      
	    public static void sort(int[] data, int left, int right){  
	        if (left < right){  
	            //找出中间索引  
	            int center = (left + right) / 2;  
	            //对左边数组进行递归  
	            sort(data, left, center);   
	            //对右边数组进行递归  
	            sort(data, center + 1, right);   
	            //合并  
	            merge(data, left, center, right);   
	        }   
	    }  
	      
	    private static void merge(int[] data  
	            , int left, int center, int right){  
	            //定义一个与待排序序列长度相同的临时数组   
	            int[] tmpArr = new int[data.length];  
	            int mid = center + 1;  
	            //third记录中间数组的索引  
	            int third = left;   
	            int tmp = left;   
	            while (left <= center && mid <= right){   
	                //从两个数组中取出小的放入中间数组   
	                if (data[left] <= data[mid]){   
	                    tmpArr[third++] = data[left++];   
	                }else{  
	                    tmpArr[third++] = data[mid++];   
	                }  
	            }   
	            //剩余部分依次放入中间数组   
	            while (mid <= right){   
	                tmpArr[third++] = data[mid++];   
	            }   
	            while (left <= center){   
	                tmpArr[third++] = data[left++];   
	            }   
	            //将中间数组中的内容复制拷回原数组  
	            //(原left～right范围的内容复制回原数组)   
	            while (tmp <= right){  
	                data[tmp] = tmpArr[tmp++];   
	            }  
	        }   
	      
	    public static void main(String[] args){  
	        int data[] = {22,34,12,32,50,67,43,32};  
	        System.out.print("排序前：");  
	        for(int i=0; i<data.length; i++){  
	            System.out.print(data[i]+" ");  
	        }  
	        System.out.println();  
	        mergeSort(data);  
	        System.out.print("排序后：");  
	        for(int i=0; i<data.length; i++){  
	            System.out.print(data[i]+" ");  
	        }  
	    }  
}
