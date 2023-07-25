package com.catcher92.demo.algorithm.sort;

import java.util.*;


@SuppressWarnings({"UnusedReturnValue", "DuplicatedCode"})
public class BucketSort {

    /**
     * 适用于大量的连续范围数据
     * 比如100万个0-99的数据
     */
    public static List<Integer> sort1(List<Integer> list, int buckSize) {
        System.out.println("排序前:" + list);
        final List<List<Integer>> bucks = new ArrayList<>(buckSize);
        for (int i = 0; i < buckSize; i++) {
            bucks.add(new LinkedList<>());
        }
        list.forEach(i -> bucks.get(i % buckSize).add(i));
        final ArrayList<Integer> result = new ArrayList<>(list.size());
        bucks.forEach(result::addAll);
        System.out.println("排序后:" + result);
        return result;
    }

    /**
     * 更加通用，可以适用于不连续数据
     * 2 4 6 8
     */
    public static List<Integer> sort2(List<Integer> list, int buckSize, int min, int max) {
        System.out.println("排序前:" + list);
        final List<List<Integer>> bucks = new ArrayList<>(buckSize);
        for (int i = 0; i < buckSize; i++) {
            bucks.add(new LinkedList<>());
        }
        int d = max - min + 1;
        list.forEach(i -> {
            int k = (i - min) * buckSize / d;
            bucks.get(k).add(i);
        });
        final ArrayList<Integer> result = new ArrayList<>(list.size());
        bucks.forEach(b -> result.addAll(QuickSort.sort1(b)));
        System.out.println("排序后:" + result);
        return result;
    }
}
