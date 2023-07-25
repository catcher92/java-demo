package com.catcher92.demo.algorithm.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] arr) {
        System.out.println("排序前:" + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            // j是抓到的牌，先放在最右侧不断地交换找个合适的位置
            int j = i;
            for (; j > 0 && current < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = current;
        }
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
