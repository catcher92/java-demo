package com.catcher92.demo.algorithm.dp;

import org.junit.Assert;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
            System.out.printf("x=%d,pre=%d,maxAns=%d\n", x, pre, maxAns);
        }
        System.out.println();
        return maxAns;
    }

    public static void main(String[] args) {
        final MaxSubArray solution = new MaxSubArray();
        Assert.assertEquals(6, solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        Assert.assertEquals(1, solution.maxSubArray(new int[]{1}));
        Assert.assertEquals(23, solution.maxSubArray(new int[]{5, 4, -1, 7, 8}));

    }


}
