package com.javarush.task.pro.task04.task0407;

/* 
Сумма чисел, не кратных 3
*/

public class Solution {
    public static void main(String[] args) {
        int i = 1;
        int summ = 0;
        while (i <= 100) {
            if (i % 3 != 0) {
                summ += i;
                i++;

            }
            else i++;
            continue;
        }
        System.out.println(summ);
    }
}