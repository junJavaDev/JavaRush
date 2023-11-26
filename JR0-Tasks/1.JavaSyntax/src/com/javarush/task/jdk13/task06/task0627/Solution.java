package com.javarush.task.jdk13.task06.task0627;

import java.util.Scanner;

/*
Вот это переворот!
*/

public class Solution {
    public static int[][] array =// new int[3][3];
          {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
          };

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        //Заполнение массива с клавиатуры
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = console.nextInt();
            }
        }

        //Переворот массива относительно главной диагонали
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array[i].length; j++) {
                if (i != j) {
                    int temp = array[i][j];
                    array[i][j] = array[j][i];
                    array[j][i] = temp;
                }
            }
        }

        //Вывод перевёрнутого массива на экран
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}