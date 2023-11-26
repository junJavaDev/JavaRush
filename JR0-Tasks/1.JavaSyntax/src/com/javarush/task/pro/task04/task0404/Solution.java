package com.javarush.task.pro.task04.task0404;

/* 
Заполненный прямоугольник
*/

public class Solution {
    public static void main(String[] args) {
        int hight = 5;
        while (hight > 0) {
            int width = 10;
            while (width > 0) {
                System.out.print("Q");
                width--;
            }
            System.out.println();
            hight--;
        }
    }
}