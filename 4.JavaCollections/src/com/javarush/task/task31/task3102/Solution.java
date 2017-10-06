package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();

        File rootFile = new File(root);

        Stack<File> deque = new Stack<>();
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