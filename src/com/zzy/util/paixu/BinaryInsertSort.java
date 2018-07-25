package com.zzy.util.paixu;
/**
 * 6.折半插入排序（BinaryInsertSort）:
 * 折半插入排序是对直接插入排序的改进，
 * 具体操作为：在将一个新元素插入已排好序的数组的过程中，
 * 寻找插入点时，将待插入区域的首元素设置为a[low],
 * 末元素设置为a[high]，则轮比较时将待插入元素与a[m],
 * 其中m=(low+high)/2相比较,如果比参考元素小，
 * 则选择a[low]到a[m-1]为新的插入区域(即high=m-1)，
 * 否则选择a[m+1]到a[high]为新的插入区域（即low=m+1），
 * 如此直至low<=high不成立，即将此位置之后所有元素后移一位，
 * 并将新元素插入a[high+1]。
 * @author 011
 *
 */
public class BinaryInsertSort {
	public static void binaryInsertSort(int[] data){  
        for(int i=1; i<data.length; i++){  
            //保存要插入的数据  
            int temp = data[i];  
            int low = 0;  
            int high = i-1;  
            while(low <= high){  
                //找出low和high中间的索引  
                int mid = (low+high)/2;  
                if(temp > data[mid]){  
                    //限制在大于mid的那一半搜索  
                    low = mid + 1;  
                }else{  
                    //限制在小于mid的那一半搜索  
                    high = mid - 1;  
                }  
            }  
            //将low到i处的所有元素移位  
            for(int m=i; m>low; m--){  
                data[m] = data[m-1];  
            }  
            //插入元素  
            data[low] = temp;  
        }  
    }  
      
    public static void main(String[] args){  
        int data[] = {22,34,12,32,50,67,43,32};  
        System.out.print("排序前：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
        System.out.println();  
        binaryInsertSort(data);  
        System.out.print("排序后：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
    }  
  
}
