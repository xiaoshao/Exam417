package com.polish.notation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArithmeticMaxMin {

    private static String convertMaxMinOperator2InfixExpression(String originalExpression) {
        String[] splitedExpression = originalExpression.split("\\s+");
        Stack<String> brackStack = new Stack<>();
        //用于储存当前括号属于哪个运算符
        Stack<String> transformOperator = new Stack();
        List<String> transformedExpression = new ArrayList();

        for (int index = 0; index < splitedExpression.length; index++) {
            switch (splitedExpression[index]) {
                case ",":
                    transformedExpression.add(transformOperator.peek());
                    break;
                case "max":
                case "min":
                    transformOperator.push(splitedExpression[index]);
                    break;
                case "(":
                    transformedExpression.add("(");
                    brackStack.add("(");
                    break;
                case ")":
                    transformedExpression.add(")");
                    brackStack.pop();
                    if (transformOperator.size() - brackStack.size() == 1) {
                        transformOperator.pop();
                    }
                    break;
                default:
                    transformedExpression.add(splitedExpression[index]);
            }
        }

        return String.join(" ", transformedExpression).trim();
    }


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String expression = scanner.nextLine();

        String expression = "max((2+3)*10,5)";
        String expWithPreAndAfterBlank = insertBlank(expression);

        String infixExpression = convertMaxMinOperator2InfixExpression(expWithPreAndAfterBlank);

        String[] suffixExpression = convert2SuffixExpression(infixExpression.split("\\s+"));

        int result = calculate(suffixExpression);
        System.out.println(result);
    }

    private static int calculate(String[] suffixExpression) {
        Stack<String> tempNumber = new Stack<>();
        for(int index = 0; index < suffixExpression.length; index ++){
            if(isDigit(suffixExpression[index])){
                tempNumber.push(suffixExpression[index]);
                continue;
            }

            Operator operator = Operator.fromVal(suffixExpression[index]);

            int left = Integer.parseInt(tempNumber.pop());
            int right = Integer.parseInt(tempNumber.pop());

            int result = operator.calc(left, right);
            tempNumber.push(String.valueOf(result));
        }

        return Integer.parseInt(tempNumber.pop());
    }

    private static String[] convert2SuffixExpression(String[] split) {
        Stack<String> operand = new Stack<>();
        Stack<String> operator = new Stack<>();
        for (int index = 0; index < split.length; index++) {
            String item = split[index];

            if (isDigit(item)) {
                operand.push(item);
                continue;
            }

            if (operator.isEmpty() || "(".equals(operator.peek()) || "(".equals(item)) {
                operator.push(item);
                continue;
            }

            if (")".equals(item)) {
                while (!operator.isEmpty() && !"(".equals(operator.peek())) {
                    operand.push(operator.pop());
                }
                operator.pop();
                continue;
            }

            if (Operator.fromVal(item).getLevel() > Operator.fromVal(operator.peek()).getLevel()) {
                operator.push(item);
            } else {
                operand.push(operator.pop());
                index --;
            }
        }

        while (!operator.isEmpty()){
            operand.push(operator.pop());
        }

        return operand.toArray(new String[0]);
    }

    private static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static String insertBlank(String expression) {
        return expression.replaceAll(Operator.getAllOperationsRegular(), " $0 ").trim();
    }
}
