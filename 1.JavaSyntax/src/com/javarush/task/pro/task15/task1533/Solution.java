package com.javarush.task.pro.task15.task1533;

/* 
task1533
*/

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Solution {

    public static void main(String[] args) throws IOException {
        writeData("Justinian", "Justinian@mega.city.one", 41, 391);
    }

    public static ByteArrayOutputStream writeData(String name, String email, int level, int rating) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] line1Bytes = ("Имя: " + name + "\n").getBytes(StandardCharsets.UTF_8);
            byte[] line2Bytes = ("Почта: " + email + "\n").getBytes(StandardCharsets.UTF_8);
            byte[] line3Bytes = ("Уровень: " + level + "\n").getBytes(StandardCharsets.UTF_8);
            byte[] line4Bytes = ("Рейтинг: " + rating + "\n").getBytes(StandardCharsets.UTF_8);
            byteArrayOutputStream.writeBytes(line1Bytes);
            byteArrayOutputStream.writeBytes(line2Bytes);
            byteArrayOutputStream.writeBytes(line3Bytes);
            byteArrayOutputStream.writeBytes(line4Bytes);
            return byteArrayOutputStream;
        }
    }
}
