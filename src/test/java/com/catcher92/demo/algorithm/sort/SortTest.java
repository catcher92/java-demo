package com.catcher92.demo.algorithm.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortTest {

    private final List<int[]> arrays = new ArrayList<>();

    @Before
    public void init() {
        arrays.add(new int[]{});
        arrays.add(new int[]{1});
        arrays.add(new int[]{1, 1});
        arrays.add(new int[]{1, 2});
        arrays.add(new int[]{1, 2, 1});
        arrays.add(new int[]{1, 2, 3});
        arrays.add(new int[]{1, 3, 2});
        arrays.add(new int[]{3, 2, 1});
        arrays.add(new int[]{2, 1, 3});
        arrays.add(new int[]{2, 3, 1});
        arrays.add(new int[]{7, 6, 9, 3, 1, 5, 2, 4});
    }

    @Test
    public void testSelectionSort() {
        for (int[] array : arrays) {
            System.out.println("----");
            SelectionSort.sort(array);
        }
    }

    @Test
    public void testBubbleSort() {
        for (int[] array : arrays) {
            System.out.println("----");
            BubbleSort.sort(array);
        }
    }

    @Test
    public void testInsertionSort() {
        for (int[] array : arrays) {
            System.out.println("----");
            InsertionSort.sort(array);
        }
    }

    @Test
    public void testMergeSort() {
        for (int[] array : arrays) {
            System.out.println("----");
            MergeSort.sort(array);
        }
    }

    @Test
    public void testQuickSort1() {
        for (int[] array : arrays) {
            System.out.println("----");
            QuickSort.sort1(Arrays.stream(array).boxed().collect(Collectors.toList()));
        }
    }

    @Test
    public void testQuickSort2() {
        for (int[] array : arrays) {
            System.out.println("----");
            QuickSort.sort2(array);
        }
    }

    @Test
    public void testBucketSort1() {
        for (int[] array : arrays) {
            System.out.println("----");
            BucketSort.sort1(Arrays.stream(array).boxed().collect(Collectors.toList()), 100);
        }
    }

    @Test
    public void testBucketSort2() {
        final List<Integer> list = Arrays.asList(2, 8, 6, 8, 4, 2);
        BucketSort.sort2(list, 2, 2, 8);
    }

    @Test
    public void testCountingSort() {
        CountSorting.sort(new int[]{2, 0, 2, 1, 1, 0, 1, 1, 0});
    }

    @Test
    public void testThreeQuickSort() {
        ThreeQuickSort.sort(new int[]{2, 0, 2, 1, 1, 0, 1, 1, 0});
    }
}
