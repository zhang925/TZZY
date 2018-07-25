package com.zzy.util.paixu;
/**
 * 快速排序（QuickSort）: 快速排序死对 冒泡排序的一种改进，
 * 基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @author 011
 *
 */
public class QuickSort {
	public static void quickSort(int[] data, int start, int end){  
		  
        if(start < end){  
            //以第一个元素为分界值  
            int middleNum = data[start];  
            int i = start;  
            int j = end + 1;  
            while(true){  
                //找到大于分界值的元素的索引或者已经到了end处  
                while(i<end && data[++i] <= middleNum);  
                //找到小于分界值的元素的索引或者已经到了start处  
                while(j>start && data[--j] >= middleNum);  
                if(i < j){  
                    //交换  
                    int temp = data[i];  
                    data[i] = data[j];  
                    data[j] = temp;  
                }else{  
                    break;  
                }  
            }  
            int temp = data[start];  
            data[start] = data[j];  
            data[j] = temp;  
            //递归左子序列  
            quickSort(data, start, j-1);  
            //递归右子序列  
            quickSort(data, j+1, end);  
        }         
    }  
  
    public static void main(String[] args){  
        int data[] = {22,34,12,32,50,67,43,32};  
        System.out.print("排序前：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
        System.out.println();  
        quickSort(data, 0, data.length-1);  
        System.out.print("排序后：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
    }  
}
