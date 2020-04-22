package com.oujian.data.structures.stack;

/**
 * @author annyu
 * @description 双向链表
 * @date 2020/4/21
 **/
public class DoubleLinklistDemo {
    public static void main(String[] args) {
        DoubleHeroNode doubleHeroNode1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode doubleHeroNode2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode doubleHeroNode3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode doubleHeroNode4 = new DoubleHeroNode(4, "林冲", "豹子头");
        DoubleHeroNode doubleHeroNode5 = new DoubleHeroNode(4, "林冲", "豹头");
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.add(doubleHeroNode1);
        doubleLinkList.add(doubleHeroNode2);
        doubleLinkList.add(doubleHeroNode3);
        doubleLinkList.add(doubleHeroNode4);
        doubleLinkList.delete(2);
        doubleLinkList.update(doubleHeroNode5);
        doubleLinkList.list();

    }
}
class DoubleLinkList{

    /**
     * 初始化头节点
     */
    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getHead() {
        return head;
    }
    /**
     * 更新元素
     *
     */
    public void update(DoubleHeroNode doubleHeroNode) {
        //链表为空
        if (head.next == null) {
            System.out.println("LinkList is empty");
        }
        DoubleHeroNode temp = head;
        boolean flag = false;
        while (true) {
            //链表遍历完了
            if (temp == null) {
                break;
            }
            if (temp.no == doubleHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //如果已经存在就添加
        if (flag) {
            temp.name = doubleHeroNode.name;
            temp.nickname = doubleHeroNode.nickname;
        } else {
            System.out.println("改元素不存在");
        }
    }

    /**
     * 添加元素
     * @param doubleHeroNode
     */
    public void add(DoubleHeroNode doubleHeroNode){
        DoubleHeroNode temp=head;
        while (true){

            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        doubleHeroNode.pre=temp;
        temp.next=doubleHeroNode;
    }

    /**
     * 删除元素
     * @param index
     */
    public void delete(int index){
        if (head.next == null) {
            System.out.println("队列为空");
        }
        DoubleHeroNode temp = head;
        boolean flag = false;
        while (true) {
            //链表遍历完了
            if (temp== null) {
                break;
            }
            if (temp.no ==index) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next=temp.next;
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        } else {
            System.out.println("没有该元素");
        }
    }
   public void list(){

        if(head.next==null){
            System.out.println("链表元素为空");
        }
        DoubleHeroNode temp=head.next;
        while(temp!=null){
            System.out.println(temp);
            temp =temp.next;
        }
   }
}
class DoubleHeroNode{
    public int no;
    public String name;
    public String nickname;
    /**
     * 下一个节点
     */
    public DoubleHeroNode next;
    public DoubleHeroNode pre;

    public DoubleHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}