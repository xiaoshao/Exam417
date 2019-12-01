package com;

public class duplicate {

    public static void main(String[] args) {
        int[] array = {0, 1, 1, 1, 2, 2, 2, 3, 4};

        int k = new duplicate().removeDuplicate(array);

        for (int index = 0; index < k; index++) {
            System.out.println(array[index]);
        }

        System.out.println();
        int[] array1 = {0, 1, 1, 1, 2, 2, 2, 3, 4};

        int kIndex = new duplicate().remoteDuplicate(array1, 2);

        for (int index = 0; index < kIndex; index++) {
            System.out.println(array1[index]);
        }
    }

    public int removeDuplicate(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int k = 1;

        for (int index = 1; index < array.length; index++) {
            if (array[index] != array[k - 1]) {
                array[k] = array[index];
                k++;
            }
        }

        return k;
    }

    public int remoteDuplicate(int[] array, int maxDuplicateCount) {
        if (array == null || array.length <= maxDuplicateCount) {
            return Math.min(array.length, maxDuplicateCount);
        }

        int kIndex = 1;
        int count = 0;
        for (int index = 1; index < array.length; index++) {
            if (array[kIndex - 1] != array[index]) {
                array[kIndex] = array[index];
                count = 1;
                kIndex++;
            } else if (array[kIndex - 1] == array[index] && count < maxDuplicateCount) {
                array[kIndex] = array[index];
                count++;
                kIndex++;
            }
        }

        return kIndex;
    }
}
