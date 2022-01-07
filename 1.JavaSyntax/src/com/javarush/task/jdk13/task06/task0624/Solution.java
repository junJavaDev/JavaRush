package com.javarush.task.jdk13.task06.task0624;

import java.util.Scanner;

/* 
Максимальный элемент
*/

public class Solution {
    public static int[][] array = new int[3][3];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = in.nextInt();
                if (array[i][j] > maxValue) maxValue = array[i][j];
            }
        }
        System.out.println(maxValue);
    }
}
