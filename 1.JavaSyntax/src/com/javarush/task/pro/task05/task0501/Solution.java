package com.javarush.task.pro.task05.task0501;

import java.util.Arrays;

/*
Выводим двумерные массивы
*/

public class Solution {
    public static int[] array = new int[21];
    public static int valueStart = 10;
    public static int valueEnd = 13;

    public static void main(String[] args) {
        int half = array.length / 2 + array.length % 2;
        Arrays.fill(array, 0, half, valueStart);
        Arrays.fill(array, half, array.length, valueEnd);

        System.out.println(Arrays.toString(array));
    }
}

