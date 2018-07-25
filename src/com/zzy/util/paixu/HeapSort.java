package com.zzy.util.paixu;
/**
 *   2.堆排序（HeapSort）:先说下堆的概念，
 *   假设有n个数据元素的序列k0,k1,k2,k3,...,kn-1,
 *   当且仅当满足下列关系时，可以将这组数据称为小顶堆，
 *   即ki <= k2i+1且 ki<= k2i+2
 *   (其中i=0,2,4,...,(n-1)/2);
 *   或者，满足如下关系成为大顶堆，
 *   即ki >= k2i+1且 ki >= k2i+2
 *   (其中i=0,2,...,(n-1)/2)。
 *   如果将堆排成一棵完全二叉树，则小顶堆的特点是：
 *   树中所有节点的值都小于其左右节点的值，且根节点的值最小；
 *   而大顶堆相反。堆排序的关键在于：
 *   1.建堆（大顶堆或小顶堆）2.拿堆的根节点和最后一个节点交换。
 * @author 011
 *
 */
public class HeapSort {
	
	public static void heapSort(int[] data){  
        //循环建堆  
        for(int i=0; i<data.length-1; i++){  
            buildMaxHeap(data, data.length-1-i);  
            //交换栈顶和最后一个元素  
            swap(data, 0, data.length-1-i);  
        }  
    }  
      
    public static void buildMaxHeap(int[] data, int lastIndex){  
        //从最后一个节点的父节点开始  
        for(int i=(lastIndex-1)/2; i>=0; i--){  
            //保存当前正在判断的节点  
            int k = i;  
            //如果当前k节点的子节点存在  
            while(k*2+1 <= lastIndex){  
                int biggerIndex = 2*k+1;  
                if(biggerIndex < lastIndex){  
                    if(data[biggerIndex] < data[biggerIndex+1]){  
                        biggerIndex++;  
                    }  
                }  
                if(data[k] < data[biggerIndex]){  
                    //交换  
                    swap(data, k, biggerIndex);  
                    k = biggerIndex;  
                }else{  
                    break;  
                }  
            }  
        }  
    }  
      
    public static void swap(int[] data, int i, int j){  
        int temp = data[i];  
        data[i] = data[j];  
        data[j] = temp;  
    }  
      
    public static void main(String[] args){  
        int data[] = {22,34,12,32,50,67,43,32};  
        System.out.print("排序前：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
        System.out.println();  
        heapSort(data);  
        System.out.print("排序后：");  
        for(int i=0; i<data.length; i++){  
            System.out.print(data[i]+" ");  
        }  
    }  
    
}
