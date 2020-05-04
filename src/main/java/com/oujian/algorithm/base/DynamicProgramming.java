package com.oujian.algorithm.base;

import java.util.Arrays;
import java.util.List;

/**
 * @author annyu
 * @description 动态规划问题
 * @date 2020/4/28
 **/
public class DynamicProgramming {
    public static void main(String[] args) {
        List<Goods> goods = Arrays.asList(new Goods(1, 1500), new Goods(4, 3000), new Goods(3, 2000));
        int algorithm = algorithm(goods, 4);
        System.out.println(algorithm);
    }
    public static int algorithm(List<Goods> list,int maxWeight){
        int[][] a=new int[list.size()+1][maxWeight+1];
        int[][] path=new int[list.size()+1][maxWeight+1];
        int max=0;
        //初始化第一列
        for (int i = 0; i <= list.size(); i++) {
            a[i][0]=0;
        }
        //初始化第一行
        for (int i = 0; i < maxWeight + 1; i++) {
            a[0][i] = 0;
        }
        for (int i = 1; i <a.length ; i++) {
            for (int j = 1; j <a[i].length; j++) {
                //加入的商品重量大于背包最大容量，则价格去前一个价格
                if(j<list.get(i-1).getWeight()){
                    a[i][j]=a[i-1][j];
                }else {
                    //如果加入的商品小于背包的最大重量，价格=加入商品的重量+ [i-1][背包的最大重量-加入商品的重量]
                    a[i][j]=Math.max(a[i-1][j],list.get(i-1).getPrice()+a[i-1][j-list.get(i-1).getWeight()]);
                    max=a[i][j];
                    path[i][j]=1;
                }
            }
        }
        int i = path.length - 1;
        int j= path[0].length- 1;
        //重最后开始遍历
        while (i>0&&j>0){
            //先找到放入最佳值的位置
            if(path[i][j]==1){
                //如果找到了就当前商品打印
                System.out.println("放入第"+i+"商品");
                //减去相应的重量
                j -=list.get(i-1).getWeight();
            }
            i--;
        }
        return max;
    }
}

class Goods{
   private int weight;
   private int price;
   private int no;

    public Goods(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}