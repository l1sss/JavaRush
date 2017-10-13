package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("/home/l1s/CODe/javaRush/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("/home/l1s/CODe/javaRush/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("/home/l1s/CODe/javaRush/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        try (InputStream in = new FileInputStream(new File(fileName))) {
            Properties properties = new Properties();
            if (fileName.endsWith(".xml")) properties.loadFromXML(in);
            else properties.load(in);

            return properties;
        } catch (Exception e) {
            return new Properties();
        }
    }
}
