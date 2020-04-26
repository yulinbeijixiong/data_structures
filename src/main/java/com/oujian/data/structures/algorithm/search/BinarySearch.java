package com.oujian.data.structures.algorithm.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author annyu
 * @description 二分法查找
 * @date 2020/4/25
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] array={1,2,10,20,30,50,50,100,1000};
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> integers = search1(array, 50, 0, array.length, result);
        System.out.println(Arrays.toString(integers.toArray()));
    }

    /**
     * 不能查询出多个
     * @param array
     * @param findValue
     * @param left
     * @param right
     * @return
     */
    public static int search(int[] array,int findValue,int left,int right){
        if(left>right){
            return -1;
        }
        int mid = (left+right)/2;
        int midValue=array[mid];
        if(findValue>midValue){
          return   search(array,findValue,mid+1,right);
        }else if(findValue<midValue){
          return   search(array,findValue,left,mid);
        }else{
            return mid;
        }
    }
    public static List<Integer> search1(int[] array, int findValue, int left, int right, List<Integer> result){
        if(left>right){
            result.add(-1);
            return result;
        }
        int mid = (left+right)/2;
        int midValue=array[mid];
        if(findValue>midValue){
              return search1(array,findValue,mid+1,right,result);
        }else if(findValue<midValue){
            return   search1(array,findValue,left,mid,result);
        }else{
            int leftTemp=mid-1;

            while (true){
                if(leftTemp<0||array[leftTemp]!=findValue){
                    break;
                }
                result.add(leftTemp);
                leftTemp--;

            }
            int rightTemp=mid+1;

            while (true){
                if(rightTemp>array.length-1||array[rightTemp]!=findValue){
                    break;
                }
                result.add(rightTemp);
                rightTemp--;

            }
            result.add(mid);
            return result;
        }
    }
}
