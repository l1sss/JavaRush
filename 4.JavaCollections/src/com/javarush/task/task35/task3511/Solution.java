package com.javarush.task.task35.task3511;

import java.util.*;

/* 
Wildcards для коллекций
*/
public class Solution {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);

        Set<String> set = new HashSet<>();
        set.add("3");
        set.add("4");
        set.add("5");

        List<Collection> combo = new ArrayList<>();
        combo.add(list);
        combo.add(set);

        System.out.println(sum(list));
        System.out.println(multiply(list));
        System.out.println(concat(list));
        System.out.println(combine(combo));
    }

    public static Double sum(List<? extends Number> list) {
        Double result = 0.0;
        for (int i = 0; i < list.size(); i++) {
            Number numb = list.get(i);
            result += numb.doubleValue();
        }
        return result;
    }

    public static Double multiply(List<? extends Number> list) {
        Double result = 1.0;
        for (int i = 0; i < list.size(); i++) {
            Number numb = list.get(i);
            result *= numb.doubleValue();
        }
        return result;
    }

    public static String concat(List<?> list) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : list) {
            builder.append(obj);
        }
        return builder.toString();
    }

    public static List combine(List<? extends Collection> list) {
        List result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Collection collection = list.get(i);
            result.addAll(collection);
        }
        return result;
    }
}
