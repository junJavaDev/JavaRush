package com.javarush.task.pro.task04.task0416;

import java.util.Scanner;

/* 
Share a Coke
*/

public class Solution {
    public static void main(String[] args) {
       Scanner in = new Scanner (System.in);
       int colas = in.nextInt();
       int programmers = in.nextInt();
       double colaPerProger = colas * 1.0 / programmers;
        System.out.println(colaPerProger);

    }
}