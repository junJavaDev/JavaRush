package com.javarush.task.pro.task15.task1520;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/* 
Перемещение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path sourceDirectory = Path.of(scanner.nextLine());
        Path targetDirectory = Path.of(scanner.nextLine());

        DirectoryStream<Path> sourceDirectoryStream = Files.newDirectoryStream(sourceDirectory);
        for (Path path : sourceDirectoryStream) {
            if (Files.isRegularFile(path)) {
                Files.move(path, targetDirectory.resolve(path.getFileName()));
            }
        }

    }
}

