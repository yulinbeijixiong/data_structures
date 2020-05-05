package com.oujian.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author annyu
 * @description 堆排序
 * @date 2020/4/26
 **/
public class HeapSort {

    public static void main(String[] args) {
        int[] array=new int[8000000];
        Random random = new Random();
        for (int i = 0; i < 8000000; i++) {
            array[i] = random.nextInt(100000000);
        }

        long now =System.currentTimeMillis();
        sort(array);
        System.out.println("花费的时间："+(System.currentTimeMillis()-now));
    }

    /**
     *
     * @param array
     */
    public static void sort(int[] array){
        //从左最低层一个非子节点开始
        for (int i = array.length/2 -1; i >=0; i--) {
            adjustHeap1(array,i,array.length);
        }
        int temp;

        for (int i = array.length-1; i >0 ; i--) {
            //将最大值放在最后
            temp=array[i];
            array[i]=array[0];
            array[0]=temp;
            //缩小顶大堆的规模，再将剩下的数，排成顶大堆
            adjustHeap1(array,0,i);
        }
    }

    /**
     * 大顶堆
     * @param array 排序数组
     * @param start 从哪个节点开始排序
     * @param length 排序的堆的长度
     */
    public static void adjustHeap(int[] array,int start,int length){
        int temp=array[start];
        //数组转二叉树的规则
        //当前节点的左子节点=当前节点的索引*2+1
        //当前节点的右子节点=当前节点的索引*2+2
        for (int i = start*2+1; i < length; i=i*2+1) {
            //找出左右节点较大的
            if(i+1<length&&array[i]<array[i+1]){
               //将当前节点指向右节点
                i++;
            }
            //比较temp与当前节点的大小
            if(temp<array[i]){
                //将起始点指向较大值
                array[start]=array[i];
                //并将起始点的指针移到当前节点
                start=i;
            }else{
                break;
            }
        }
        //将temp的值赋给当前节点，因为start 在循环中会变成较大值的索引，这么做是没有问题的
        array[start]=temp;
        
    }

    /**
     * 降序排列使用小顶堆
     * 小顶堆
     * @param array
     * @param start
     * @param length
     */
    public static void adjustHeap1(int[] array,int start,int length){
        int temp=array[start];

        for (int i = start*2+1; i <length; i=i*2+1) {
            //先比较左右节点
            if(i+1<length&& array[i]>array[i+1]){
                i++;
            }
            //比较当前值与左右节点的最大值进行比较
            if(temp>array[i]){
                array[start]=array[i];
                start=i;
            }else{
                break;
            }
        }
        array[start]=temp;

    }

}



