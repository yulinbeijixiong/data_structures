package com.oujian.data.structures.stack;

/**
 * @author annyu
 * @description 迷宫路径问题
 * @date 2020/4/22
 **/
public class MazePathDemo {
    public static void main(String[] args) {
        int[][] map =new int[7][8];
        //设置障碍,value为1代表不能走
        for(int i=0;i<map.length;i++){
            map[i][0]=1;
            map[i][7]=1;
        }
        for(int j=1;j<map[0].length-1;j++){
            map[6][j]=1;
            map[0][j]=1;

        }
        map[3][1]=1;
        map[3][2]=1;
        map[2][2]=1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();

        }
        System.out.println("+++++++++++++++++++");
        System.out.println();
        boolean save = createSave(map, 1, 1);
        if(save){
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j]+"\t");
                }
                System.out.println();

            }
        }

    }
    public static boolean createSave(int[][] map,int i,int j){
        if(map[5][6]==2){
            return true;
        }else{
            if (map[i][j]==0) {
                //先将该点置为可以走的
                map[i][j]=2;
                //向下
                if(createSave(map,i+1,j)){
                    return true;
                    //向右
                }else if(createSave(map,i,j+1)){
                    return true;
                    //向上
                }else if(createSave(map,i-1,j)){
                    return true;
                    //向左
                }else if(createSave(map,i,j-1)){
                    return true;
                }else{
                    //如果这个点上下左右都不能走，则置为3
                    map[i][j]=3;
                    return false;
                }
            }else{
                //可以的值是1，2，3
                // 1、是障碍不能走
                // 2、已经可以走了，不需要重复验证
                // 3、死路不能走
                return false;
            }
        }
    }
}
