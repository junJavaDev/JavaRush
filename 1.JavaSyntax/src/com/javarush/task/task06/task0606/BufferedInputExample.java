package com.javarush.task.task06.task0606;

import java.io.*;

import java.io.*;

public class BufferedInputExample {

    public static void main(String[] args) throws IOException {

        FileOutputStream outputStream = new FileOutputStream("C:/Users/Викуля/someFile2.txt");
        BufferedOutputStream bufferedStream = new BufferedOutputStream(outputStream);

        String text = "I love Java!, Потому что гладиолус"; // эту строку мы преобразуем в массив байтов и запишем в файл

        byte[] buffer = text.getBytes();

        bufferedStream.write(buffer, 0, buffer.length);
        bufferedStream.close();
    }
}