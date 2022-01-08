package com.javarush.task.pro.task15.task1527;

import java.io.*;
import java.util.Scanner;

/* 
task1527
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             BufferedReader fileReader = new BufferedReader(new FileReader(scanner.nextLine()))) {
            int count = 0;
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                if (count % 2 == 0) {
                    System.out.println(line);
                }
                count++;
            }
        }
    }
}