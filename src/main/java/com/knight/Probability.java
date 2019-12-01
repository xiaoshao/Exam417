package com.knight;

import java.util.Vector;

public class Probability {
    double[][][] dp;

    public static void main(String[] args) {
        Probability probability = new Probability();
        System.out.println(probability.knightProbability(3, 2, 0, 0));
    }


    public double knightProbability(int N, int K, int r, int c) {
        dp = new double[K + 1][N][N];

        for (int kIndex = 0; kIndex < K; kIndex++) {
            for (int index = 0; index < N; index++) {
                for (int innerIndex = 0; innerIndex < N; innerIndex++) {
                    dp[kIndex][index][innerIndex] = 0.0;
                }
            }
        }

        dp[0][r][c] = 1.0d;

        return dfs(N, K, r, c);
    }

    private double dfs(int n, int k, int r, int c) {
        if (k == 0) {
            return 1.0d;
        }

        for (int kindex = 1; kindex <= k; kindex++) {
            for (int index = 0; index < n; index++) {
                for (int innerIndex = 0; innerIndex < n; innerIndex++) {
                    if (dp[kindex - 1][index][innerIndex] > 0.0) {
                        if (isIn(index + 2, innerIndex - 1, n)) {
                            dp[kindex][index + 2][innerIndex - 1] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }

                        if (isIn(index + 2, innerIndex + 1, n)) {
                            dp[kindex][index + 2][innerIndex + 1] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }

                        if (isIn(index - 2, innerIndex - 1, n)) {
                            dp[kindex][index - 2][innerIndex - 1] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }

                        if (isIn(index - 2, innerIndex + 1, n)) {
                            dp[kindex][index - 2][innerIndex + 1] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }

                        if (isIn(index + 1, innerIndex - 2, n)) {
                            dp[kindex][index + 1][innerIndex - 2] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }

                        if (isIn(index + 1, innerIndex + 2, n)) {
                            dp[kindex][index + 1][innerIndex + 2] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }

                        if (isIn(index - 1, innerIndex - 2, n)) {
                            dp[kindex][index - 1][innerIndex - 2] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }

                        if (isIn(index - 1, innerIndex + 2, n)) {
                            dp[kindex][index - 1][innerIndex + 2] += dp[kindex - 1][index][innerIndex] * 0.125;
                        }
                    }
                }
            }
        }

        double result = 0.0;

        for (int index = 0; index < n; index++) {
            for (int innerIndex = 0; innerIndex < n; innerIndex++) {
                System.out.println(dp[k][index][innerIndex]);
                result += dp[k][index][innerIndex];
            }
        }

        return result;
    }

    private boolean isIn(int r, int c, int N) {
        return r >= 0 && c >= 0 && r <= N - 1 && c <= N - 1;
    }
}
