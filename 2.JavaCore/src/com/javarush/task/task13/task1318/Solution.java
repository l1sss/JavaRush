package com.javarush.task.task13.task1318;

import java.io.*;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();//считываем с консоли полный путь к файлу
        InputStream inputStream = new FileInputStream(fileName);
        while (inputStream.available() > 0) {
            int data = inputStream.read();//читаем файл
            System.out.print((char)data);//выводим на экран тело файла
        }
        System.out.println();

        inputStream.close();
        reader.close();
    }
}