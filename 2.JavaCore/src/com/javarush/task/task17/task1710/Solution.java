package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    public static SimpleDateFormat dfIn = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public static SimpleDateFormat dfOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        int id;
        Person person = null;

        switch (args[0]) {
            case "-c":
                if (args[2].equals("м")) {
                    allPeople.add(Person.createMale(args[1], dfIn.parse(args[3])));
                    System.out.println(allPeople.size() - 1);
                }
                else if (args[2].equals("ж")) {
                    allPeople.add(Person.createFemale(args[1], dfIn.parse(args[3])));
                    System.out.println(allPeople.size() - 1);
                }
                else throw new IllegalArgumentException("Invalid argument input!");
                break;

            case "-u":
                id = Integer.parseInt(args[1]);
                person = allPeople.get(id);
                person.setName(args[2]);
                person.setSex((args[3].equals("м") ? Sex.MALE : Sex.FEMALE));
                person.setBirthDay(dfIn.parse(args[4]));
                break;

            case "-d":
                id = Integer.parseInt(args[1]);
                person = allPeople.get(id);
                person.setName(null);
                person.setSex(null);
                person.setBirthDay(null);
                break;

            case "-i":
                id = Integer.parseInt(args[1]);
                person = allPeople.get(id);
                String sex = (person.getSex() == Sex.MALE ? "м" : "ж");
                System.out.println(person.getName() + " " + sex + " " + dfOut.format(person.getBirthDay()));
                break;

            default:
                throw new IllegalArgumentException("Invalid param input!");
        }
    }
}