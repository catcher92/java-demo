package com.catcher92.demo.algorithm.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("UnusedReturnValue")
public class QuickSort {

    public static List<Integer> sort1(List<Integer> list) {
        System.out.println("排序前:" + list);
        List<Integer> result = _sort1(list);
        System.out.println("排序后:" + result);
        return result;
    }

    private static List<Integer> _sort1(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }
        int x = list.get(0);
        List<Integer> left = list.stream().filter(i -> i < x).collect(Collectors.toList());
        List<Integer> mid = list.stream().filter(i -> i == x).collect(Collectors.toList());
        List<Integer> right = list.stream().filter(i -> i > x).collect(Collectors.toList());
        left = _sort1(left);
        right = _sort1(right);
        left.addAll(mid);
        left.addAll(right);
        return left;
    }

    public static void sort2(int[] arr) {
        System.out.println("排序前:" + Arrays.toString(arr));
        _sort2(arr, 0, arr.length);
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    // arr[l, r)
    private static void _sort2(int[] arr, int l, int r) {
        if (r - l <= 1) {
            return;
        }
        // 找到第i个元素使的i左边的都< arr[l] i右边的都>arr[l]
        int i = partition(arr, l, r);
        _sort2(arr, l, i);
        _sort2(arr, i + 1, r);
    }

    // [l, r)
    private static int partition(int[] arr, int l, int r) {
        int x = arr[l];
        int i = l + 1, j = r;
        while (i != j) {
            if (arr[i] > x) {
                Helper.swap(arr, i, --j);
            } else {
                i++;
            }
        }
        Helper.swap(arr ,i - 1, l);
        return i - 1;
    }
}
