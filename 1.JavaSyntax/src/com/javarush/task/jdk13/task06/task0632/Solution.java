package com.javarush.task.jdk13.task06.task0632;


import java.io.IOException;
import java.util.Scanner;

/* 
Пирамида
*/

public class Solution {
    public static char[][] array;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int hight = scanner.nextInt();
        int weight = hight * 2 - 1;

        array = new char[hight][weight];
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < weight; j++) {
                if (j < hight - 1 - i || j > hight - 1 + i) array[i][j] = ' ';
                else array[i][j] = '#';
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}
