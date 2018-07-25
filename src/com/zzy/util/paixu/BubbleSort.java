package com.zzy.util.paixu;
/**
 * 冒泡排序（BubbleSort）:冒泡排序是最简单的排序算法之一，
 * 实现起来也比较简单，其原理就是进行n-1趟比较并交换，
 * 小数往上冒，大数往下沉，经过n-1趟之后形成了有序的数列。
 * @author 011
 *
 */
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = {4,1,3,2,6,5};
		System.out.println("排序前:");
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		for(int i=0;i<a.length;i++){
			int temp=0;
			for(int j=0;j<a.length-i-1;j++){
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
		System.out.println();
		System.out.println("排序后:");
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}

}
