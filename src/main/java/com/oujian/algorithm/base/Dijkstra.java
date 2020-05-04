package com.oujian.algorithm.base;

import sun.jvm.hotspot.debugger.linux.LinuxDebugger;

import java.util.Arrays;

/**
 * @author annyu
 * @description  迪杰斯特拉算法
 * @date 2020/5/4
 **/
public class Dijkstra {
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
        Graph1 graph1 = new Graph1(vertex, matrix);
        graph1.show();
        graph1.djs(6);
        graph1.showDijkstra();

    }
}
class Graph1{
    public char[] vertex;
    public int[][] matrix;
    public VisitedVertex vv;

    /**
     * 初始化图
     * @param vertex
     * @param matrix
     */
    public Graph1(char[] vertex, int[][] matrix) {
       this.vertex= new  char[vertex.length];
       this.matrix=new int[vertex.length][vertex.length];
        System.arraycopy(vertex, 0, this.vertex, 0, vertex.length);
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i],0,this.matrix[i],0,matrix.length);
        }
    }

    /**
     * 展示邻阶矩阵
     */
    public void show(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 展示迪杰斯特拉结果
     */
    public void showDijkstra(){
        vv.show();
    }
    public void update(int index){
        int len;
        //遍历与当前节点相关的点
        for (int i = 0; i < matrix[index].length; i++) {
             len = vv.getDis(index) + matrix[index][i];
             //如果节点没有访问则更新距离与前驱节点
             if(!vv.isVisited(i)&& len<vv.getDis(i)){
                 //更新前驱节点
                 vv.updatePreVertex(i,index);

                 vv.updateDis(i,len);
             }
        }
    }
    public void djs(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 0; i < vertex.length; i++) {
            index=vv.updateArray();
            update(index);
        }

    }

}
class VisitedVertex{
    /**
     * 已访问顶点数组
     */
    public int[] alreadyArray;
    /**
     * 顶点到当前节点最短路径
     */
    public int[] dis;
    /**
     * 前置节点
     */
    public int[] preVertexArray;


    public VisitedVertex(int len,int index) {
        alreadyArray = new int[len];
        dis = new int[len];
        preVertexArray = new int[len];

        //开始节点初始化
        alreadyArray[index]=1;
        //初始化开始节点与其他节点的距离
        Arrays.fill(dis,65535);
        //将当前节点的距离设置为0
        dis[index]=0;

    }
    public void updateAlreadyArray(int index){
        alreadyArray[index]=1;
    }
    public void updateDis(int index,int len){
        dis[index]=len;
    }
    public void updatePreVertex(int index,int preVertex){
        preVertexArray[index]=preVertex;
    }
    public boolean isVisited(int index){
        return alreadyArray[index]==1;
    }
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 访问下一个节点并返回索引
     * @return
     */
    public int updateArray(){
        int min=65535,index=0;
        //遍历没有访问的数组
        for (int i = 1; i < alreadyArray.length; i++) {
            //判断节点是否已经访问没有，就对距离进行更新
            if(alreadyArray[i]==0&&dis[i]<min){
                min=dis[i];
                index=i;
            }
            
        }
        //更新已访问节点
        alreadyArray[index]=1;
        return index;
    }
    public void show(){
        System.out.println(Arrays.toString(alreadyArray));
        System.out.println(Arrays.toString(preVertexArray));
        System.out.println(Arrays.toString(dis));
        char[] vertex={'A','B','C','D','E','F','G'};
        int count=0;
        for (int i:dis) {
            if(i!=65535){
                System.out.printf(vertex[count]+"-%d"+"\t",i);
            }else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
    
}