package com.oujian.algorithm.base;

import sun.jvm.hotspot.prims.JvmtiExport;

import java.util.Arrays;

/**
 * @author annyu
 * @description 克鲁斯卡尔算法
 * @date 2020/4/29
 **/
public class KruskalDemo {


    /**
     * 表示顶点不连通的距离
     */
    private static final int INF =100000;
    public static void main(String[] args) {
        int[][] weight={
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
                        };
        char[] data={'A','B','C','D','E','F','G'};

        Kruskal kruskal = new Kruskal(data, weight);
        kruskal.print();
        EData[] eData = kruskal.getEData();
        System.out.println(Arrays.toString(eData));
        kruskal.sortEData(eData);
        kruskal.kruskal();
    }
}

class Kruskal {
    private static final int INF =100000;
    private char[] vertex;
    private int[][] weight;
    /***
     * 边的数量
     */
    private int sideCount;

    /**
     * 创建图
     * @param vertexList
     * @param weightList
     */
    public Kruskal(char[] vertexList, int[][] weightList) {
        vertex=new char[vertexList.length];
        weight=new int[vertexList.length][vertexList.length];
        System.arraycopy(vertexList, 0, vertex, 0,vertexList.length);
        for (int i = 0; i < weightList.length; i++) {
            System.arraycopy(weightList, 0, weight, 0, weightList.length);
        }
        for (int i = 0; i < vertex.length; i++) {
            for (int j =i+1 ; j < vertex.length; j++) {
                if(weight[i][j]!=100000){
                    sideCount++;
                }

            }
        }
    }

    public void print(){
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%7d",weight[i][j]);

            }
            System.out.println();

        }
    }

    /**
     * 获取边以及权值
     * @return
     */
    public EData[] getEData(){
        int index=0;
        EData[] eData = new EData[sideCount];
        for (int i = 0; i <vertex.length ; i++) {
            for (int j = i+1; j <vertex.length ; j++) {
                if(weight[i][j]!=INF){
                    eData[index++]=new EData(vertex[i],vertex[j],weight[i][j]);
                }
            }
            
        }
        return eData;
    }

    /**
     * 将边的权值进行排序
     * @param edata
     */
    public void sortEData(EData[] edata){
        EData temp;
        for (int i = 0; i < edata.length; i++) {
            for (int j = 0; j <edata.length-i-1; j++) {
                if(edata[j].weight>edata[j+1].weight){
                    temp=edata[j+1];
                    edata[j+1]=edata[j];
                    edata[j]=temp;
                }

            }

        }
    }

    /**
     * 获取顶点
     * @param ends
     * @param i
     * @return
     */
    public int getEnd(int[] ends,int i){
        while(ends[i]!=0){
            i=ends[i];
        }
        return i;
    }
    public int getPosition(char ch){

        for (int i = 0; i <vertex.length ; i++) {
            if(vertex[i]==ch){
                return i;
            }
            
        }
        return -1;
    }
    public void kruskal(){
        int index=0;
        //保存最小生成树的终点
        int[] ends=new int[sideCount];
        //保存结果
        EData[] eData = new EData[sideCount];
        //获取所有边的集合
        EData[] sideList = getEData();
        //对所有边进行排序
        sortEData(sideList);
        for (int i = 0; i < sideCount; i++) {
            //获取每一条边的起始点和终点
            int p1 = getPosition(sideList[i].start);
            int p2 = getPosition(sideList[i].end);
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            //
            if(m!=n){
                ends[m]=n;
                eData[index++]=sideList[i];
            }
        }
        for (int i = 0; i <eData.length ; i++) {
            if(eData[i]!=null) {
                System.out.println(eData[i]);
            }
        }


    }
}
class EData{
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}