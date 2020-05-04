package com.oujian.algorithm.sort;

import java.util.Arrays;

/**
 * @author annyu
 * @description 桶排序
 * @date 2020/4/24
 **/
public class CardinalitySort {
    public static void main(String[] args) {
        int[] array={12,23,7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        int maxElement = array[0];
        //用于储存的桶
        int[][] barrel = new int[10][array.length];
        //用于存储桶中有多少个元素
        int[] count = new int[10];
        //求去元素的最大值，方便判断需要循环几次
        for (int i = 1; i < array.length; i++) {
            if (maxElement < array[i]) {
                maxElement = array[i];
            }
        }
        //求出最大元素的位置
        int maxElementLength = (maxElement + "").length();
        for (int i = 0, n = 1; i < maxElementLength; i++, n *= 10) {
            //将元素放入桶中
            for (int j = 0; j < array.length; j++) {
                int index = (array[j] / n) % 10;
                barrel[index][count[index]] = array[j];
                count[index]++;
            }
            int arrayIndex = 0;
            //从桶中取元素放入数组中
            for (int j = 0; j < barrel.length; j++) {
                //如果桶中元素的个数为零则直接跳过
                if (count[j] != 0) {
                    for (int k = 0; k < count[j]; k++) {
                        array[arrayIndex++] = barrel[j][k];
                    }
                    count[j] = 0;
                }
            }

        }
    }

}
