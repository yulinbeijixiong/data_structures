package com.oujian.data.structures.linklist;


import java.util.Stack;

/**
 * @author annyu
 * @description 单链表结构
 * @date 2020/4/21
 **/
public class SingleLinkListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkList singleLinkList = new SingleLinkList();
        singleLinkList.add(heroNode1);
        singleLinkList.add(heroNode2);
        singleLinkList.add(heroNode3);
        singleLinkList.add(heroNode4);
//        singleLinkList.orderAdd(heroNode1);
//        singleLinkList.orderAdd(heroNode4);
//        singleLinkList.orderAdd(heroNode3);
//        singleLinkList.orderAdd(heroNode2);
//        singleLinkList.delete(heroNode5);
//        singleLinkList.list();
//        System.out.println(singleLinkList.getLength(singleLinkList.getHead()));
//        System.out.println(singleLinkList.getReciprocalElement(3));
//        singleLinkList.reversion1();
//        singleLinkList.list();
        singleLinkList.reversionPrint(singleLinkList.getHead());
    }
}

class SingleLinkList {
    /**
     * 初始化头节点
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void reversionPrint(HeroNode heroNode) {
        if (heroNode.next == null) {
            System.out.println("链表中没有数据");
        }
        Stack<HeroNode> heroNodes = new Stack<HeroNode>();
        HeroNode temp = heroNode.next;
        //先将节点压入栈中，利用栈的前进后出的特点实现逆序打印
        while (temp != null) {
            heroNodes.add(temp);
            temp = temp.next;
        }
        //从栈中弹出数据
        while (!heroNodes.empty()) {
            System.out.println(heroNodes.pop());
        }
    }

    /**
     * 单链表反转
     *
     * @return
     */
    public void reversion() {
        if (head.next == null || head.next.next == null) {
            System.out.println("没有元素或者只有一个元素");
        }
        HeroNode temp = head;
        HeroNode[] tempData = new HeroNode[getLength(head)];

        //将链表中的数据进行遍历，存到集合中
        for (int i = 0; i < tempData.length; i++) {
            temp = temp.next;
            HeroNode heroNode = new HeroNode(temp.no, temp.name, temp.nickname);
            tempData[i] = heroNode;
        }
        for (int i = tempData.length - 1; i > 0; i--) {
            tempData[i].next = tempData[i - 1];

        }
        head.next = tempData[tempData.length - 1];
    }

    public void reversion1() {
        if (head.next == null || head.next.next == null) {
            System.out.println("没有元素或者只有一个元素");
        }
        HeroNode temp = head.next;
        HeroNode reversion = new HeroNode(0, "", "");
        HeroNode next = null;
        while (temp != null) {
            //保存原来链表的下一个节点
            next = temp.next;
            //将反转链的节点下一个接点给原来链表的当前节点的下一个节点，就相当于在头结点后面不断的插入
            temp.next = reversion.next;
            //将当前节点，给反转链的下一个节点
            reversion.next = temp;
            //当前下一个节点
            temp = next;
        }
        //将原来节点的头指向反转链表的头节点的第一个节点
        head.next = reversion.next;
    }


    /**
     * 返回倒数第几个节点
     *
     * @param reciprocalIndex
     * @return
     */
    public HeroNode getReciprocalElement(int reciprocalIndex) {
        int length = getLength(head);
        if (head.next == null) {
            System.out.println("没有节点");
            return null;
        }
        if (reciprocalIndex <= 0 || reciprocalIndex > length) {
            System.out.println("输入的数字有误");
        }

        //计算出倒数第几个节点为顺序的第几个
        int index = length - reciprocalIndex + 1;
        HeroNode temp = head;

        while (index > 0) {
            index--;
            temp = temp.next;
        }
        return temp;

    }

    /**
     * 获取有效节点
     *
     * @param head
     * @return
     */
    public int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head;
        int length = 0;
        while (temp.next != null) {
            length++;
            temp = temp.next;

        }
        return length;
    }

    /**
     * 添加元素
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {

        HeroNode temp = head;
        while (true) {

            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除元素
     *
     * @param heroNode
     */
    public void delete(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("队列为空");
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //链表遍历完了
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有该元素");
        }

    }

    public boolean isExist(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //链表遍历完了
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        return flag;
    }

    /**
     * 更新元素
     *
     * @param heroNode
     */
    public void update(HeroNode heroNode) {
        //链表为空
        if (head.next == null) {
            System.out.println("LinkList is empty");
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //链表遍历完了
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //如果已经存在就添加
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("改元素不存在");
        }
    }

    public void orderAdd(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //当下一节点为空时直接添加
            if (temp.next == null) {
                break;
            }
            //当下个节点大于当前节点时，证明已经找到插入的位置
            if (temp.next.no > heroNode.no) {
                break;
                //下一个节点与插入的节点序号相等证明，插入的节点已经存在
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //执行插入操作
        if (!flag) {
            heroNode.next = temp.next;
            temp.next = heroNode;
        } else {
            throw new RuntimeException("The linked list has this element");
        }
    }

    /**
     * 展示元素
     */
    public void list() {
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;

        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    /**
     * 下一个节点
     */
    HeroNode next;

    HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
