package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String key = "abra-kadabra";

        try (FileInputStream reader = new FileInputStream(args[0]);
             FileOutputStream writer = new FileOutputStream(args[1])) {
            byte[] buffer = new byte[reader.available()];

            reader.read(buffer);

            writer.write(crypt(key, buffer));
        }
    }

    static byte[] crypt(String key, byte[] array) {
        byte[] k = key.getBytes();

        for (int i = 0; i < array.length; i++) {
            array[i] ^= k[i % k.length];
        }

        return array;
    }
}