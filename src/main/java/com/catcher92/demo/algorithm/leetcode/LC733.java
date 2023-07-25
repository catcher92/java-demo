package com.catcher92.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class LC733 {
    private int[][] visited;
    private int initColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || visited[sr][sc] == 1) {
            return image;
        }
        if (image[sr][sc] == initColor) {
            image[sr][sc] = color;
            visited[sr][sc] = 1;
            floodFill(image, sr + 1, sc, color);
            floodFill(image, sr - 1, sc, color);
            floodFill(image, sr, sc + 1, color);
            floodFill(image, sr, sc - 1, color);
        }
        return image;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[][]{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}}, floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2));
        Assert.assertArrayEquals(new int[][]{{2, 2, 2}, {2, 2, 2}}, floodFill(new int[][]{{0, 0, 0}, {0, 0, 0}}, 0, 0, 2));
        Assert.assertArrayEquals(new int[][]{{2, 2, 2}, {2, 2, 2}}, floodFill(new int[][]{{0, 0, 0}, {0, 0, 0}}, 1, 0, 2));
    }
}
