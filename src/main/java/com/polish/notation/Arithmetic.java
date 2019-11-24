package com.polish.notation;

import java.util.Stack;

public class Arithmetic {

    public static void main(String[] args) {

        String exp = "1-1*1-3";

        String[] splitedExpression = splitExp(exp.toLowerCase());

        Stack<String> bolanExpress = parseBolan(splitedExpression);

        int result = calc(bolanExpress);

        System.out.println(result);
    }


    private static String[] splitExp(String expression) {
        for (Operator operator : Operator.values()) {
            expression = expression.replace(operator.getOp(), " " + operator.getOp() + " ");
        }
        expression = expression.trim().replaceAll("  ", " ");

        return expression.split(" ");
    }

    private static int calc(Stack<String> bolanExpress) {
        Stack<Integer> operand = new Stack<Integer>();

        for (int index = 0; index < bolanExpress.size(); index++) {
            String item = bolanExpress.get(index);
            if (isDigit(item)) {
                operand.push(Integer.parseInt(item));
            } else {
                Operator op = Operator.fromVal(item);
                int right = operand.pop();
                int left = operand.pop();
                int result = op.calc(left, right);
                operand.push(result);
            }
        }

        return operand.pop();
    }

    private static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static Stack<String> parseBolan(String[] exp) {
        Stack<String> bolanExp = new Stack<String>();
        Stack<String> operand = new Stack<String>();

        for (int index = 0; index < exp.length; index++) {
            String item = exp[index];
            if (",".equals(item)) {
                continue;
            }
            if (isDigit(item)) {
                bolanExp.push(item);
                continue;
            }

            if (")".equals(item)) {
                while (!operand.isEmpty()) {
                    if ("(".equals(operand.peek())) {
                        operand.pop();
                        break;
                    }
                    bolanExp.push(operand.pop());
                }
                continue;
            }

            if (operand.isEmpty() || "(".equals(operand.peek()) || "(".equals(item)) {
                operand.push(item);
                continue;
            }

            if (Operator.fromVal(item).getLevel() > Operator.fromVal(operand.peek()).getLevel()) {
                operand.push(item);
                continue;
            } else {
                bolanExp.push(operand.pop());
                index--;
            }
        }

        while (!operand.isEmpty()) {
            bolanExp.push(operand.pop());
        }
        return bolanExp;
    }
}
