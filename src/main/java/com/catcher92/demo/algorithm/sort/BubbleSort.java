package com.catcher92.demo.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void sort(int[] arr) {
        System.out.println("排序前:" + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Helper.swap(arr, j, j + 1);
                }
            }
        }
        System.out.println("排序后:" + Arrays.toString(arr));
    }

}
