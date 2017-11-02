package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        int a = 2;
        while (a <= n) {
            if ((n % a) == 0) {
                if (a != n) {
                    System.out.println(a + " ");
                    recursion(n / a);
                } else System.out.println(a);
                return;
            }
            a++;
        }
    }
}
