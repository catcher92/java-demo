package com.catcher92.demo.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class LC383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counts = new int[26];
        int idx;
        for (int i = 0; i < magazine.length(); i++) {
            idx = magazine.charAt(i) - 'a';
            ++counts[idx];
        }
        System.out.println(Arrays.toString(counts));
        for (int i = 0; i < ransomNote.length(); i++) {
            idx = ransomNote.charAt(i) - 'a';
            if (--counts[idx] < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test1() {
        final LC383 lc383 = new LC383();
        System.out.println(lc383.canConstruct("aa", "aab"));
    }
}
