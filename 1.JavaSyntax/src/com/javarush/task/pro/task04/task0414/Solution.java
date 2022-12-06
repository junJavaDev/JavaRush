package com.javarush.task.pro.task04.task0414;

import java.util.Scanner;

/* 
Хорошего не бывает много
*/

public class Solution {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String s = console.nextLine();
        int number = console.nextInt();
        do {
            System.out.println(s);
            number--;
        }
        while (number > 0 && number < 4);

    }
}