package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        final String RANGE = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++)
            sb.append(RANGE.charAt((int) (Math.random() * RANGE.length())));

        String pass = sb.toString();

        int randomIteration1 = 1 + (int) (Math.random() * 3);
        for (int i = 0; i < randomIteration1; i++) {
            String letter = String.valueOf(pass.charAt((int) (Math.random() * pass.length())));
            pass = pass.replaceFirst(letter, letter.toUpperCase());
        }

        int randomIteration2 = 1 + (int) (Math.random() * 3);
        for (int i = 0; i < randomIteration2; i++) {
            while (true) {
                String letter = String.valueOf(pass.charAt((int) (Math.random() * pass.length())));
                if (letter.matches("[a-z]")) {
                    String number = String.valueOf((int) (Math.random() * 9));
                    pass = pass.replaceFirst(letter, number);
                    break;
                }
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(pass.getBytes());

        return out;
    }
}