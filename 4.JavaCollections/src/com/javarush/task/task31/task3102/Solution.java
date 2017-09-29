package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();

        File rootFile = new File(root);

        ArrayDeque<File> deque = new ArrayDeque<>();
        deque.add(rootFile);
        while (!deque.isEmpty()) {
            File child = deque.pop();
            if (child.isDirectory())
                for (File f : child.listFiles()) deque.push(f);
            else if (child.isFile())
                list.add(child.getAbsolutePath());
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFileTree("/home/l1s/IdeaProjects/test-folder"));
        System.out.println(getFileTree("/home/l1s/IdeaProjects/test-folder").size());
    }
}