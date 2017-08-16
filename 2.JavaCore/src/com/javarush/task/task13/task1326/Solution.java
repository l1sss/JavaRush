package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();

        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        ArrayList<Integer> list = new ArrayList<>();

        while (fr.ready()) {
            int i = Integer.parseInt(fr.readLine());
            list.add(i);
        }
        fr.close();

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)%2 == 0) System.out.println(list.get(i));
        }


    }
}
