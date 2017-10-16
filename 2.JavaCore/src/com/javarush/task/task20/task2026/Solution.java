package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 1 && (i == 0 || a[i - 1][j] == 0) && (j == 0 || a[i][j - 1] == 0)) count++;
            }

        return count;
    }
}