package com.oujian.data.structures.linklist;

/**
 * @author annyu
 * @description 环形链表
 * @date 2020/4/21
 **/
public class RingLinkListDemo {
    public static void main(String[] args) {
        RingLinkList ringLinkList = new RingLinkList();
        ringLinkList.add(5);
        ringLinkList.traverse();
        ringLinkList.joseph(1,2,5);
    }
}
class RingLinkList{
    private Boy first = null;

    /**
     * 添加环形链表
     * @param num
     */
    public void add(int num){
        if(num<1){
            System.out.println("数据错误");
        }
        Boy curBoy=null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            //第一个元素特殊处理
            if(i==1){
                first=boy;
                first.setNext(first);
                curBoy =boy;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;

            }
        }
    }

    /**
     * 约瑟夫问题
     * @param startNo
     * @param num
     * @param total
     */
    public void joseph(int startNo,int num,int total){
        if(first==null||startNo<0||startNo>total){
            System.out.println("输入参数错误");
            return;
        }
        //找到first 的上一个节点,用作help
        Boy helper=first;
        while (helper.getNext()!=first){
            helper=helper.getNext();
        }
        //确定起始位置
        for (int i = 0; i < startNo-1; i++) {
            helper=helper.getNext();
            first=first.getNext();
        }
        while (first!=helper){
            //报数次数
            for (int i = 0; i < num - 1; i++) {
                helper=helper.getNext();
                first=first.getNext();
            }
            System.out.printf("出圈小孩的no:%d\n",first.getNo());
            //移动指针
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("￿最后小孩出圈的no:%d\n",first.getNo());
    }


    /**
     * 遍历环形链表
     */
    public void traverse(){
        if(first==null){
            System.out.println("链表中没有数据");
        }
        Boy curBoy=first;
        while (true) {
            System.out.println(curBoy);
            if(curBoy.getNext()==first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


}
class Boy{
    private int no;
    private Boy next;

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no ;
    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}