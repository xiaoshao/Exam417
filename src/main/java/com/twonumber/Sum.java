package com.twonumber;

import java.util.HashMap;

public class Sum {

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        for (int index = 0; index < nums.length; index++) {
            for (int innerIndex = index + 1; innerIndex < nums.length; innerIndex++) {
                if (nums[index] + nums[innerIndex] == target) {
                    result[0] = index;
                    result[1] = innerIndex;
                    break;
                }
            }
        }

        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {

        int[] result = new int[2];

        HashMap<Integer, Integer> diffs = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {

            if (diffs.containsKey(nums[index])) {
                result[0] = diffs.get(nums[index]);
                result[1] = index;
                break;
            }
            int dif = target - nums[index];
            diffs.put(dif, index);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = twoSum2(new int[]{2, 7, 8}, 9);
        System.out.println(result[0] + "==" + result[1]);
    }
}
