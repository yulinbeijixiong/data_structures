package com.oujian.algorithm.base;

/**
 * @author annyu
 * @description 非递归二分查找
 * @date 2020/4/28
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6};
        int search = search(array, 5);
        System.out.println(search);
    }
    public static int search(int[] array,int target){
        int left =0;
        int right=array.length;

        while(left<=right){
            int mid = (left+right)/2;
            if(target==array[mid]){
                return mid;
            }else if(target>array[mid]){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return -1;
    }
}
