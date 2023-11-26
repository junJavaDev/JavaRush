package com.javarush.task.jdk13.task06.task0634;

import java.util.Scanner;

/* 
Шахматная доска
*/

public class Solution {
    public static char[][] array;

    public static void main(String[] args) {
        int dimension = new Scanner(System.in).nextInt();
        array = new char[dimension][dimension];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (i + j) % 2 == 0 ? '#' : ' ';
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}
