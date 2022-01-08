package com.javarush.task.pro.task15.task1524;

import java.io.*;
import java.util.Scanner;

/* 
task1524
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             FileInputStream fis = new FileInputStream(scanner.nextLine());
             FileOutputStream fos = new FileOutputStream(scanner.nextLine())) {
            byte[] file = fis.readAllBytes();
            byte[] result = new byte[file.length];
            int i = 0;
            while (i < file.length - 1) {
                result[i] = file[i + 1];
                result[i + 1] = file[i];
                i += 2;
            }
            if (i < file.length) {
                result[i] = file[i];
            }
            fos.write(result);
        }
    }
}