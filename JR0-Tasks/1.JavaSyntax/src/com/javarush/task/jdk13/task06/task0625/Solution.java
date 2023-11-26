package com.javarush.task.jdk13.task06.task0625;

import java.util.Scanner;

/* 
Минимальная сумма
*/

public class Solution {
    public static int[][] array = new int[3][3];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = console.nextInt();
            }
        }
        int minSumm = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            int lineSumm = 0;
            int columnSumm = 0;
            for (int j = 0; j < array[i].length; j++) {
                lineSumm += array[i][j];
                columnSumm += array[j][i];
            }
            if (lineSumm < minSumm) minSumm = lineSumm;
            if (columnSumm < minSumm) minSumm = columnSumm;
        }
        System.out.println(minSumm);
    }
}
