package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName = args[0];

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(Long.parseLong(args[1]));
            if (raf.length() < Long.parseLong(args[1])) raf.seek(raf.length());
            raf.write(args[2].getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}