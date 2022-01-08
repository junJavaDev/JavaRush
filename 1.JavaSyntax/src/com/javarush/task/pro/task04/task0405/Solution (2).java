package com.javarush.task.pro.task04.task0405;

/* 
Незаполненный прямоугольник
*/

public class Solution {
    public static void main(String[] args) {
        int hight = 10;
        while (hight > 0) {
            int weight = 20;
            while (weight > 0) {
                if (hight == 1 || hight == 10 || weight == 1 || weight == 20) {
                    System.out.print("Б");
                }
                else System.out.print(" ");
                weight--;
            }
            System.out.println();
            hight--;
        }

    }
}