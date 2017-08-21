package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static List<String> parts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String fileName = consoleReader.readLine();
            if (fileName.equals("end")) break;
            parts.add(fileName);
        }
        consoleReader.close();

        parts.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int n1 = Integer.parseInt(o1.substring(o1.lastIndexOf(".part") + 5));
                int n2 = Integer.parseInt(o2.substring(o2.lastIndexOf(".part") + 5));

                return n1 - n2;
            }
        });

        int index = parts.get(0).lastIndexOf(".");
        String fileName = parts.get(0).substring(0, index);

        FileOutputStream writer = new FileOutputStream(fileName);

        for (String file : parts) {
            FileInputStream reader = new FileInputStream(file);
            byte[] buffer = new byte[reader.available()];

            reader.read(buffer);
            writer.write(buffer);

            reader.close();
        }
        writer.close();
    }
}