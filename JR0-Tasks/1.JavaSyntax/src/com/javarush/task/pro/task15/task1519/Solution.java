package com.javarush.task.pro.task15.task1519;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

/* 
Поверхностное копирование
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path sourceDirectory = Path.of(scanner.nextLine());
        Path targetDirectory = Path.of(scanner.nextLine());

        DirectoryStream<Path> sourceDirectoryStream = Files.newDirectoryStream(sourceDirectory);
        for (Path path : sourceDirectoryStream) {

            if (Files.isRegularFile(path)) {
                Files.copy(path, targetDirectory.resolve(path.getFileName()));
            }
        }
    }
}

