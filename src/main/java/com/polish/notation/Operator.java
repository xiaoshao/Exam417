package com.polish.notation;

import java.util.HashMap;
import java.util.Map;

public enum Operator {

    MAX("max", 0),
    MIN("min", 0),
    PLUS("+", 10),
    SUB("-", 10),
    DIVIDE("/", 20),
    PLUSP("*", 20);

//    LEFTQUATA("(", 3),
//    RIGHTTQUATA(")", 3),
//    DOT(",", 4);

    private static final Map<String, Operator> keyMap = new HashMap<String, Operator>();
    static {
        for (Operator item : Operator.values()) {
            keyMap.put(item.getOp(), item);
        }
    }

    private static String regular = "[-+*/(),]|max|min";

    public static String getAllOperationsRegular() {
        return regular;
    }

    private String op;
    private int level;

    public String getOp() {
        return op;
    }

    Operator(String op, int level) {
        this.op = op;
        this.level = level;
    }

    public static Operator fromVal(String s) {
        return keyMap.get(s);
    }

    public int calc(int left, int right) {
        if (op.length() == 1) {
            switch (op.charAt(0)) {
                case '+': return left + right;
                case '-': return left - right;
                case '*': return left * right;
                default:  return left / right;
            }
        } else {
            if ("min".equals(op.toLowerCase())) {
                return Math.min(left, right);
            } else {
                return Math.max(left, right);
            }
        }

    }

    public int getLevel() {
        return level;
    }
}
