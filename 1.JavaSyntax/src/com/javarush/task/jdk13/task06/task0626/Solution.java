package com.javarush.task.jdk13.task06.task0626;

import java.util.Scanner;

/* 
Первая база данных
*/

public class Solution {
    public static String[][] array = new String[][]{{"123", "Иванов", "Богдан"},
                                                    {"1425", "Петрова", "Марина"},
                                                    {"37", "Богдан", "Андрей"},
                                                    {"98", "Богданова", "Марина"},
                                                    {"6285", "Прутко", "Сергей"},
                                                    {"8", "Клочкова", "Елена"},
                                                    {"754", "Котов", "Иван"}};

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String in = console.nextLine();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (in.equals(array[i][j])) {
                    String result = "";
                    for (int k = 0; k < array[i].length; k++) {
                        result += array[i][k] + " ";
                    }
                    System.out.println(result.substring(0, result.length() - 1));
                }
            }
        }
    }
}
