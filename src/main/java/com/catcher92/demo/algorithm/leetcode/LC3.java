package com.catcher92.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC3 {

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        final int[] counts = new int[128];
        int maxLength = 0;
        int l = 0, r = 0;
        while (r < length) {
            System.out.printf("l=%s,r=%s,max=%s,map=%s%n", l, r, maxLength, Arrays.toString(counts));
            if (counts[s.charAt(r)] == 0) {
                counts[s.charAt(r)]++;
                maxLength = Math.max(r - l + 1, maxLength);
                r++;
            } else {
                int lastIndex = counts[s.charAt(r)];
                int oldL = l;
                l = lastIndex + 1 > l ? lastIndex + 1 : r;
                for (int i = oldL; i < l; i++) {
                    counts[(s.charAt(i))] = 0;
                }
            }
        }
        return maxLength;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        // Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
        // Assert.assertEquals(1, lengthOfLongestSubstring("b"));
        // Assert.assertEquals(1, lengthOfLongestSubstring("bb"));
        // Assert.assertEquals(2, lengthOfLongestSubstring("abba"));
        // Assert.assertEquals(5, lengthOfLongestSubstring("tmmzuxt"));
    }
}
