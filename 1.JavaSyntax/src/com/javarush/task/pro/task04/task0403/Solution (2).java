package com.javarush.task.pro.task04.task0403;

import java.util.Scanner;

/* 
Суммирование
*/

public class Solution {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int summ = 0;
        String input;
        while (!(input = console.nextLine()).equals("ENTER")) {
            summ += Integer.parseInt(input);
        }
        System.out.println(summ);


    }
}