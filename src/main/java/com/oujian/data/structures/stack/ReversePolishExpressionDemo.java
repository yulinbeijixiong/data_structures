package com.oujian.data.structures.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author annyu
 * @description 逆波兰表达式
 * @date 2020/4/22
 **/
public class ReversePolishExpressionDemo {
    public static void main(String[] args) {
        String input ="30 4 + 5 * 6 -";
        int cal = cal(Arrays.asList(input.split(" ")));
        System.out.println(cal);
    }
    public static int cal(List<String> list){
        Stack<String> stack = new Stack<String>();
        int result=0;
        for (String s:list) {
            if(s.matches("\\d+")){
                stack.push(s);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                if("+".equals(s)){
                   result= num1+num2;

                }else if("-".equals(s)){
                    result=num2-num1;
                }else if("*".equals(s)){
                    result=num1*num2;
                }else if("/".equals(s)){
                    result=num2/num1;
                }
                stack.push(Integer.valueOf(result).toString());
            }

        }
        return  Integer.parseInt(stack.pop());
    }
}
