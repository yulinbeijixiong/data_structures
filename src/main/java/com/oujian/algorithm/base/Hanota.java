package com.oujian.algorithm.base;

/**
 * @author annyu
 * @description 分治算法实践汉若塔
 * @date 2020/4/28
 **/
public class Hanota {
    public static void main(String[] args) {
        mobile(3,'A','B','C');
    }
    public static void mobile(int num,char a,char b,char c){
        if(num==1){
            System.out.println("将第"+num+"盘从"+a+"->"+c);
        }else{
            //先将上面第num-1 个盘从a移动到b,然后将第num个盘从 a 移动到c
            mobile(num-1,a,c,b);
            System.out.println("将第"+num+"盘从"+a+"->"+c);
            mobile(num-1,b,a,c);
        }
    }
}
