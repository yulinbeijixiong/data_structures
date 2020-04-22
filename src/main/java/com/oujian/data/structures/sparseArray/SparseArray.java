package com.oujian.data.structures.sparseArray;

/**
 * @author annyu
 * @description 稀疏数组与二维数组相互转化
 * @date 2020/4/20
 **/
public class SparseArray {
    private final static int ARRAY_EFFECTIVE_ELEMENT_COUNT=2;
    public static void main(String[] args) {
        int[][] data = new int[10][10];
        data[1][3]=12;
        data[2][7]=45;
        int[][] ints = transformSpareArray(data);

        for (int i = 0; i < ints.length; i++) {
            System.out.printf("%d\t%d\t%d\n",ints[i][0],ints[i][1],ints[i][2]);
        }
        int[][] ints1 = transformArray(ints);
        for (int i = 0; i < ints1.length; i++) {
            for (int j = 0; j < ints1[i].length; j++) {
                System.out.printf("%d\t",ints1[i][j]);
            }
            System.out.println();
        }

    }

    /**
     * 将二维数据转为稀疏数组
     * @param data 输入的二维数组
     * @return 稀疏数组
     */
    private static int[][] transformSpareArray(int[][] data){
        int count=0;
        //遍历获取有效值个数
        for(int[] a:data){
            for(int b:a){
                if(b!=0){
                    count++;

                };
            }
            System.out.println();
        }
        //创建稀疏数组
        int[][] sparseArray=new int[count+1][3];

        //设置第一个数据，第一个为行数，第二个为列数，第三为有效元素个数
        sparseArray[0][0]=data.length;
        sparseArray[0][1]=data[0].length;
        sparseArray[0][2]=count;
        int sparseArrayIndex=1;
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                if(data[i][j]!=0){
                    sparseArray[sparseArrayIndex][0]=i;
                    sparseArray[sparseArrayIndex][1]=j;
                    sparseArray[sparseArrayIndex][2]=data[i][j];
                    sparseArrayIndex++;
                }
            }
        }
       return sparseArray;

    }
    private static int[][] transformArray(int[][] data){
        int[] arrayInfo = data[0];
        int[][] array = new int[arrayInfo[0]][arrayInfo[1]];
        for (int i = 1; i <= arrayInfo[ARRAY_EFFECTIVE_ELEMENT_COUNT]; i++) {
            int[] datum = data[i];
            array[datum[0]][datum[1]]=datum[2];
        }
        return array;
    }

}
