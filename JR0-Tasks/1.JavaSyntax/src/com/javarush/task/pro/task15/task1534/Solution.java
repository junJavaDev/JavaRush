package com.javarush.task.pro.task15.task1534;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        splitFile(fileName, 1024);
    }

    public static void splitFile(String fileName, int partSize) throws IOException {
        char[] buffer = new char[partSize];
        int realReaded;
        String newFileName;
        int count = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {
                realReaded = reader.read(buffer);
                newFileName = getNewFileName(fileName, count++);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
                    writer.write(buffer, 0, realReaded);
                }
            }
        }
    }

    private static String getNewFileName(String oldFileName, int number) {
        String[] fileNameParts = oldFileName.split("\\.");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < fileNameParts.length - 1; i++) {
            result.append(fileNameParts[i]).append(".");
        }

        result.append(number).append(".").append(fileNameParts[fileNameParts.length - 1]);
        return result.toString();
    }
}


//Правильное решение:
//public static void splitFile(String fileName, int partSize) {
//    try (FileChannel inputChannel = FileChannel.open(Paths.get(fileName))) {
//        ByteBuffer buf = ByteBuffer.allocate(partSize);
//        long size = inputChannel.size();
//        long position = 0;
//        int counter = 1;
//        while (position < size) {
//            Path outputFilePath = Paths.get(getNewFileName(fileName, counter));
//            Files.createFile(outputFilePath);
//            try (FileChannel outputChannel = FileChannel.open(outputFilePath, StandardOpenOption.WRITE)) {
//                buf.clear();
//                if (inputChannel.read(buf) < 0) {
//                    break;
//                }
//                buf.flip();
//                position += outputChannel.write(buf);
//                counter++;
//            }
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
