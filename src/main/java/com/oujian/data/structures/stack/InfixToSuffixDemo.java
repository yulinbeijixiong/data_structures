package com.oujian.data.structures.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author annyu
 * @description
 * @date 2020/4/22
 **/
public class InfixToSuffixDemo {
    public static void main(String[] args) {
        String input = "1 + ( ( 2 + 3 ) * 4 ) - 5";
        String input1 ="1+((2+3)*4)-5";

        String s = StringSplit(input1);
        String transform = transform(s);
        int cal = ReversePolishExpressionDemo.cal(Arrays.asList(transform.split(" ")));

        System.out.println(cal);
    }
    public static String StringSplit(String input){
        int index=0;
        boolean flag=false;
        Stack<String> stack = new Stack<String>();
        while(index <input.length()){
            String sub = input.substring(index, index + 1);
            if(sub.matches("\\d+")){
                if(!stack.isEmpty()){
                    if(!flag){
                        String pop = stack.pop();
                        String value =Integer.valueOf(Integer.parseInt(pop)*10+Integer.parseInt(sub)).toString() ;
                        stack.push(value);
                    }
                    stack.push(sub);
                }else {
                    stack.push(sub);
                }
            }else {
                flag=true;
                stack.push(sub);
            }
            index ++;
        }
        Stack<String> integers = new Stack<String>();
        while (!stack.isEmpty()) {
            integers.push(stack.pop());
        }
        String result = "";
        while (!integers.isEmpty()){
            result +=integers.pop()+" ";
        }
        return result;




    }

    public static String transform(String input) {
        List<String> data = Arrays.asList(input.split(" "));
        //用于存储操作符
        Stack<String> s1 = new Stack<String>();
        //用于存储后缀表达式
        Stack<String> s2 = new Stack<String>();
        for (String s : data) {
            if (s1.empty() && !s.matches("\\d+")) {
                s1.push(s);
                continue;
            }
            if (s.matches("\\d+")) {
                s2.push(s);
            } else {
                //处理操作符之间的比较
                while (!s1.isEmpty()) {
                    String beforeOp = s1.peek();
                    //进行操作符优先级的比较，如果栈中的操作符比当前操作符的优先级大，则将是s1 的操作符加到s2
                    if (Arrays.asList("*", "/").contains(beforeOp) && Arrays.asList("+", "-").contains(s)) {
                        s2.push(s1.pop());
                        s1.push(s);
                        break;
                    }else if(Arrays.asList("+", "-").contains(beforeOp) && Arrays.asList("+", "-").contains(s)){
                        s2.push(s1.pop());
                        s1.push(s);
                        break;
                    }else if(Arrays.asList("*", "/").contains(beforeOp) && Arrays.asList("*", "/").contains(s)){
                        s2.push(s1.pop());
                        s1.push(s);
                        break;
                    } else {
                        //如果遇见右括号,则将s1操作符加入到s2，直到s1遇见左括号
                        if (")".equals(s)) {
                            while (!s1.isEmpty()) {
                                String peek = s1.peek();
                                //遇见左括号,s1弹出左括号，并跳出
                                if ("(".equals(peek)) {
                                    s1.pop();
                                    break;
                                }
                                s2.push(s1.pop());
                            }
                            break;
                        } else {
                            s1.push(s);
                            break;
                        }
                    }
                }
            }
        }
        //将s1中所有的元素添加到s2中
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        //将结果进行翻转，得到正确的结果
        Stack<String> stack = new Stack<String>();
        while (!s2.isEmpty()) {
            //将s1 最后一个操作符加入到s2中

            stack.push(s2.pop());
        }
        String result = "";
        while (!stack.isEmpty()) {
            result =result+ stack.pop() + " ";
        }
        return result;
    }
}
