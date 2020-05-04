package com.oujian.algorithm.search;

/**
 * @author annyu
 * @description 插值查找
 * @date 2020/4/25
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array=new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i]=i;
        }
        int search = search(array, 0, array.length-1, 78);
        System.out.println(search);
    }

    public static int search(int[] array,int left,int right,int findValue){
        System.out.println("------");
        if(left>right||findValue<array[left]||findValue>array[right]){
            return -1;
        }
        int mid = left+(left+right)*(findValue-array[left])/(array[right]-array[left]);
        int midValue=array[mid];
        if(findValue>midValue){
            return search(array,mid+1,right,findValue);
        }else if(findValue<midValue){
            return search(array, left, mid, findValue);
        }else {
            return mid;
        }
    }
}
