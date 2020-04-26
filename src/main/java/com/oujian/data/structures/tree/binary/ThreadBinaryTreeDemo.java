package com.oujian.data.structures.tree.binary;

/**
 * @author annyu
 * @description 线索化二叉树
 * @date 2020/4/26
 **/
public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        TreeNode a = new TreeNode(1, "a");
        TreeNode b = new TreeNode(2, "b");
        TreeNode c = new TreeNode(3, "c");
        TreeNode d = new TreeNode(4, "d");
        TreeNode e = new TreeNode(5, "e");
        TreeNode f = new TreeNode(6, "f");
        threadBinaryTree.setRoot(a);
        a.setLeft(b);
        b.setLeft(d);
        b.setRight(e);
        a.setRight(c);
        c.setLeft(f);
        threadBinaryTree.midOrderClue();
//        System.out.println(e.getLeft());
//        System.out.println(e.getRight());
        threadBinaryTree.midOrderTraverse();


    }

}

class ThreadBinaryTree {
    private TreeNode root;
    private TreeNode pre;
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    public void midOrderClue(){
        midOrderClue(root);
    }

    /**
     * 中序线索化二叉树
     * @param treeNode
     */
    public void midOrderClue(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        //向左递归
        midOrderClue(treeNode.getLeft());
        //设置前驱点
        if(treeNode.getLeft()==null){
            treeNode.setLeft(pre);
            treeNode.setLeftType(1);
        }
        //设置后后继点
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(treeNode);
            pre.setRightType(1);
        }
        pre=treeNode;
        midOrderClue(treeNode.getRight());


    }
    public void midOrderTraverse(){
        TreeNode node=root;
        while (node!=null){
            //从左子树找到等于0的节点
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            System.out.println(node);
            //
            while(node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);

            }
            node=node.getRight();

        }
    }
}

