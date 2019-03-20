package com.xjy.sort;

import java.util.Arrays;

/**
 * @Author: Mr.Xu
 * @Date: Created in 9:25 2019/3/16
 * @Description:希尔排序 关键词：增量序列+插入排序
 */
public class ShellSort {
    public static void sort(int[] a){
        int j = 0,h = 1, N = a.length;
        while(h < N/3) h = 3 * h + 1;
        while(h >= 1){
            for(int i = h; i < N; i++){
                int tmp = a[i];
                for(j = i; j >= h && tmp < a[j-h]; j -= h){
                    a[j] = a[j-h];
                }
                a[j] = tmp;
            }
            h /= 3;
        }
    }
    //普通插入排序
    public static void insertionSort(int[] a){
        int j;
        for(int i = 1; i < a.length; i++){
            int tmp = a[i];
            for(j = i; j > 0 && tmp < a[j - 1]; j--){
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int a[] = new int[]{3,5,2,8,7,19,3,76,51,-2,30,89};
        System.out.println("排序前："+ Arrays.toString(a));
        ShellSort.sort(a);
        System.out.println("希尔排序后："+Arrays.toString(a));
    }
}
