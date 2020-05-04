package com.oujian.algorithm.base;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author annyu
 * @description 马踏棋盘算法
 * @date 2020/5/4
 **/
public class HorseChessBoard {
    /**
     * 棋盘的行
     */
    private static int X;
    /**
     * 棋盘的列
     */
    private static int Y;
    private static boolean[] visited;
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        visited = new boolean[X * Y];
        int[][] ints = new int[X][Y];
        traverChessboard(ints, row - 1, column - 1, 1);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }

    }

    public static void traverChessboard(int[][] chessboard, int row, int column, int step) {
        //设置该位置是第几步访问的
        chessboard[row][column] = step;
        //标记该位置已经访问
        visited[row * X + column] = true;
        ArrayList<Point> ps = next(new Point(row, column));
        sort(ps);
        while (!ps.isEmpty()) {
            //取出第一个点
            Point point = ps.remove(0);
            //判断该点是否访问
            if (!visited[point.y * X + point.x]) {
                traverChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        //如果没有完成
        if (!finished && X * Y > step) {
            //将棋盘该位置置为0
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }

    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        return points;
    }
    public static void sort(ArrayList<Point> points){
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
