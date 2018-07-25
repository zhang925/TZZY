package com.zzy.util.paixu;

import java.util.Arrays;

public class ArraysSort {
	 public static void main(String[] args){  
		 int a[] = {4,1,3,2,6,5};
			System.out.println("排序前:");
			for(int i=0;i<a.length;i++){
				System.out.print(a[i]+" ");
			}
			Arrays.sort(a);
			System.out.println();
			System.out.println("排序后:");
			for(int i=0;i<a.length;i++){
				System.out.print(a[i]+" ");
			}
			
			System.out.println();
			for(int i=a.length-1;i>=0;i--){
				System.out.print(a[i]+" ");
			}
	 }
}
