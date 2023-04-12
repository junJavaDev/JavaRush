package com.javarush.task.jdk13.task12.task1211;

/* 
Int и Integer
*/

public class Solution {
    public static final int BAKERS_DOZEN = 13;
    public static void main(String[] args) {
        print(BAKERS_DOZEN);
        print(Integer.valueOf(BAKERS_DOZEN));
    }

    public static void print (int i) {
        System.out.println("int: " + i);
    }
    public static void print (Integer i) {
        System.out.println("Integer: " + i);
    }
}
