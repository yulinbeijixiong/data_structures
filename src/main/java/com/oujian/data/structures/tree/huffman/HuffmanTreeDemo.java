package com.oujian.data.structures.tree.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author annyu
 * @description 赫夫曼树
 * @date 2020/4/26
 **/
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] array={13,7,8,3,29,6,1};
        Node huffManTree = createHuffManTree(array);
        huffManTree.proOrder();

    }
    public static Node createHuffManTree(int[] array){
        List<Node> list = new ArrayList<Node>();
        for (int i = 0; i < array.length; i++) {
            list.add(new Node(array[i]));
        }

        while(list.size()>1){
            //按从小到大进行排序
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            //左右节点相加
            Node node = new Node(leftNode.getValue() + rightNode.getValue());
            node.setLeftNode(leftNode);
            node.setRightNode(rightNode);
            //从集合中删除左右节点
            list.remove(leftNode);
            list.remove(rightNode);
            //将两个节点的权值加入数组
            list.add(node);

        }
        return list.get(0);
    }
}

class Node implements Comparable<Node> {
    private int value;
    private Node leftNode;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
    public void proOrder(){
        System.out.println(this);

        if(this.leftNode!=null){
            this.leftNode.proOrder();
        }

        if(this.rightNode!=null){
            this.rightNode.proOrder();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }
}
