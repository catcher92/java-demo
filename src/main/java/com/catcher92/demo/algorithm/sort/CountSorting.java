package com.catcher92.demo.algorithm.sort;

import java.util.Arrays;

/**
 * leetcode
 * 75: sort-colors
 */
public class CountSorting {

    public static void sort(int[] nums) {
        System.out.println("排序前:" + Arrays.toString(nums));
        final int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        int c = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                nums[c++] = i;
            }
        }
        System.out.println("排序后:" + Arrays.toString(nums));
    }
}
