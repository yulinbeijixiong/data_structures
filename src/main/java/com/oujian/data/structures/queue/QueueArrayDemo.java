package com.oujian.data.structures.queue;

import java.util.Scanner;

/**
 * @author annyu
 * @description 数据模拟队列
 * @date 2020/4/21
 **/
public class QueueArrayDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("开始输入数据");
        ArrayQueue arrayQueue = new ArrayQueue(3);
        while (true) {
            int i = scanner.nextInt();
            arrayQueue.addElement(i);
            //System.out.println(arrayQueue.getElement());
            System.out.println(arrayQueue.getHead());
        }


    }

}
class ArrayQueue{
    /**
     * 最大容量
     */
    private int maxsize=0;
    /**
     * 队列的头部
     */
    private int front=-1;
    /**
     * 队列的尾部
     */
    private int rear=-1;
    /**
     * 储存数据
     */
    private int[] data ;
    public ArrayQueue(int maxsize){
        this.maxsize=maxsize;
        data = new int[maxsize];
    }
    private boolean isEmpty(){
        return front==rear;
    }
    private boolean isFull(){
        if(maxsize-1==rear){
            throw new RuntimeException("queue is full");
        }else {
            return false;
        }
    }

    /**
     * 队列中添加元素
     * @param element
     */
    public void addElement(int element){
        if(!isFull()){
            data[++rear]=element;
        }
    }

    /**
     * 获取一个元素
     * @return
     */
    public int getElement(){
        if(!isEmpty()){
            return data[++front];
        }else {
            throw new RuntimeException("queue is empty");
        }

    }
    public int getHead(){
        if(!isEmpty()){
            //并不是获取第一个元素
            return data[front+1];
        }else{
            throw new RuntimeException("queue is empty");
        }

    }
}