package com.oujian.data.structures.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author annyu
 * @description 插入排序
 * @date 2020/4/24
 **/
public class InsertSort {
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
        int insertIndex=0;
        int insertValue=0;
        for (int i = 1; i < array.length; i++) {
            insertIndex=i;
            insertValue = array[i];
            //insertIndex>0 保证下标不越界
            //如果要插入的值比前一个值小，则将前一个值后移，直到找到有序数组中，比插入值小的值，则当前位置是插入的位置
            while (insertIndex>0&&insertValue<array[insertIndex-1]){
                array[insertIndex]=array[insertIndex-1];
                insertIndex--;
            }
            //判断是否需要改变
            if(insertIndex!=i) {
                array[insertIndex] = insertValue;
            }
        }
    }
}
