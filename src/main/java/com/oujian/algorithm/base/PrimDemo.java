package com.oujian.algorithm.base;

/**
 * @author annyu
 * @description 普利姆算法
 * @date 2020/4/29
 **/
public class PrimDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        //权值设置为10000表示不能访问
        int[][] weight ={
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};
        char[] vertex={'A','B','C','D','E','F','G'};
        graph.createGraph(vertex,weight);
        MinTree minTree = new MinTree();
        minTree.createMinTree(graph,0);


    }
}
class MinTree {
    public void createMinTree(Graph graph,int v){
        int[] isVisited=new int[graph.getVertex().length];
        int length = graph.getVertex().length;
        isVisited[v]=1;
        int h1=-1;
        int h2=-1;
        int minWeight=100000;
        //依次遍历每个顶点
        for (int i = 1; i <length ; i++) {
            //遍历已经访问的顶点
            for (int j = 0; j <length ; j++) {
                //遍历没有访问的顶点
                for (int k = 0; k < length; k++) {
                    //当j的顶点已经访问，k的节点没有访问，且当前的权值小于最小权值
                    if(isVisited[j]==1&&isVisited[k]==0&&graph.getWeight()[j][k]<minWeight){
                        minWeight=graph.getWeight()[j][k];
                        h1=j;
                        h2=k;
                    }
                }

            }
            System.out.println("边>"+graph.getVertex()[h1]+"->"+graph.getVertex()[h2]+"权值："+minWeight);
            //将k 以前没有访问的节点置为已访问
            isVisited[h2]=1;
            //重置最小节点
            minWeight=10000;
        }


    }
}
class Graph{
    private int countVertex;
    private char[] vertex;
    private int[][] weight;

    public int getCountVertex() {
        return countVertex;
    }

    public int[][] getWeight() {
        return weight;
    }

    public void setWeight(int[][] weight) {
        this.weight = weight;
    }

    public void setCountVertex(int countVertex) {
        this.countVertex = countVertex;
    }

    public char[] getVertex() {
        return vertex;
    }

    public void setVertex(char[] vertex) {
        this.vertex = vertex;
    }

    public Graph(int countVertex) {
        this.countVertex = countVertex;
        vertex = new char[countVertex];
        weight=new int[countVertex][countVertex];
    }
    public void createGraph(char[] vertexList,int[][] weightList){
        System.arraycopy(vertexList, 0, vertex, 0, vertexList.length);
        for (int i = 0; i < weightList.length; i++) {
            System.arraycopy(weightList[i], 0, weight[i], 0, weightList[i].length);
        }
    }

}