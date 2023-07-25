package com.catcher92.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC1539 {

    public int findKthPositive(int[] arr, int k) {
        int n = Integer.MAX_VALUE;
        int arrIdx = 0;
        int missIdx = 1;
        int i;
        for (i = 1; i < n; i++) {
            if (arrIdx < arr.length && arr[arrIdx] == i) {
                arrIdx++;
            } else {
                if (++missIdx > k) {
                    break;
                }
            }
        }
        return i;
    }

    @Test
    public void test1() {
        Assert.assertEquals(9, findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
        Assert.assertEquals(6, findKthPositive(new int[]{1, 2, 3, 4}, 2));
    }
}
