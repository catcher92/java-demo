package com.catcher92.demo.algorithm.leetcode;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class LC80 {

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int slow = 2, fast = 2;
        while (fast < nums.length) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public int removeDuplicates1(List<Integer> list) {
        if (list.size() <= 2) {
            return list.size();
        }
        int slow = 2, fast = 2;
        while (fast < list.size()) {
            if (!Objects.equals(list.get(slow - 2), list.get(fast))) {
                list.remove(slow);
                slow++;
            }
            fast++;
        }
        return slow;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
        Assert.assertEquals(7, removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
        Assert.assertEquals(7, removeDuplicates(new int[]{0, 0, 0, 1, 1, 1, 1, 2, 3, 3, 3}));
        Assert.assertEquals(2, removeDuplicates(new int[]{1, 1, 1}));
        Assert.assertEquals(4, removeDuplicates(new int[]{1, 1, 2, 2}));
    }

    @Test
    public void test1() {
        Assert.assertEquals(5, removeDuplicates1(Lists.newArrayList(1, 1, 1, 2, 2, 3)));
        Assert.assertEquals(7, removeDuplicates1(Lists.newArrayList(0, 0, 1, 1, 1, 1, 2, 3, 3)));
        Assert.assertEquals(7, removeDuplicates1(Lists.newArrayList(0, 0, 0, 1, 1, 1, 1, 2, 3, 3, 3)));
        Assert.assertEquals(2, removeDuplicates1(Lists.newArrayList(1, 1, 1)));
        Assert.assertEquals(4, removeDuplicates1(Lists.newArrayList(1, 1, 2, 2)));
    }
}
