package com.oujian.data.structures.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author annyu
 * @description 希尔排序
 * @date 2020/4/24
 **/
public class ShellSort{
    public static void main(String[] args) {
//        int[] array=new int[80000000];
//        Random random = new Random();
//        for (int i = 0; i < 80000000; i++) {
//            array[i] = random.nextInt(1000000000);
//        }
        int[] array = {1, 3, 5, -1, -4, -7, 10};
        long now =System.currentTimeMillis();
        sort1(array);
       System.out.println(Arrays.toString(array));
        System.out.println("花费的时间："+(System.currentTimeMillis()-now));
    }

    /**
     * 交换法
     * @param array
     */
    public static void sort(int[] array){
        int temp=0;
        //对数组进行分组
        for (int i = array.length/2; i >0; i/=2) {
            //j=i 是因为一个元素时不需要比较，所以是在一个步长后才开始遍历
            for (int j = i; j <array.length ; j++) {
                // 后来的数都会依次与前一个数进行比较
                for (int k = j-i; k >=0; k-=i) {
                    if(array[k]>array[k+i]) {
                        temp = array[k];
                        array[k] = array[k + i];
                        array[k + i] = temp;
                    }
                }
            }
        }
    }

    /**
     * 移位法
     * @param array
     */
    public static void sort1(int[] array){
        for (int i = array.length/2; i >0 ; i/=2) {
            for (int j = i; j <array.length ; j++) {
                int index=j-i;
                int temp=array[j];
                //将对当前数字比小的，则将数字后移一个步长
                while (index >= 0 && temp>array[index]) {

                    array[index+i]=array[index];
                    index -=i;
                }
                array[index+i]=temp;
            }
        }
    }
}
