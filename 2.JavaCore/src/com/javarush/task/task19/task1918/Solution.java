package com.javarush.task.task19.task1918;

/*
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String tag = args[0];
        String begin = "<" + tag + ">";
        String end = "</" + tag + ">";

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        consoleReader.close();

        StringBuffer sb = new StringBuffer();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready())
                sb.append(fileReader.readLine());
        }

        Pattern p = Pattern.compile(begin + "(.*?)" + end);
        Matcher m = p.matcher(sb.toString());
        while (m.find())
            System.out.println(m.group(1));
    }
}
