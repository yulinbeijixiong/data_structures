package com.oujian.data.structures.algorithm.sort;

import java.util.Arrays;

/**
 * @author annyu
 * @description 归并排序
 * @date 2020/4/24
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] a= {1,-3,2,9,-1,10,-5};
        int[] b= new int[a.length];
        mergeSort(a,0,a.length-1,b);
        System.out.println(Arrays.toString(a));
    }
    public static void mergeSort(int[]array, int left,int right,int[] temp){
        if(left<right){
            int mid = (left + right)/2;
            mergeSort(array,left,mid,temp);
            mergeSort(array,mid+1,right,temp);
            merge(array,left,right,mid,temp);
        }
    }
    public static void merge(int[]array,int left,int right,int mid,int[] temp){
        int t=0;
        int i =left;
        int j = mid+1;
        while (i<=mid &&  j<=right){
            if(array[i]<array[j]){
                temp[t]=array[i];
                i++;
                t++;
            }else {
                temp[t]=array[j];
                j++;
                t++;
            }

        }
        while (i<=mid){
            temp[t]=array[i];
            t++;
            i++;
        }
        while(j<=right){
            temp[t]=array[j];
            t++;
            j++;
        }
        t=0;
        int tempLeft =left;
        while(tempLeft<=right){
            array[tempLeft]=temp[t];
            tempLeft++;
            t++;
        }
    }
}
