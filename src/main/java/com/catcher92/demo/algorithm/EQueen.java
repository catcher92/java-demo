package com.catcher92.demo.algorithm;

public class EQueen {
    private final int size = 8;
    private final int[] queens = new int[size];

    public boolean solve(int col) {
        if (col == size) {
            return true;
        }
        // i : row
        for (int i = 0; i < size; i++) {
            queens[col] = i;
            boolean resolve = true;
            for (int j = 0; j < col; j++) {
                int colDiff = col - j;
                int rowDiff = Math.abs(queens[col] - queens[j]);
                if (rowDiff == 0 || colDiff == rowDiff) {
                    resolve = false;
                    break;
                }
            }
            if (resolve && solve(col + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final EQueen eQueen = new EQueen();
        final boolean solve = eQueen.solve(0);
        if (solve) {
            eQueen.print();
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (queens[j] == i) {
                    System.out.print("Q");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
}
