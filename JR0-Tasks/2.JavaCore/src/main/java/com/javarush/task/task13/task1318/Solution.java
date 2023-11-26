package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = reader.readLine();
        //String filePath = "C:/Users/Aleksandr/" + fileName + ".txt";
        FileInputStream input = new FileInputStream(filePath);
        //BufferedInputStream buffer = new BufferedInputStream(input);
         //(buffer.available() > 0) {
        //System.out.print((char)input.read());
        //}
        while (input.available() > 0) {

            char symbol = (char)input.read();
            System.out.print(symbol);
        }
        input.close();
        reader.close();
    }
}