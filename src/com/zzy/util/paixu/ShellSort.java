package com.zzy.util.paixu;
/**
 * 7.希尔排序（ShellSort）:
 * 又称作缩小增量排序,其基本思想是：
 * 先取一个小于n的整数d1作为第一个增量，
 * 把文件的全部记录分成d1个组。
 * 所有距离为dl的倍数的记录放在同一个组中。
 * 先在各组内进行直接插人排序；
 * 然后，取第二个增量d2<d1重复上述的分组和排序，
 * 直至所取的增量dt=1(dt<dt-l<…<d2<d1)，
 * 即所有记录放在同一组中进行直接插入排序为止。
 * @author 011
 *
 */
public class ShellSort {
	public static void shellSort(int[] data){  
        int h = 1;  
        //得到增量序列的最大值  
        while(h <= data.length-3){  
            h = h*3+1;  
        }  
        while(h > 0){  
            for(int i = h; i<data.length; i++){  
                int temp = data[i];  
                if(data[i] <= data[i-h]){  
                    int j = i-h;  
                    for(; j >= 0 && data[j] > temp; j=j-h){  
                        data[j+h] = data[j];  
                    }  
                    //插入元素  
                    data[j+h] = temp;  
                }  
            }  
            h = (h-1)/3;  
        }  
    }  
      
    public static void main(String[] args){  
        int data[] = {22,34,12,32,50,67,43,32};  
        System.out.print("排序前：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
        System.out.println();  
        shellSort(data);  
        System.out.print("排序后：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
    }  
}
