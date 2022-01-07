package com.javarush.task.pro.task03.task0314;

import java.util.Scanner;

/* 
Сломанная клавиатура
*/

public class Solution {
    public static String secret = "AmIGo";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (in.nextLine().equalsIgnoreCase(secret))
            System.out.println("доступ разрешен");
        else
            System.out.println("доступ запрещен");
    }
}
