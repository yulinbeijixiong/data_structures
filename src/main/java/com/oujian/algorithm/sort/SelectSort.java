package com.oujian.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author annyu
 * @description 选择排序
 * @date 2020/4/24
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] array=new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            array[i] = random.nextInt(100000000);
        }
        long now =System.currentTimeMillis();
        sort(array);
        System.out.println("花费的时间："+(System.currentTimeMillis()-now));

    }
    public static void sort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int max=array[i];
            int index=i;
            //找出最大值
            for (int j = i+1; j < array.length; j++) {
                if(array[j]>max){
                    max=array[j];
                    index=j;
                }
            }
            //判断是否发生交换
            if(index>i){
                array[index]=array[i];
                array[i]=max;
            }

        }
    }
}
