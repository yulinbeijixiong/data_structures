package com.oujian.data.structures.hashTable;

/**
 * @author annyu
 * @description 哈希表
 * @date 2020/4/25
 **/
public class HashTableDemo {
    public static void main(String[] args) {
        HashTableEmp hashTableEmp = new HashTableEmp(10);
        Emp a = new Emp(1, "a");
        Emp b = new Emp(21, "b");
        hashTableEmp.add(a);
        hashTableEmp.add(b);
        hashTableEmp.list();
        System.out.println(hashTableEmp.get(20));
        hashTableEmp.del(1);
        hashTableEmp.list();
    }
}

class HashTableEmp {
    LinkListEmp[] linkListEmps;
    int maxSize;

    public HashTableEmp(int maxSize) {
        linkListEmps = new LinkListEmp[maxSize];
        //初始化数组
        for (int i = 0; i < maxSize; i++) {
            linkListEmps[i] = new LinkListEmp();
        }
        this.maxSize = maxSize;
    }

    public int hashCode(int no) {
        return no % maxSize;
    }

    public void add(Emp emp) {
        int index = hashCode(emp.no);
        linkListEmps[index].add(emp);
    }

    public void list() {
        for (int i = 0; i < linkListEmps.length; i++) {
            if (linkListEmps[i] != null) {
                System.out.print("链表：" + i + "\t");
                linkListEmps[i].list();
                System.out.println();
            }
        }
    }

    public void del(int no) {
        int i = hashCode(no);
        LinkListEmp linkListEmp = linkListEmps[i];
        linkListEmp.del(no);
    }

    public String get(int no) {
        int i = hashCode(no);
      return   linkListEmps[i].get(no);


    }
}

class LinkListEmp {
    Emp head=new Emp(0,"");

    public void add(Emp emp) {
        if (head == null) {
            head.next = emp;
            return;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list() {
        if (head == null) {
            System.out.println("该链表没有值");
        }
        Emp curEmp = head;
        while (curEmp != null) {
            if(curEmp.no>0) {
                System.out.print("no:" + curEmp.no + "name:" + curEmp.name + "\t");

            }
            curEmp = curEmp.next;
        }
    }

    public void del(int no) {
        if (head == null) {
            System.out.println("没有这个值");
        }
        Emp cur = head;

        while ( cur.next!=null) {

            if(cur.next.no==no){
                break;
            }
            cur = cur.next;

        }
        if(cur.next==null){
            System.out.println("没有这个员工");
            return;
        }
        if (cur.next.next != null) {
            cur.next = cur.next.next;
        } else {
            cur.next = null;
        }
    }
    public String get(int no){
        if(head==null){
            System.out.println("没有这个员工");
        }
        Emp emp=head;
        while (emp!=null&& emp.no!=no){
            emp=emp.next;
        }
        if(emp==null){
            System.out.println("没有这个员工");
            return null;
        }else{
           return emp.name;
        }

    }

}

class Emp {
    int no;
    String name;
    Emp next;

    public Emp(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void add(Emp emp) {

    }
}