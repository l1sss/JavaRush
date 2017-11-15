package com.javarush.task.task35.task3507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();

        Path source = Paths.get(pathToAnimals);
        System.out.println(source);

        MyClassLoader cl = new MyClassLoader();
        try {
            List<Path> list = Files.list(source)
                    .filter(path -> path.toString().endsWith(".class"))
                    .collect(Collectors.toList());

            for (Path p : list) {
                try {
                    Class clazz = cl.findClass(p.toString());
                    set.add((Animal) clazz.newInstance());
                } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return set;
    }
}
