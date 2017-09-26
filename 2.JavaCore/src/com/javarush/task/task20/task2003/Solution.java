package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        consoleReader.close();

        try (InputStream inputStream = new FileInputStream(fileName)) {
            load(inputStream);
        }
    }

    public void save(OutputStream outputStream) throws IOException {
        Properties prop = new Properties();
        prop.putAll(properties);

        prop.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);

        properties.putAll((Map)prop);
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();

        /*properties.put("superKey", "superValue");
        OutputStream outputStream = new FileOutputStream("/home/l1s/CODe/javaRush/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2003/config.properties");
        solution.save(outputStream);*/

        solution.fillInPropertiesMap();
        System.out.println(properties);
    }
}