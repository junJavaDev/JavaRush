package com.javarush.task.jdk13.task15.task1510;

/* 
Статики и исключения
*/

public class Solution {
    public static int A = 0;

    static {
        if (A == 0) {
            throw new RuntimeException("");
        }
    }

    public static int B = 5;

    public static void main(String[] args) {
        System.out.println(B);
    }
}
