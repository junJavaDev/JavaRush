package com.javarush.task.pro.task15.task1526;

import java.io.*;
import java.util.Scanner;

/* 
task1526
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             FileReader fileReader = new FileReader(scanner.nextLine())) {
            while (fileReader.ready()) {
                char ch = (char)fileReader.read();
                if (ch != ' ' && ch != ',' && ch != '.') {
                    System.out.print(ch);
                }
            }
        }
    }
}