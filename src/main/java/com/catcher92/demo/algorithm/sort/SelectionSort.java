package com.catcher92.demo.algorithm.sort;

import java.util.Arrays;

/**
 * O(N²)
 */
public class SelectionSort {

    public static void sort(int[] arr) {
        System.out.println("排序前:" + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            int j = minIndex(arr, i, arr.length);
            Helper.swap(arr, i, j);
        }
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    // array[L, r)
    private static int minIndex(int[] array, int l, int r) {
        int minIndex = l;
        for (int k = l; k <= r - 1; k++) {
            if (array[minIndex] > array[k]) {
                minIndex = k;
            }
        }
        return minIndex;
    }
}
