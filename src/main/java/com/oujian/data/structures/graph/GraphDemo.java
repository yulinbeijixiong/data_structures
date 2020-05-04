package com.oujian.data.structures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author annyu
 * @description 图
 * @date 2020/4/27
 **/
public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexs={"A","B","C","D","E" };
        for (int i = 0; i < vertexs.length; i++) {
            graph.addVertex(vertexs[i]);
        }
        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,0,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);
        graph.insertEdges(2,0,1);
        graph.insertEdges(2,1,1);
        graph.insertEdges(3,1,1);
        graph.insertEdges(4,1,1);
        graph.showGraph();
//        graph.dfs();
        graph.bfs();

    }
}
class Graph{
    /**
     * 储存顶点信息
     */
   private List<String> vertexs;
    /**
     * 存储边
     */
   private int[][] edges;
    /**
     * 存储边的条数
     */
    private int numOfEdges;
    private boolean[] isVisited;
    
    

    public Graph(int n) {
        edges=new int[n][n];
        vertexs= new ArrayList<String>(n);
        isVisited=new boolean[n];
        numOfEdges=0;
    }

    /**
     * 添加顶点
     * @param vertex
     */
    public void addVertex(String vertex){
        vertexs.add(vertex);
    }

    /**
     * 返回顶点个数
     * @return
     */
    public int getNumVertex(){
        return vertexs.size();
    }

    /**
     * 放回边的条数
     * @return
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 插入边
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2]=weight;
    }

    /**
     * 通过下标获取顶点
     * @return
     */
    public String getValueByIndex(int index){
        return vertexs.get(index);
    }

    /**
     * 展示图
     */
    public void showGraph(){
        for (int[] link:edges
             ) {
            System.out.println(Arrays.toString(link));
        }
    }
    public void dfs(int index){
        System.out.print(vertexs.get(index)+"->");
        //将该点设置成已访问
        isVisited[index]=true;
        int w = getFirstNear(index);

        while (w!=-1){
            //如果没有被访问
            if(!isVisited[w]){
                //则进行递归深度遍历,把原来的列变成行传入
                dfs(w);
            }
            //否则获取下一个邻近节点
             w = getNextNear(index, w);
        }

    }
    public void bfs(int index){
        int u;
        int w;
        //创建一个队列用于保存已经遍历的节点
        LinkedList<Integer> queue = new LinkedList<Integer>();
        System.out.println(getValueByIndex(index)+"->");
        isVisited[index]=true;
        queue.addLast(index);
        while(!queue.isEmpty()){
            u = queue.removeFirst();
            w = getFirstNear(u);
            while (w!=-1){
                //如果没有遍历则直接遍历，并将遍历的节点添加到队列中
                if(!isVisited[w]) {
                    System.out.println(getValueByIndex(w) + "->");
                    queue.addLast(w);
                    isVisited[w] = true;
                }
                //如果已经遍历则遍历下一个节点
                w = getNextNear(u, w);
            }


        }
    }
    public void bfs(){
        for (int i = 0; i < getNumVertex(); i++) {
            if(!isVisited[i]){
                bfs(i);
            }
        }

    }
    public void dfs(){
        for (int i = 0; i < getNumVertex(); i++) {
            if(!isVisited[i]){
                dfs(i);
            }
        }
    }
    /**
     * 获取第一个邻近节点
     * @param index
     * @return
     */
    public int getFirstNear(int index){
        for (int i = 0; i < getNumVertex(); i++) {
           if(edges[index][i]>0){
               return i;
           }
        }
        return -1;
    }


    /**
     * 获取下一个邻近节点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNear(int v1,int v2){
        for (int i = v2+1; i < getNumVertex(); i++) {
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }
}