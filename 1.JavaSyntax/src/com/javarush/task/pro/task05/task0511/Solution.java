package com.javarush.task.pro.task05.task0511;

import java.util.Scanner;

/* 
Создаем двумерный массив
*/

public class Solution {
    public static int[][] multiArray;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int N = in.nextInt();
        multiArray = new int[N][];
        for (int i = 0; i < N; i++) {
            multiArray[i] = new int[in.nextInt()];
        }
    }
}
