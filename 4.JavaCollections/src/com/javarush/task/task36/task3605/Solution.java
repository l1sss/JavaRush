package com.javarush.task.task36.task3605;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Path path = Paths.get(args[0]);
        sb.append(Files.readAllLines(path));

        String text = sb.toString().toLowerCase().replaceAll("[^a-z]", "");
        TreeSet<Character> set = new TreeSet<>();
        for (char c : text.toCharArray())
            set.add(c);

        Iterator<Character> iterator = set.iterator();
        int printSize = set.size() < 5 ? set.size() : 5;
        for (int i = 0; i < printSize; i++) {
            System.out.print(iterator.next());
        }
    }
}
