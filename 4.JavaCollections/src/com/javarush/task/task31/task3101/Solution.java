package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    private static List<File> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try (FileWriter writer = new FileWriter(allFilesContent)) {
            File path = new File(args[0]);
            runToDir(path);
            listSort();

            for (File temp : list) {
                try (BufferedReader reader = new BufferedReader(new FileReader(temp))) {
                    while (reader.ready()) {
                        String line = reader.readLine();
                        writer.write(line);
                    }
                    writer.write('\n');
                }
            }
        }
    }

    static void runToDir(File file) {
        for (File temp : file.listFiles()) {
            if (temp.isFile() && temp.length() > 50) FileUtils.deleteFile(temp);
            else if (temp.isDirectory()) runToDir(temp);
            else list.add(temp);
        }
    }

    static void listSort() {
        list.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}