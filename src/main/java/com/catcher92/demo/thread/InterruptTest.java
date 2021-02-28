package com.catcher92.demo.thread;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class InterruptTest {

    private static final Map<Integer, Thread> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        InterruptTest interruptTest = new InterruptTest();
        assert (24 == interruptTest.maxArea(new int[]{1, 3, 2, 5, 25, 24, 5}));
        assert (49 == interruptTest.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assert (17 == interruptTest.maxArea(new int[]{2, 3, 4, 5, 18, 17, 6}));
        assert (1 == interruptTest.maxArea(new int[]{1, 1}));
        assert (16 == interruptTest.maxArea(new int[]{4, 3, 2, 1, 4}));
        assert (2 == interruptTest.maxArea(new int[]{1, 2, 1}));
        System.out.println(interruptTest.climbStairs(45));
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int maxArea = 0;
        while (j > i) {
            int minHeight = height[i] < height[j] ? height[i++] : height[j--];
            maxArea = Math.max((j - i + 1) * minHeight, maxArea);
        }
        return maxArea;
    }

    private HashMap<Integer, Integer> stairMap = new HashMap<>();

    public int climbStairs(int n) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(Arrays.asList(new Integer[]{}));
        List<Integer[]> nums = new ArrayList<>();
        int n1 = 1, n2 = 2, n3 = n1 + n2;
        for (int i = 3; i <= n; i++) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n3;
    }
}
