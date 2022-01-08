package com.javarush.task.pro.task04.task0402;

import java.util.Scanner;

/* 
Все любят Мамбу
*/

public class Solution {
    public static void main(String[] args) {
        String text = " любит меня.";
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int i = 0;
        while (i < 10) {
            System.out.println(name + text);
            i++;
        }

    }
}
