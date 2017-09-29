package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName, partOfContent;
    private int minSize, maxSize;

    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean containsName = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName))
            containsName = false;

        boolean containsContent = true;
        String content = new String(Files.readAllBytes(file));
        if (partOfContent != null && !content.contains(partOfContent))
            containsContent = false;

        boolean isInMinBorder = true;
        if (minSize != 0 && minSize > Files.size(file))
            isInMinBorder = false;

        boolean isInMaxBorder = true;
        if (maxSize != 0 && maxSize < Files.size(file))
            isInMaxBorder = false;

        if (containsName && containsContent && isInMinBorder && isInMaxBorder)
            foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}