package com.oujian.data.structures.stack;

/**
 * @author annyu
 * @description 栈的使用
 * @date 2020/4/22
 **/
public class StackArrayDemo {
    public static void main(String[] args) {
        StackArray opStack = new StackArray(10);
        StackArray numStack = new StackArray(10);
        String input = "10000*2-3*4";
        int index=0;
        char a =' ';
        //记录是否不是连续数字
        boolean flag=false;
        while(true) {
            if(index>input.length()-1){
                break;
            }
            a= input.substring(index,index+1).charAt(0);
            index++;
            if (opStack.isOperator(a)) {
                flag=true;
                //符号操作栈,不为空再进行比较
                if (!opStack.isEmpty()) {
                    int stackOp = opStack.peek();
                    int priority = opStack.priority(stackOp);
                    int priority1 = opStack.priority(a);
                    //将入栈的操作符的优先级小于等于原来了栈顶操作符的优先级
                    if (priority1 <= priority) {
                        //取出两个数进行计算
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        //进行计算
                        int result = numStack.cal(num1, num2, stackOp);
                        //将计算结果添加到数据栈中
                        numStack.push(result);
                        //弹出以前的操作符
                        opStack.pop();
                    }
                    //将新的符号位添加到操作符栈中
                    opStack.push(a);

                } else {
                    //操作符加入到操作符栈中
                    opStack.push(a);
                }
            } else {
                if(flag||numStack.isEmpty()){
                    numStack.push(a-48);
                }else{
                    int num = numStack.pop()*10+(a-48);
                    numStack.push(num);
                }

            }

        }
        int result = 0;
        //进行出栈操作，计算已加入的数据的结果
        while (true) {
            if (opStack.isEmpty()) {
                break;
            }
            //取出两个数
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            //取出一个操作符
            int op = opStack.pop();
            result = numStack.cal(num1, num2, op);
            //如果操作符的栈为空时，则计算完毕

            numStack.push(result);
        }
        System.out.println(numStack.pop());

    }

}

class StackArray {
    private int maxSize;
    private int top = -1;
    private int[] data;

    public StackArray(int maxSize) {
        this.maxSize = maxSize;
        data = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int element) {
        if (isFull()) {
            System.out.println("stack is full");
            return;
        }
        top++;
        data[top] = element;

    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        int result = data[top];
        top--;
        return result;

    }

    public int peek() {
        return data[top];
    }

    /**
     * 是否是操作符
     *
     * @param val
     * @return
     */
    public boolean isOperator(int val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 操作符的优先级
     *
     * @param op
     * @return
     */
    public int priority(int op) {
        if (op == '*' || op == '/') {
            return 1;
        } else if (op == '+' || op == '-') {
            return 0;
        }
        throw new RuntimeException("不支持该运算");
    }

    /**
     * 进行计算
     *
     * @param num1
     * @param num2
     * @param op
     * @return
     */
    public int cal(int num1, int num2, int op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                //后出栈的数据减前面出栈的数据
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
            default:
        }
        return 0;
    }
}