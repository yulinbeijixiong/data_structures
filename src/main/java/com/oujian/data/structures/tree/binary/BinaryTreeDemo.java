package com.oujian.data.structures.tree.binary;


/**
 * @author annyu
 * @description 二叉树demo
 * @date 2020/4/26
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode a = new TreeNode(1, "a");
        TreeNode b = new TreeNode(2, "b");
        TreeNode c = new TreeNode(3, "c");
        TreeNode d = new TreeNode(4, "d");
        TreeNode e = new TreeNode(5, "e");
        binaryTree.setRoot(a);
        a.setLeft(b);
        a.setRight(c);
        c.setLeft(d);
        c.setRight(e);
        binaryTree.preOrder();
        binaryTree.midOrder();
        binaryTree.afterOrder();
        System.out.println(binaryTree.preSearch(5));
        System.out.println(binaryTree.midSearch(5));
        System.out.println(binaryTree.afterSearch(5));
        System.out.println(binaryTree.del(1));
        binaryTree.preOrder();


    }
}
class BinaryTree{
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("没有根节点");
        }
    }
    public void midOrder(){
        if(this.root!=null){
            this.root.midOrder();
        }else{
            System.out.println("没有根节点");
        }
    }
    public void afterOrder(){
        if(this.root!=null){
            this.root.afterOrder();
        }else{
            System.out.println("没有根节点");
        }
    }
    public TreeNode preSearch(int no){
        if(root!=null){
            if(root.getNo()==no){
                return root;
            }
           return this.root.preSearch(no);
        }
        return null;
    }
    public TreeNode midSearch(int no){
        if(root!=null){
            if(root.getNo()==no){
                return root;
            }
            return this.root.midSearch(no);
        }
        return null;
    }
    public TreeNode afterSearch(int no){
        if(root!=null){
            if(root.getNo()==no){
                return root;
            }
            return this.root.afterSearch(no);
        }
        return null;
    }
    public boolean del(int no){
        if(this.root!=null){
            if(this.root.getNo()==no){
                this.root=null;
                return true;
            }
          return   this.root.del(no);
        }
        return false;
    }
}
class TreeNode{
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;
    /**
     * 0代表左子树
     * 1，前序节点
     */
    private int leftType;
    /**
     * 0代表右子树
     * 1，后继节点
     */
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        //输出当前节点
        System.out.println(this);
        //向左递归输出左子节点
        if(this.left!=null){
            this.left.preOrder();
        }
        //向右递归输出右子节点
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder(){
        //先输出左子节点
        if(this.left!=null){
            this.left.midOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.midOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void afterOrder(){
        if(this.left!=null){
            this.left.afterOrder();
        }

        if(this.right!=null){
            this.right.afterOrder();
        }
        System.out.println(this);

    }
    public TreeNode preSearch(int no){
        System.out.println("aaaaa");
        if(this.no==no){
            return this;
        }
        //向左递归
        TreeNode result=null;
        if(this.left!=null){
           result= this.left.preSearch(no);
        }
        //结果不为空则直接返回否则向右递归
        if(result!=null){
            return result;
        }
        if(this.right!=null){
            result=this.right.preSearch(no);
        }
        return result;
    }

    /**
     * 中序查找
     * @param no
     * @return
     */
    public TreeNode midSearch(int no){

        //向左递归
        TreeNode result=null;
        if(this.left!=null){
            result= this.left.midSearch(no);
        }
        //结果不为空则直接返回否则看当前节点是否相等

        if(result!=null){
            return result;
        }
        System.out.println("bbbb");
        if(this.no==no){
            return this;
        }
        if(this.right!=null){
            result=this.right.midSearch(no);
        }
        return result;
    }
    public TreeNode afterSearch(int no){

        //向左递归
        TreeNode result=null;
        if(this.left!=null){
            result= this.left.afterSearch(no);
        }

        //结果不为空则直接返回否则向右递归
        if(result!=null){
            return result;
        }
        if(this.right!=null){
            result=this.right.afterSearch(no);
        }
        if(result!=null){
            return result;
        }
        System.out.println("cccc");
        if(this.no==no){
            return this;
        }

        return result;
    }
    public boolean del(int no){
        if(this.left!=null){
            if(this.left.no==no){
                this.left=null;
                return true;
            }
        }
        if(this.right!=null){
            if(this.right.no==no){
                this.right=null;
                return true;
            }
        }
        //向左递归
        boolean flag=false;
        if(this.left!=null){
            flag = this.left.del(no);
        }
        if (flag){
            return true;
        }
        //向右递归
        if(this.right!=null){
            flag=this.right.del(no);
        }
        if(flag){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
