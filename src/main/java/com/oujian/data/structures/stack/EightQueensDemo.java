package com.oujian.data.structures.stack;

/**
 * @author annyu
 * @description 八皇后问题
 * @date 2020/4/23
 **/
public class EightQueensDemo {
    //需要放几个皇后
    private static int max = 8;
    //使用索引代表第几个皇后
    //每个值代表第几列
    private static int[] array=new int[max];
    private static int count;
    private static  int  conflictCount;


    public static void main(String[] args) {
        check(0);
        System.out.println(count);
        System.out.println(conflictCount);
    }
    public static void check(int n){
        //证明前八个皇后已经放好
        if(n==max){
            count++;
            print();
            return;
        }
        //第一行的所有列依次放入第一个皇后
        for (int i = 0; i < max; i++) {
            //放置皇后，每行都会从第一列放到最后一列
            array[n]=i;
            //判断是否发生冲突，然后再回溯以前放置的皇后是否与当前皇后发生冲突
            if(!conflict(n)){
                //不发生冲突则放置下一个皇后
                check(n+1);
            }
        }
    }

    /**
     * 判断当前皇后是否与前面的皇后冲突
     * @param n
     * @return
     */
    public static boolean conflict(int n){
        for (int i = 0; i < n; i++) {
            conflictCount++;
            //在同一个列代表冲突
            //如果索引值的绝对值与值的绝对值相等证明在同一个斜线上
            if(array[n]==array[i]||Math.abs(n-i)==Math.abs(array[i]-array[n])){
                return true;
            }
        }
        return false;
    }
    public static void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+",");
        }
        System.out.println();
    }
}
