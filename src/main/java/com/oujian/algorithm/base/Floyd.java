package com.oujian.algorithm.base;

import java.util.Arrays;

/**
 * @author annyu
 * @description 弗洛伊德算法
 * @date 2020/5/4
 **/
public class Floyd {
    public static void main(String[] args) {
        char[] vertex={'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N=65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph2 graph2 = new Graph2(vertex, matrix);
        graph2.floyd();
        graph2.show();

    }
}
class Graph2{
    public char[] vertex;
    public int[][] dis;
    public int[][] pre;

    public Graph2(char[] vertex, int[][] dis) {
        this.vertex = vertex;
        this.dis = dis;
        //初始化前驱节点
        pre=new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i],i);

        }
    }
    public void show(){
       char[] vertex={'A','B','C','D','E','F','G'};
        for (int i = 0; i <dis.length ; i++) {
            for (int j = 0; j <dis.length ; j++) {
                System.out.print(vertex[pre[i][j]]+"\t");
            }
            System.out.println();
            for (int j = 0; j <dis.length ; j++) {
                System.out.println("从"+vertex[i]+"到"+vertex[j]+"的距离为"+dis[i][j]);
            }
            System.out.println();
        }
    }
    public void floyd(){
        int len=0;
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                for (int k = 0; k < dis.length; k++) {
                    //获取j->i 和 i->k 的距离
                    len=dis[j][i]+dis[i][k];
                    // 如果j->i->k 小于j->k的距离
                    if(len<dis[j][k]){
                        //更新距离
                        dis[j][k]=len;
                        //更新前驱节点
                        pre[j][k]=pre[i][k];
                    }
                }
            }
        }
    }
}