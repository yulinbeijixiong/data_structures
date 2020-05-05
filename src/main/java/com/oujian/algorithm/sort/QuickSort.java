package com.oujian.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author annyu
 * @description 快速排序
 * @date 2020/4/24
 **/
public class QuickSort {
    public static void main(String[] args) {
//        int[] array = new int[800000];
//        Random random = new Random();
//        for (int i = 0; i < 800000; i++) {
//            array[i] = random.nextInt(1000000000);
//        }
//        long now =System.currentTimeMillis();
       int[] array={1,2,-4,-7,-8,6,10};
        sort(0, array.length - 1, array);
        System.out.println(System.currentTimeMillis());

    }
    public static void sort(int left, int right, int[] array) {
        //left >=right 终止递归
        if (left < right) {
            int l = left;
            int r = right;
            int standPoint = array[l];
            //当l==r 跳出循环
            while (l < r) {
                //在左边找到比基准数小的数
                while (l < r && standPoint <= array[r]) {
                    r--;
                }
                //将右边数传到左边
                array[l] = array[r];
                //在右边找到比基准数小的
                while (l < r && standPoint >= array[l]) {
                    l++;
                }
                //将左边的数传给右边
                array[r] = array[l];

            }
            //然后将标准值赋给左边
            array[l] = standPoint;
            //标准数左边的数递归
            sort(left, l, array);
            //标准数右边的数递归
            sort(l + 1, right, array);
        }

    }
}
