package com.javarush.task.pro.task03.task0312;

import java.util.Scanner;

/* 
Сравним строки
*/

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isEquals = in.nextLine().equals(in.nextLine());
        if (isEquals)
        System.out.println("строки одинаковые");
        else
        System.out.println("строки разные");
    }
}
