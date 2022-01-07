package com.javarush.task.pro.task05.task0506;

import java.util.Scanner;

/* 
Минимальное из N чисел
*/

public class Solution {
    public static int[] array;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        final int N = in.nextInt();
        if (N > 0) {
            array = new int[N];
            for (int i = 0; i < array.length; i++) {
                array[i] = in.nextInt();
            }
            int min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < min) min = array[i];
            }
            System.out.println(min);
        }
    }
}
