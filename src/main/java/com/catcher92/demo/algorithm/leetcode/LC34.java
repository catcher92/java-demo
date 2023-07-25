package com.catcher92.demo.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class LC34 {



    public static void main(String[] args) {
        final HashSet<Short> shorts = new HashSet<>();
        for (Short i = 0; i < 100; i++) {
            shorts.add(i);
            shorts.remove(i-1);
        }
        System.out.println(shorts.size());
    }


}
