package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bf.readLine();
        String fileName2 = bf.readLine();
        String fileName3 = bf.readLine();

        FileInputStream fis = new FileInputStream(fileName1);
        FileOutputStream fos1 = new FileOutputStream(fileName2);
        FileOutputStream fos2 = new FileOutputStream(fileName3);

        int size = fis.available();
        int half = size / 2;

        byte[] firstBuff = new byte[size - half];
        byte[] secondBuff = new byte[half];

        fis.read(firstBuff);
        fos1.write(firstBuff);

        fis.read(secondBuff);
        fos2.write(secondBuff);

        fis.close();
        fos1.close();
        fos2.close();
    }
}