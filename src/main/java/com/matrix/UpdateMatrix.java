package com.matrix;

import javafx.util.Pair;

import java.util.concurrent.ArrayBlockingQueue;

public class UpdateMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1}, {0, 0, 1}, {1, 1, 1}};

        int[][] result = new UpdateMatrix().update(matrix);

        print(result);
    }

    private int[][] update(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows == 0 || cols == 0) {
            return matrix;
        }

        ArrayBlockingQueue<Pair<Integer, Integer>> queue = new ArrayBlockingQueue(rows * cols);

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    result[i][j] = 0;
                    queue.add(new Pair<>(i, j));
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> node = queue.poll();
            if (node.getKey() + 1 < rows) {
                int dis = result[node.getKey()][node.getValue()] + 1;
                if (dis < result[node.getKey() + 1][node.getValue()]) {
                    result[node.getKey() + 1][node.getValue()] = dis;
                    queue.add(new Pair<>(node.getKey() + 1, node.getValue()));
                }
            }

            if (node.getKey() - 1 >= 0) {
                int dis = result[node.getKey()][node.getValue()] + 1;
                if (dis < result[node.getKey() - 1][node.getValue()]) {
                    result[node.getKey() - 1][node.getValue()] = dis;
                    queue.add(new Pair<>(node.getKey() - 1, node.getValue()));
                }
            }


            if (node.getValue() + 1 < cols) {
                int dis = result[node.getKey()][node.getValue()] + 1;
                if (dis < result[node.getKey()][node.getValue() + 1]) {
                    result[node.getKey()][node.getValue() + 1] = dis;
                    queue.add(new Pair<>(node.getKey(), node.getValue() + 1));
                }
            }


            if (node.getValue() - 1 >= 0) {
                int dis = result[node.getKey()][node.getValue()] + 1;
                if (dis < result[node.getKey()][node.getValue() - 1]) {
                    result[node.getKey()][node.getValue() - 1] = dis;
                    queue.add(new Pair<>(node.getKey(), node.getValue() - 1));
                }
            }
        }

        return result;
    }


    public static void print(int[][] result) {

        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
