package com.oujian.algorithm.search;

import java.util.Arrays;

/**
 * @author annyu
 * @description 斐波那契查找
 * @date 2020/4/25
 **/
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] array=new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i]=i;
        }
        int search = search(array, 78);
        System.out.println(search);
    }

    /**
     * 生成斐波拉契数列
     * @param maxLength
     * @return
     */
    public static int[] fib(int maxLength){
        int[] array = new int[maxLength];
        array[0]=1;
        array[1]=1;
        for (int i = 2; i < maxLength; i++) {
            array[i]=array[i-1]+array[i-2];
        }
        return array;
    }
    public static int search(int[] array,int findValue){

        int low=0;
        int high=array.length-1;
        int mid;
        int[] fib = fib(20);
        //斐波那契数列下标
        int k=0;

        while(high>fib[k]-1){
            k++;
        }
        int[] newArray = Arrays.copyOf(array, fib[k]);
        for (int i = high+1; i < newArray.length; i++) {
            newArray[i]=array[high];
        }
        while(low<=high){
            mid=low+fib[k-1]-1;
            if(findValue<newArray[mid]){
                high=mid-1;
                k--;
            }else if(findValue>newArray[mid]){
                low=mid+1;
                k-=2;
            }else {
                if(mid<=high){
                   return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
