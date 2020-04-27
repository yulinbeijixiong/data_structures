package com.oujian.data.structures.tree.huffman;


import java.util.*;

/**
 * @author annyu
 * @description 霍夫曼编码
 * @date 2020/4/26
 **/
public class HuffmanTreeCode {
    private static StringBuilder code = new StringBuilder();
    private static Map<Byte,String> huffmanCode=new HashMap<Byte, String>(50);
    public static void main(String[] args) {
        String input="i like like like java do you like a java";
        byte[] bytes = huffmanCodeZip(input);
        Byte[] decode = decode(bytes);
        byte[] bytes1 = new byte[decode.length];
        for (int i = 0; i < decode.length; i++) {
            bytes1[i]=decode[i];

        }
        System.out.println(new String(bytes1));


    }
    public static Byte[] decode(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转为二进制字符串
        for (int i = 0; i < bytes.length; i++) {
            //最后一位不需要补高位
            boolean flag=i==bytes.length-1;
            stringBuilder.append(byteToString(bytes[i], !flag)) ;
        }
        //反转编码表
        HashMap<String, Byte> stringByteHashMap = new HashMap<String, Byte>();
        for (Map.Entry<Byte,String> entry :huffmanCode.entrySet()
                ) {
            stringByteHashMap.put(entry.getValue(),entry.getKey());
        }
        //将二进制字符串按编码表进行匹配
        String str = stringBuilder.toString();
        List<Byte> list=new ArrayList<Byte>();
        for (int i = 0; i < str.length();) {
            int count=0;
            boolean flag=true;
            while(flag){
                String substring = str.substring(i, i + count);
                Byte aByte = stringByteHashMap.get(substring);

                if(aByte!=null){
                    list.add(aByte);
                    flag=false;
                }else {
                    //如果是没有匹配到则向后移一位
                    count++;
                }
            }
            i+=count;
        }

        Byte[] bytes1 = new Byte[list.size()];
        return list.toArray(bytes1);
    }
    public static String byteToString(Byte b,boolean flag){
        int temp=b;
        if(flag){
            //如果是正数需要在高位补0
            temp|=256;
        }
        //将数字转为bit位的字符串
        String s = Integer.toBinaryString(temp);
        if(flag){
            return s.substring(s.length()-8);
        }else {
            return s;
        }
    }
    public static byte[] huffmanCodeZip(String str){
        List<TreeNode> treeNodes = handCount(str.getBytes());
        TreeNode huffmanTree = createHuffmanTree(treeNodes);
        huffmanTree.preOrder();
        getHuffmanCode(huffmanTree);
        System.out.println(huffmanCode.toString());
        return getHuffmanCodeByte(str.getBytes());
    }
    public static byte[] getHuffmanCodeByte(byte[] strByte){
        StringBuilder stringBuilder = new StringBuilder();
        //将原来的字符串数组，转成霍夫曼编码的数组
        for (int i = 0; i < strByte.length; i++) {
            byte b = strByte[i];
            stringBuilder.append(huffmanCode.get(b));
        }
        String codeHuffmanStr = stringBuilder.toString();
        int len= (stringBuilder.toString().length()+7) / 8;
        byte[] bytes = new byte[len];
        int index=0;
        for (int i = 0; i < codeHuffmanStr.length(); i+=8) {
            String by;
            if(i+8>codeHuffmanStr.length()){
                by=codeHuffmanStr.substring(i);
            }else {
                by= codeHuffmanStr.substring(i,i+8);
            }
            byte b =(byte) Integer.parseInt(by, 2);
            bytes[index++]=b;
        }
        return bytes;
    }
    /**
     * 对霍夫曼树进行编码
     * @param treeNode 树节点
     * @param code 按路径进行编码，左节点为0 ，右节点为1
     * @param tempCode 储存中间编码
     */
    public static void getHuffmanCode(TreeNode treeNode,String code,StringBuilder tempCode){
        StringBuilder stringBuilder = new StringBuilder(tempCode);
        stringBuilder.append(code);
        if(treeNode!=null){
            //非叶子节点
            if(treeNode.getData()==null){
                    getHuffmanCode(treeNode.getLeft(),"0",stringBuilder);
                    getHuffmanCode(treeNode.getRight(),"1",stringBuilder);
            }else{
                huffmanCode.put(treeNode.getData(),stringBuilder.toString());
            }
        }
    }
    public static Map<Byte,String> getHuffmanCode(TreeNode root){
        if(root!=null){
            getHuffmanCode(root.getLeft(),"0",code);
            getHuffmanCode(root.getRight(),"1",code);
        }
        return huffmanCode;
    }

    public static List<TreeNode> handCount(byte[] bytes){
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        Map<Byte, Integer> map = new HashMap<Byte, Integer>(16);
        for (int i = 0; i < bytes.length; i++) {
            if(map.containsKey(bytes[i])){
                map.put(bytes[i],map.get(bytes[i])+1);
            }else{
                map.put(bytes[i],1);
            }

        }

        for (Map.Entry<Byte, Integer> entry :map.entrySet()) {
            TreeNode treeNode = new TreeNode(entry.getValue(), entry.getKey());
            nodes.add(treeNode);
        }
        return nodes;
    }
    public static TreeNode createHuffmanTree(List<TreeNode> list){
        while(list.size()>1){
            Collections.sort(list);
            TreeNode leftNode = list.get(0);
            TreeNode rightNode = list.get(1);
            TreeNode treeNode = new TreeNode(rightNode.getWeight() + leftNode.getWeight(), null);
            treeNode.setLeft(leftNode);
            treeNode.setRight(rightNode);
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(treeNode);
        }
        return list.get(0);
    }

}
class TreeNode implements Comparable<TreeNode>{

   private Byte data;
    private int weight;
   private TreeNode left;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    private TreeNode right;

    public TreeNode(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }



    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.weight-o.weight;
    }
}