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
        helper(s.length - 1, s[s.length - 1], s);
    }

    public void helper(int index, char item, char[] s) {
        if (index < 0) {
            return;
        }

        if (index == 0) {
            s[s.length - index - 1] = item;
            return;
        }

        helper(index - 1, s[index - 1], s);

        s[s.length - index - 1] = item;
    }
}
