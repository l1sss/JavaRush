package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private boolean sex;
        private String name;
        private int age;
        private double growth;
        private double weight;
        private String profession;

        public Human(boolean sex) {
            this.sex = sex;
        }
        public Human(boolean sex, int age) {
            this.sex = sex;
            this.age = age;
        }
        public Human(boolean sex, double growth, double weight) {
            this.sex = sex;
            this.growth = growth;
            this.weight = weight;
        }
        public Human(boolean sex, String name) {
            this.sex = sex;
            this.name = name;
        }
        public Human(boolean sex, String name, int age) {
            this.sex = sex;
            this.name = name;
            this.age = age;
        }
        public Human(boolean sex, String name, int age, String profession) {
            this.sex = sex;
            this.name = name;
            this.age = age;
            this.profession = profession;
        }
        public Human(String name, String profession) {
            this.name = name;
            this.profession = profession;
        }
        public Human(String name) {
            this.name = name;
        }
        public Human(String profession, boolean sex) {
            this.profession = profession;
            this.sex = sex;
        }
        public Human(String name, boolean sex, int age, double growth, double weight) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.growth = growth;
            this.weight = weight;
        }
    }
}
