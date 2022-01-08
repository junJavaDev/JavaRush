package com.javarush.task.pro.task15.task1528;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

/* 
task1528
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             FileOutputStream fileOutputStream = new FileOutputStream(scanner.nextLine())) {
            byte[] bytes = new byte[]{106, 97, 118, 97};
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            System.out.println("Something went wrong : " + e);
        }
    }
}