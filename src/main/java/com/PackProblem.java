package com;

public class PackProblem {
    public static void main(String[] args) {
        int sumV = 10;
        int[] vlist = {1, 5, 2, 4};
        int[] wlist = {2, 3, 5, 7};
        int maxWeight = new PackProblem().max(sumV, vlist, wlist);

        System.out.println(maxWeight);
    }

    private int max(int sumV, int[] vlist, int[] wlist) {
        if (sumV <= 0) {
            return 0;
        }

        int[] valueArray = new int[sumV + 1];

        for (int index = 0; index < sumV + 1; index++) {
            valueArray[index] = 0;
        }

        for (int i = 0; i < wlist.length; i++) {
            for (int w = 0; w <= sumV; w++) {
                int load = sumV - w;

                if (load < wlist[i]) {
                    continue;
                } else {
                    int valueIndex = w + wlist[i];
                    int takeValue = valueArray[valueIndex] + vlist[i];
                    int unTakenValue = valueArray[w];

                    valueArray[w] = Math.max(takeValue, unTakenValue);
                }
            }
        }

        return valueArray[0];
    }
}
