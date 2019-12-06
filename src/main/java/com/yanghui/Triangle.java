package com.yanghui;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> result = new Triangle().generate(5);
        System.out.println(result);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = init(numRows);

        for (int index = 2; index < numRows; index++) {
            List<Integer> line = result.get(index);
            List<Integer> previousLine = result.get(index - 1);
            for (int innerIndex = 1; innerIndex < index; innerIndex++) {
                line.set(innerIndex, previousLine.get(innerIndex - 1) + previousLine.get(innerIndex));
            }
        }

        return result;
    }

    private List<List<Integer>> init(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for (int index = 0; index < numRows; index++) {
            List<Integer> line = new ArrayList<>(index + 1);
            for (int innerIndex = 0; innerIndex < index + 1; innerIndex++) {
                line.add(1);
            }
            result.add(line);
        }

        return result;
    }
}
