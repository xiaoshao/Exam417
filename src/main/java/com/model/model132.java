package com.model;

public class model132 {
    public static void main(String[] args) {
        int[] ints = {1, 0, 1, -4, -3};
        System.out.println(new model132().is132(ints));
    }

    public boolean is132(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[j] > nums[k]) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
