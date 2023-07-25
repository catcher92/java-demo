package com.catcher92.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC231 {

    public boolean isPowerOfTwo(int n) {
        if (n == 1) {
            return true;
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            long x = 2L << mid;
            if (x == n) {
                return true;
            } else if (x > n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(isPowerOfTwo(1));
        Assert.assertTrue(isPowerOfTwo(2));
        Assert.assertFalse(isPowerOfTwo(3));
        Assert.assertTrue(isPowerOfTwo(4));
        Assert.assertTrue(isPowerOfTwo(16));
        Assert.assertFalse(isPowerOfTwo(1073741825));
    }
}
