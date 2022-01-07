package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Задача по алгоритмам Ӏ Java Syntax: 9 уровень, 11 лекция
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String line = reader.readLine();
        while (!line.isEmpty()) {
            list.add(line);
            line = reader.readLine();
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> nums = new ArrayList<>();
        for (String lol:array
             ) {
            if (isNumber(lol))
                nums.add(lol);
            else
                strings.add(lol);
        }
        Collections.sort(strings);
        Collections.sort(nums);
        Collections.reverse(nums);
        ArrayList<String> result = new ArrayList<>();
        int i = 0, j = 0;
        for (String lol:array
             ) {
            if (isNumber(lol) && i < nums.size()) {
                result.add(nums.get(i));
                i++;
            } else if (j < strings.size()) {
                result.add(strings.get(j));
                j++;
            }
        }
        int k = 0;
        for (String lol:result
             ) {
            array[k] = lol;
            k++;
        }


    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String text) {
        if (text.length() == 0) {
            return false;
        }

        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];

            // есть '-' внутри строки
            if (i != 0 && character == '-') {
                return false;
            }

            // не цифра и не начинается с '-'
            if (!Character.isDigit(character) && character != '-') {
                return false;
            }

            // одиночный '-'
            if (chars.length == 1 && character == '-') {
                return false;
            }
        }

        return true;
    }
}
