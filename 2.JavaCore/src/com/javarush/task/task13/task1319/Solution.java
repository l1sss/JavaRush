package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Запись в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        String s;
        while (true) {
            s = reader.readLine();
            writer.write(s + "\n");
            writer.flush();
            if (s.equals("exit")) break;
        }

        reader.close();
        writer.close();
    }
}
