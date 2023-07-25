package com.catcher92.demo.algorithm.sort;

import java.util.Arrays;

/**
 * leetcode
 * 75: sort-colors
 */
public class ThreeQuickSort {

    public static void sort(int[] nums) {
        // [0...zero]= 0
        // [zero+1...i-1] = 1
        // [two...n-1] = 2
        System.out.println("排序前:" + Arrays.toString(nums));
        int zero = -1, two = nums.length;
        for (int i = 0; i < two; ) {
            if (nums[i] == 0) {
                Helper.swap(nums, ++zero, i);
                i++;
            } else if (nums[i] == 2) {
                Helper.swap(nums, --two, i);
            } else {
                i++;
            }
        }
        System.out.println("排序后:" + Arrays.toString(nums));
    }

    public int[] twoSum(int[] numbers, int target) {
        int index0 = 0, index1 = 1;
        for (int i = 0; i < numbers.length; i++) {
            int j = search(numbers, i + 1, numbers.length - 1, target - numbers[i]);
            if (j != -1) {
                index0 = i + 1;
                index1 = j + 1;
                break;
            }
        }
        return new int[]{index0, index1};
    }

    // search target in [l, r]
    private int search(int[] numbers, int l, int r, int target) {
        if (r - l < 0) {
            return -1;
        }
        if (r == l && numbers[l] == target) {
            return l;
        }
        if (r - l == 1) {
            if (numbers[l] == target) {
                return l;
            } else if (numbers[r] == target) {
                return r;
            }
            return -1;
        }
        int mid = (l + r) / 2;
        if (target > numbers[mid]) {
            return search(numbers, mid, r, target);
        } else if (target < numbers[mid]) {
            return search(numbers, l, mid, target);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        final ThreeQuickSort sort = new ThreeQuickSort();
        System.out.println(Arrays.toString(sort.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(sort.twoSum(new int[]{2, 3, 4}, 6)));
    }
}
