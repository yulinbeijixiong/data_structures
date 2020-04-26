package com.oujian.data.structures.algorithm.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author annyu
 * @description
 * @date 2020/4/23
 **/
public class BubbleSort {
    public static void main(String[] args) {
//        int[] array=new int[80000];
//        Random random = new Random();
//        for (int i = 0; i < 80000; i++) {
//            array[i] = random.nextInt(100000000);
//        }
        int[] array={4,3,2,1};
        long now = System.currentTimeMillis();
        sort(array);
        System.out.println(System.currentTimeMillis()-now);
    }
    public static void sort(int[] array){
        //如果没有发生交换
        boolean flag=false;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j <array.length-i-1; j++) {
                int temp;
                //进行比较交换
                if(array[j+1]<array[j]){
                    temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                    flag=true;
                }
            }
            if(!flag){
                break;
            }else{
                //重置false
                flag=false;
            }

        }
        System.out.println(Arrays.toString(array));
    }
}
