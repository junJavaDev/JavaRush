package com.javarush.task.pro.task15.task1514;

import java.nio.file.Path;
import java.util.Scanner;

/* 
Все относительно
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        Path firstPath = Path.of(str1);
        Path secondPath = Path.of(str2);
        firstPath.toAbsolutePath();
        secondPath.toAbsolutePath();
        if (firstPath.getRoot().equals(secondPath.getRoot())) {
            System.out.println(secondPath.relativize(firstPath));
        }
    }
}

