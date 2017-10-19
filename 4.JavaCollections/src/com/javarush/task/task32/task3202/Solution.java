package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("/home/l1s/IdeaProjects/test-folder/StringWriter"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready())
                sb.append(reader.readLine());

            StringWriter writer = new StringWriter();
            writer.write(sb.toString());

            return writer;
        } catch (IOException e) {
            return new StringWriter();
        }
    }
}