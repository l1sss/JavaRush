package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://n1s1.elle.ru/48/7b/36/487b36300c62c5f0cb905da52aa874b4/940x627_1_5a0bfdc1ca88097a61d2d64668c61ef9@940x627_0xc0a839a4_18087198581488362059.jpeg", Paths.get("/home/l1s/Загрузки"));

        /*for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }*/
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

        Path temp = Files.createTempFile("temp", ".tmp");
        Files.copy(inputStream, temp, REPLACE_EXISTING); //!!!
        inputStream.close();

        String fileName = urlString.substring(urlString.lastIndexOf("/"), urlString.length());
        Path result = Paths.get(downloadDirectory + "/" + fileName);
        Files.move(temp, result);

        return result;
    }
}
