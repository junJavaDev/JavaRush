package com.javarush.task.pro.task14.task1411;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    private void readFile(String filePath) throws FileNotFoundException {
        System.out.println("Читаем содержимое файла " + filePath);
    }

    private void writeFile(String filePath) throws FileSystemException {
        System.out.println("Записываем данные в файл " + filePath);
    }

    public void copyFile(String sourceFile, String destinationFile) {
        try {
            readFile(sourceFile);
            writeFile(destinationFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileSystemException e) {
            throw new RuntimeException(e);
        }
    }
}
