package com.oujian.algorithm.base;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author annyu
 * @description kmp 算法
 * @date 2020/4/28
 **/
public class KMPDemo {
    public static void main(String[] args) {
        String str = "ABBA";
        String str2 = "ABCCDAAVBCDABBABCDA";
        System.out.println(Arrays.toString(kmpNext(str)));
        System.out.println(kmp(str2, str, kmpNext(str)));
    }

    public static int kmp(String str, String matchStr, int[] next) {


        for (int i = 0, j = 0; i < str.length(); i++) {
            //核心知识点
            while (j>0&&str.charAt(i) != matchStr.charAt(j)){
                j=next[j-1];
            }
            if (str.charAt(i) == matchStr.charAt(j)) {
                j++;
            }
            if (j == matchStr.length()) {
                return i - j + 1;
            }
        }
        return -1;


    }

    public static int[] kmpNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
