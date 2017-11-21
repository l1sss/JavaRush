package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();

        for (Class c : classes) {
            if (List.class.isAssignableFrom(c)) {
                if (Modifier.isPrivate(c.getModifiers()) && Modifier.isStatic(c.getModifiers())) {
                    try {
                        //noinspection unchecked
                        Constructor constructor = c.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        List list = (List) constructor.newInstance();
                        list.get(0);
                    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        //ignore
                    } catch (IndexOutOfBoundsException e) {
                        return c;
                    }
                }
            }
        }
        return null;
    }
}
