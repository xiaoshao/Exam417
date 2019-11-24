package com;

import java.util.Stack;

public class BolanEx2 {

    public static void main(String[] args) {
        String expression = "0-(2*(1+3)+8)/4+1+9";
        Stack<Character> characterStack = new Stack();
        Stack<Double> integerStack = new Stack();
        // 1.拆分字符串
        char[] arr = expression.toCharArray();
        // 2.将拆分好的字符依次放入两个栈中
        Double result = setStackDate(arr, characterStack, integerStack);

        System.out.println(expression + "=" + result);
    }

    /**
     * 将拆分好的字符依次放入两个栈中
     *
     * @param arr            需要运算的字符
     * @param characterStack 符号运算符
     * @param integerStack   数字运算符
     */
    private static Double setStackDate(char[] arr, Stack<Character> characterStack, Stack<Double> integerStack) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                characterStack.push(arr[i]);
                continue;
            }
            if (arr[i] == ')') {
                // 3.根据规则,弹栈并运算
                calculate(characterStack, integerStack);
                continue;
            }
            // 如果是数字
            if ('0' <= arr[i] && arr[i] <= '9') {
                integerStack.push(Double.valueOf(String.valueOf(arr[i])));
            } else {
                // 如果是运算符
                characterStack.push(arr[i]);
            }
        }
        // 将栈中,最后的结果算出
        Double num1 = integerStack.pop();
        Double num2 = integerStack.pop();
        integerStack.push(num2);
        integerStack.push(num1);
        return calculate(characterStack, integerStack);
    }

    /**
     * 根据规则进行计算
     *
     * 规则:
     * integerStack弹出两位,与characterStack弹出的一位运算符进行计算,并把结果压回到integerStack,直到characterStack为空
     *
     * @param characterStack 字符栈
     * @param integerStack   数字栈
     */
    private static Double calculate(Stack<Character> characterStack, Stack<Double> integerStack) {
        Double sum = 0.0;
        while (!characterStack.empty()) {
            Double num1 = integerStack.pop();
            Double num2 = integerStack.pop();
            Character operator = characterStack.pop();
            // 乘/除 检验
            if (!characterStack.empty() && (characterStack.peek() == '*' || characterStack.peek() == '/')) {
                Character speOperator = characterStack.pop();
                Double speNum = integerStack.pop();
                if (speOperator == '*') {
                    num2 = speNum * num2;
                } else if (speOperator == '/') {
                    num2 = speNum / num2;
                }
            }
            if (operator == '+') {
                sum = num2 + num1;
            } else if (operator == '-') {
                sum = num2 - num1;
            } else if (operator == '*') {
                sum = num2 * num1;
            } else if (operator == '/') {
                sum = num2 / num1;
            }
            integerStack.push(sum);
            // '(' 检验
            if (!characterStack.empty() && characterStack.peek() == '(') {
                characterStack.pop();
                return sum;
            }
        }
        return sum;
    }
}
