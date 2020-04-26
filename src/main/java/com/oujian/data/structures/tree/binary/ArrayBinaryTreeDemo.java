package com.oujian.data.structures.tree.binary;

/**
 * @author annyu
 * @description 顺序存储二叉树
 * @date 2020/4/26
 **/
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7};
        ArrayBinary arrayBinary = new ArrayBinary(array);
        arrayBinary.preOrder(0);
    }
}
class ArrayBinary{
    private int[] array;

    public ArrayBinary(int[] array) {
        this.array = array;
    }
    public void preOrder(int index){
        if(array==null||array.length==0){
            System.out.println("数组为空");
        }
        System.out.println(array[index]);
        //向左递归
        if(index*2+1<array.length){
            preOrder(index*2+1);
        }
        //向右digui
        if(index*2+2<array.length){
            preOrder(index*2+2);
        }

    }
}