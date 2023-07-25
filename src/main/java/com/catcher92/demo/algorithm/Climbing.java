package com.catcher92.demo.algorithm;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class Climbing {

    private static Map<Integer, Integer> cache = new HashMap<>();

    public static int climbStairs(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        final int result = climbStairs(n - 1) + climbStairs(n - 2);
        if (!cache.containsKey(n)) {
            cache.put(n, result);
        }
        return result;
    }

    public static void main(String[] args) {
        // Assert.assertEquals(1, climbStairs(1));
        // Assert.assertEquals(2, climbStairs(2));
        // Assert.assertEquals(3, climbStairs(3));
        Assert.assertEquals(5, climbStairs(4));
        System.out.println(climbStairs(45));
    }
}
