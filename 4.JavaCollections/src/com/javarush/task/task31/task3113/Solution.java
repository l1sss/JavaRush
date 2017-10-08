package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(consoleReader.readLine());

        if (!Files.isDirectory(path)) System.out.println(path.toString() + " - не папка");
        else {
            CounterFileVisitor counterFileVisitor = new CounterFileVisitor();
            Files.walkFileTree(path, counterFileVisitor);
            System.out.println("Всего папок - " + counterFileVisitor.getDirectoryCount());
            System.out.println("Всего файлов - " + counterFileVisitor.getFileCount());
            System.out.printf("Общий размер - " + counterFileVisitor.getOverallSize());
        }
    }

    static class CounterFileVisitor extends SimpleFileVisitor<Path> {
        private int directoryCount = -1;
        private int fileCount;
        private long overallSize;

        public int getDirectoryCount() {
            return directoryCount;
        }

        public int getFileCount() {
            return fileCount;
        }

        public long getOverallSize() {
            return overallSize;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            directoryCount++;
            overallSize += Files.size(dir);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            fileCount++;
            overallSize += Files.size(file);

            return FileVisitResult.CONTINUE;
        }
    }
}
