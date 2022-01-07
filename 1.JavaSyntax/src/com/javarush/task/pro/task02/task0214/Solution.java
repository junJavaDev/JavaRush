package com.javarush.task.pro.task02.task0214;

import java.util.Scanner;

/* 
Чтение и преобразование строк
*/

public class Solution {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String string1 = console.nextLine();
        String string2 = console.nextLine();
        String string3 = console.nextLine();
        System.out.println(string3);
        System.out.println(string2.toUpperCase());
        System.out.println(string1.toLowerCase());

    }
}
