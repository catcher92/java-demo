package com.catcher92.demo.algorithm.leetcode;

public class LC556 {

    public static void main(String[] args) {
        Integer a = 1024;
        Integer b = new Integer(1024);
        Integer c = 1024;
        int d = 1024;

        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a==d);
        System.out.println(b==c);
        System.out.println(b==d);
        System.out.println(c==d);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param N int整型
     * @return int整型
     */
    public int findGreaterNum(int N) {
        char[] nums = Integer.toString(N).toCharArray();
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }

        int j = nums.length - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            j--;
        }
        swap(nums, i, j);
        reverse(nums, i + 1);
        long ans = Long.parseLong(new String(nums));
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    // [l...)
    private void reverse(char[] nums, int l) {
        for (int i = l, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    private void swap(char[] nums, int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
