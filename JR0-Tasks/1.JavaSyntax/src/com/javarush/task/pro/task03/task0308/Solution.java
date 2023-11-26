package com.javarush.task.pro.task03.task0308;

import java.util.Scanner;

/* 
Координатные четверти
*/

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int quarter;
        int x = in.nextInt(), y = in.nextInt();
        if (x > 0) {
            if (y > 0) {
                quarter = 1;
            }
            else quarter = 4;
        }
        else if (y > 0) quarter = 2;
        else quarter = 3;

        System.out.println(quarter);
    }
}
