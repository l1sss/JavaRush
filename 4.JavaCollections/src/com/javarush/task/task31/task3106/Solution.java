package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> zipParts = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            zipParts.add(args[i]);
        }

        zipParts.sort((o1, o2) -> {
            int o1Part = Integer.parseInt(o1.substring(o1.lastIndexOf(".") + 1));
            int o2Part = Integer.parseInt(o2.substring(o2.lastIndexOf(".") + 1));
            return o1Part - o2Part;
        });

        List<FileInputStream> fisList = new ArrayList<>();
        for (int i = 0; i < zipParts.size(); i++) {
            fisList.add(new FileInputStream(zipParts.get(i)));
        }

        try (ZipInputStream zip = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fisList)));
             FileOutputStream resultFileOutputStream = new FileOutputStream(args[0])) {
            byte[] buffer = new byte[1024 * 1000];
            while (zip.getNextEntry() != null) {
                int len;
                while ((len = zip.read(buffer)) != -1)
                    resultFileOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}