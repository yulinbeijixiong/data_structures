package com.oujian.data.structures.tree.binary.sort;


/**
 * @author annyu
 * @description 二叉排序树
 * @date 2020/4/27
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
//        Node node = new Node(1);
//        Node node1 = new Node(-9);
//        Node node2 = new Node(7);
//        Node node3 = new Node(3);
//        Node node4 = new Node(10);
//        Node node5 = new Node(-10);
//        Node node6 = new Node(2);
//        BinarySortTree binarySortTree = new BinarySortTree();
//        binarySortTree.setRoot(node);
//        binarySortTree.add(node1);
//        binarySortTree.add(node2);
//        binarySortTree.add(node3);
//        binarySortTree.add(node4);
//        binarySortTree.add(node5);
//        binarySortTree.add(node6);
//        binarySortTree.del(7);
//        binarySortTree.del(1);
//        binarySortTree.preOrder();
        AVLTree avlTree = new AVLTree();
        for (int i = 3; i < 20; i++) {
            avlTree.add(new Node(i));
        }
        avlTree.add(new Node(2));
        System.out.printf("左子树高度%d   右子树高度%d", avlTree.getRoot().leftHeight(), avlTree.getRoot().rightHeight());
    }
}

class AVLTree extends BinarySortTree {

}

class BinarySortTree {
    private Node root;


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        if (root != null) {
            root.add(node);
        } else {
            root = node;
        }
    }

    public void preOrder() {
        if (root == null) {
            System.out.println("this is empty tree");
        } else {
            root.midOrder();
        }

    }

    /**
     * 删除
     *
     * @param targetValue
     */
    public void del(int targetValue) {
        if (root.getValue() == targetValue) {
            root = null;
            return;
        }
        Node targetNode = root.getTargetNode(targetValue);
        Node targetParent = root.getTargetParent(targetValue);
        if (targetNode == null) {
            System.out.println("没有该节点");
        }
        //目标节点是一个子节点
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            //先判断是左节点还是右节点
            if (targetParent.getLeft() == targetNode) {
                targetParent.setLeft(null);
                return;
            } else {
                targetParent.setRight(null);
                return;
            }
        }
        //目标节点有一个子节点
        //先判断是左字节点还是右子节点
        //目标节点下面是个右节点
        if (targetNode.getLeft() == null) {
            if (targetParent.getLeft() == targetNode) {
                targetParent.setLeft(targetNode.getRight());
                return;
            } else {
                targetParent.setRight(targetNode.getRight());
                return;
            }
        }
        if (targetNode.getRight() == null) {
            if (targetParent.getLeft() == targetNode) {
                targetParent.setLeft(targetNode.getLeft());
                return;
            } else {
                targetParent.setRight(targetNode.getLeft());
                return;
            }
        }
        //如果目标节点有两节点
        //将目标节点的右节点的左节点指向目标节点的左节点
        targetNode.getRight().setLeft(targetNode.getLeft());
        //目标节点下面有两个节点
        if (targetParent.getLeft() == targetNode) {
            //如果目标节点是父类节点的左节点
            //将父类节点的左节点指向目标节点的右节点
            targetParent.setLeft(targetNode.getRight());
        } else {
            //如果目标节点是父类节点的左节点
            //将父类节点的左节点指向目标节点的右节点
            targetParent.setRight(targetNode.getRight());
        }

    }
}

class Node {
    private int value;
    private Node left;
    private Node right;

    public void setValue(int value) {
        this.value = value;
    }

    public void leftRotate() {
        //创一个新节点，将当前节点的值赋给新节点
        Node node = new Node(value);
        //将新节点的左节点指向当前节点的左节点
        node.left = left;
        //将新节点的右节点指向当前节点的右节点的左节点
        node.right = right.getLeft();
        //将右节点的值赋给当前节点，原来的当前节点已经给了新节点
        value = right.value;
        //当前节点已经变成了右节点，然后将当前节点的右节点指向原来右节点有右节点
        right = right.right;
        //将当前节点的左节点指向新创建的节点
        left = node;
    }

    public void rightRote() {
        Node node = new Node(value);
        node.right = right;
        node.left = left.getRight();
        value = left.value;
        left = left.left;
        right = node;

    }

    /**
     * 获取目标节点
     *
     * @param targetValue
     * @return
     */
    public Node getTargetNode(int targetValue) {
        Node temp = this;
        if (value == targetValue) {
            return this;
        } else if (value > targetValue) {
            while (temp != null && temp.value != targetValue) {
                if (temp.value > targetValue) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            return temp;
        } else {

            while (temp != null && temp.value != targetValue) {
                if (temp.value > targetValue) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
        }
        return temp;
    }

    /**
     * 获取目标节点的父节点
     *
     * @param targetValue
     * @return
     */
    public Node getTargetParent(int targetValue) {
        if (this.left.value == targetValue || this.right.value == targetValue) {
            return this;
        }
        Node temp = this;
        while (true) {
            if (temp.right == null || temp.left == null) {
                break;
            }
            if (temp.right.value == targetValue || temp.left.value == targetValue) {
                break;
            }
            if (this.left.value > targetValue) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return temp;
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (node != null) {
            //当前值大于要添加的值，则将添加值放在树的左边
            if (this.value >= node.value) {
                if (this.left != null) {
                    this.left.add(node);
                } else {
                    this.left = node;
                }
            } else {
                if (this.right != null) {
                    this.right.add(node);
                } else {
                    this.right = node;
                }
            }
            //右子树的高度大于左子树的高度发生左旋
            if (rightHeight() - leftHeight() > 1) {
                //右子树的右子树大于右子树的左子树，先发生右子树右旋，再左旋
                if (left != null && right.rightHeight() > right.leftHeight()) {
                    if (left.right != null) {
                        right.rightRote();
                    }
                    leftRotate();

                } else {

                    leftRotate();

                }
                return;
            }
            if (leftHeight() - rightHeight() > 1) {
                //左子树的左子树大于左子树的右子树，先发生左子树的左旋，然后在右旋
                if (right != null && left.leftHeight() > left.rightHeight()) {
                    //需要加一个非空判断，否则空指针异常
                    if (right.left != null) {
                        left.leftRotate();
                    }
                    rightHeight();

                } else {

                    rightHeight();

                }
            }
        }
    }

    /**
     * 获取左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        return left == null ? 0 : left.height();
    }

    /**
     * 获取右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        return right == null ? 0 : right.height();
    }

    /**
     * 以当前节点为根节点，树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public void midOrder() {
        Node temp = this;
        if (temp.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (temp.right != null) {
            this.right.midOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
