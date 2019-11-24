package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Bolan {

    public static void main(String[] args) {

        List<Character> ops = new ArrayList<Character>();
        ops.add('+');
        ops.add('-');
        ops.add('*');
        ops.add('/');
        ops.add('(');
        ops.add(')');

        Stack<String> operands = new Stack<String>();
        Stack<String> bolan = new Stack<String>();

//        Scanner scanner = new Scanner(System.in);

//        String expression = scanner.nextLine();
        String expression = "(0*1)/9+((2+3)*4)-5";
//        String expression = "1-1*2-3";

        System.out.println(expression);
        char[] exp = expression.toCharArray();


        for (int index =0; index< exp.length; index ++) {
            char c = exp[index];
            if (c >= '0' && c <= '9') {
                bolan.push(String.valueOf(c));
                continue;
            }

            if (operands.isEmpty()) {
                operands.push(String.valueOf(c));
                continue;
            }

            if (c == '(') {
                operands.push(String.valueOf(c));
                continue;
            }

            if (c == ')') {
                while (operands.peek().charAt(0) != '(') {
                    bolan.push(operands.pop());
                }
                operands.pop();
                continue;
            }

            if (ops.indexOf(operands.peek().charAt(0)) < ops.indexOf(c) || operands.peek().charAt(0) == '(') {
                operands.push(String.valueOf(c));
            } else {
                bolan.push(operands.pop());
                index --;
            }
        }

        while (!operands.isEmpty()) {
            bolan.push(operands.pop());
        }

        for (int index = 0; index < bolan.size(); index++) {
            System.out.print(bolan.get(index));
        }
        System.out.println("");

        Stack<String> ops1 = new Stack<String>();

        Stack<String> nums = new Stack<String>();

        for (int index = 0; index < bolan.size(); index++) {
            char c = bolan.get(index).charAt(0);
            if (c >= '0' && c <= '9') {
                nums.push(String.valueOf(c));
            } else {
                int right = Integer.parseInt(nums.pop());
                int left = nums.isEmpty()? 0 : Integer.parseInt(nums.pop());

                Operator operator = Operator.fromVal(String.valueOf(c));

                int result = operator.calc(left, right);
                nums.push(String.valueOf(result));
            }
        }

        System.out.println(nums.pop());
    }
}
