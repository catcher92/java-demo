package com.catcher92.demo.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] arr) {
        System.out.println("排序前:" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length);
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    // arr[l, r)
    private static void mergeSort(int[] arr, int l, int r) {
        if (r - l <= 1) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid, r);
        // 合并
        merge(arr, l, mid, r);
    }

    // [l, mid), [mid, r)
    private static void merge(int[] arr, int l, int mid, int r) {
        // 后边添加元素防止越界
        int[] left = Arrays.copyOfRange(arr, l, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid, r + 1);
        left[left.length - 1] = right[right.length - 1] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int k = l; k < r; k++) {
            if (left[i] > right[j]) {
                arr[k] = right[j++];
            } else {
                arr[k] = left[i++];
            }
        }
    }

}
