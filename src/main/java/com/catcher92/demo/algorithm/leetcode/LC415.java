package com.catcher92.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC415 {

    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int result = x + y + add;
            add = result / 10;
            builder.append(result % 10);
        }
        return builder.reverse().toString();
    }

    public String addStrings1(String num1, String num2) {
        if (num2.length() > num1.length()) {
            return addStrings(num2, num1);
        }
        char[] num1Array = num1.toCharArray();
        char[] num2Array = new char[num1Array.length];
        for (int i = 0; i < num2Array.length - num2.length(); i++) {
            num2Array[i] = '0';
        }
        System.arraycopy(num2.toCharArray(), 0, num2Array, num1Array.length - num2.length(), num2.length());
        char[] result = new char[num1Array.length];
        boolean needUpper = false;
        for (int i = num1Array.length - 1; i >= 0; i--) {
            int sum = needUpper ? num1Array[i] - '0' + 1 : num1Array[i] - '0';
            sum += num2Array[i] - '0';
            if (sum >= 10) {
                result[i] = (char) (sum % 10 + '0');
                needUpper = true;
            } else {
                result[i] = (char) (sum + '0');
                needUpper = false;
            }
        }
        return String.format("%s%s", needUpper ? "1" : "", new String(result));
    }

    @Test
    public void test() {
        Assert.assertEquals("100", addStrings("1", "99"));
        Assert.assertEquals("134", addStrings("11", "123"));
        Assert.assertEquals("533", addStrings("456", "77"));
        Assert.assertEquals("0", addStrings("0", "0"));
        Assert.assertEquals("579", addStrings("123", "456"));
        Assert.assertEquals("152", addStrings("31", "121"));
    }
}
