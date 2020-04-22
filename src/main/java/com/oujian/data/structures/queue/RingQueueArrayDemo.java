package com.oujian.data.structures.queue;

import java.util.Scanner;

/**
 * @author annyu
 * @description 环形队列
 * @date 2020/4/21
 **/
public class RingQueueArrayDemo{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一个数字");
        RingQueueArray ringQueueArray = new RingQueueArray(3);
        while (true){
            int element = scanner.nextInt();
            ringQueueArray.addElement(element);
            ringQueueArray.showQueue();
            System.out.println(ringQueueArray.getHead());
            System.out.println(ringQueueArray.getElement());
        }
    }
}
 class RingQueueArray {
    private int maxsize;
    /**
     * 第一个元素
     */
    private int front;
    /**
     * 最后一个元素的后一个元素
     */
    private int rear;
    private int[] data;
    public RingQueueArray(int maxsize){
        this.maxsize=maxsize;
        data=new int[maxsize];
    }
    public boolean isEmpty(){
        return front==rear;
    }
    public boolean isFull(){
        return (rear+1)%maxsize==front;
    }
    public void addElement(int element){
        if(!isFull()){
            data[rear]=element;
            //rear后移一位
            rear=(rear+1)%maxsize;
        }else{
            throw new RuntimeException("queue is full");
        }

    }
    public int getElement(){
        if(!isEmpty()){
            int returnElement = data[front];
            front=(front+1)%maxsize;
            return returnElement;
        }else {
            throw new RuntimeException("queue is empty");
        }
    }
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("queue is empty");

        }
        for (int i = front;  i<front+size(); i++) {
            System.out.println(data[i%maxsize]);
        }
    }
    public int getHead(){
        if(isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        return data[front];
    }

     /**
      * 获取元素个数
      * @return
      */
    public int size(){
        return (rear+maxsize-front)%maxsize;
    }

}
