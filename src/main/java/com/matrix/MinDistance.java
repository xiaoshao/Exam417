package com.matrix;

public class MinDistance {

    public static void main(String[] args) {

    }

    public int[][] distance(int[][] data) {
        int row = data.length;
        if (row == 0) {
            return data;
        }
        int col = data[0].length;

        int[][] result = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (data[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        return null;
    }
}
