package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.*;
/*
Прайсы
*/

public class Solution {
    public static List<String> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        consoleReader.close();

        if (args != null && args[0].equals("-c")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                while (reader.ready())
                    lines.add(reader.readLine());
            }

            lines.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int id1 = Integer.parseInt(o1.substring(0, 8).replaceAll("\\D", ""));
                    int id2 = Integer.parseInt(o2.substring(0, 8).replaceAll("\\D", ""));

                    return id1 - id2;
                }
            });

            addProductToList(args);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                lines.clear();
            }
        }
    }

    public static void addProductToList(String[] args) {
        int id = 0;
        if (lines.size() == 0) id = 1;
        else {
            String lastLine = lines.get(lines.size() - 1);
            int lastId = Integer.parseInt(lastLine.substring(0, 8).replaceAll("\\D", ""));
            id = ++lastId;
        }

        String productName = args[1].length() > 30 ? args[1].substring(0, 30) : args[1];
        String price = args[2].length() > 8 ? args[2].substring(0, 8) : args[2];
        String quantity = args[3].length() > 4 ? args[3].substring(0, 4) : args[3];

        String result = String.format("%-8s%-30s%-8s%-4s", id, productName, price, quantity);

        lines.add(result);
    }
}
