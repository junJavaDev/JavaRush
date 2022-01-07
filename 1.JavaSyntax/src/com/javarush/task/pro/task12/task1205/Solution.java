package com.javarush.task.pro.task12.task1205;

/* 
Метод деления
*/

public class Solution {

    public static void main(String[] args) {
        divide(-1, Double.NEGATIVE_INFINITY);
    }

    public static void divide(double a, double b) {
        System.out.println(a * b);
        System.out.println(a / b);
        System.out.println(a + b);
        System.out.println(a - b);
    }
}
