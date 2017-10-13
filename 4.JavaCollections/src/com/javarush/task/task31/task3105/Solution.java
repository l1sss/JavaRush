package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path pathToZip = Paths.get(args[1]);

        Map<String, ByteArrayOutputStream> archivedFiles = new HashMap<>();

        try (ZipInputStream zipReader = new ZipInputStream(new FileInputStream(pathToZip.toString()))) {
            ZipEntry entry;
            while ((entry = zipReader.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[32];
                int count;
                while ((count = zipReader.read(buffer)) != -1)
                    byteArrayOutputStream.write(buffer, 0, count);

                archivedFiles.put(entry.getName(), byteArrayOutputStream);
            }
        }

        Path pathToFile = Paths.get(args[0]);

        try (ZipOutputStream zipWriter = new ZipOutputStream(new FileOutputStream(pathToZip.toString()))) {
            for (Map.Entry<String, ByteArrayOutputStream> pair : archivedFiles.entrySet()) {
                String fileName = pair.getKey().substring(pair.getKey().lastIndexOf("/") + 1);
                if (fileName.equals(pathToFile.getFileName().toString())) continue;
                zipWriter.putNextEntry(new ZipEntry(pair.getKey()));
                zipWriter.write(pair.getValue().toByteArray());
            }

            ZipEntry zipEntry = new ZipEntry("new/" + pathToFile.getFileName());
            zipWriter.putNextEntry(zipEntry);
            Files.copy(pathToFile, zipWriter);
        }
    }
}