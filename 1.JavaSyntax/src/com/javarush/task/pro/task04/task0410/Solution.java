package com.javarush.task.pro.task04.task0410;

import java.util.Scanner;

/* 
Второе минимальное число из введенных
*/

public class Solution {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        int min = Integer.MAX_VALUE;
        int nextMin = Integer.MAX_VALUE;

        while (console.hasNextInt()) {
            int a = console.nextInt();
            if (a < nextMin) {
                if (a < min) {
                    nextMin = min;
                    min = a;
                    continue;
                }
                if (a != min) nextMin = a;
            }
        }

        System.out.println(nextMin);
    }
}