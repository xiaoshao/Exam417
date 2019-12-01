package com.recursion;

public class Demo {

    public static void main(String[] args) {
//        String test = "hello world";
//        helper(0, test);

        char[] hello = {'j', 'k', 'm', 't'};

        Demo demo = new Demo();
        demo.reverseString(hello);

        System.out.println(hello);
    }

    public static void helper(int index, String str) {
        if (str == null || index >= str.length()) {
            return;
        }

        helper(index + 1, str);

        System.out.print(str.charAt(index));
    }

    public void reverseString(char[] s) {
        helper(s.length - 2, s);
    }

    public void helper(int index, char[] s) {
        if (index < 0) {
            return;
        }

        helper(index - 1, s);

        char temp = s[index];
        s[index] = s[index + 1];
        s[index + 1] = temp;
    }
}
