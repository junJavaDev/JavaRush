package com.javarush.task.pro.task04.task0406;

import java.util.Scanner;

/* 
Показываем, что получаем
*/

public class Solution {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String in;
        while (true) {
            if (!(in = console.nextLine()).equals("enough")) {
                System.out.println(in);
            }
            else break;
        }

    }
}