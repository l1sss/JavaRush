package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fileName = args[0];

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(Long.parseLong(args[1]));

            byte[] buffer = new byte[args[2].length()];
            raf.read(buffer, 0, buffer.length);
            raf.seek(raf.length());

            String text = convertByteToString(buffer);
            if (text.equals(args[2])) raf.write("true".getBytes());
            else raf.write("false".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertByteToString(byte readBytes[]) {
        return new String(readBytes);
    }
}