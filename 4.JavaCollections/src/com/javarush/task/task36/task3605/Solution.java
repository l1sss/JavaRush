package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        List<Character> list = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            StringBuilder sb = new StringBuilder();

            Path path = Paths.get(args[0]);
            sb.append(Files.readAllLines(path));

            String text = sb.toString().toLowerCase().replaceAll("[^a-z]", "");

            TreeSet<Character> set = new TreeSet<>();
            for (char c : text.toCharArray())
                set.add(c);

            list = new ArrayList<>(set);
        }

        int printSize = list.size() < 5 ? list.size() : 5;

        for (int i = 0; i < printSize; i++) {
            System.out.print(list.get(i));
        }
    }
}
