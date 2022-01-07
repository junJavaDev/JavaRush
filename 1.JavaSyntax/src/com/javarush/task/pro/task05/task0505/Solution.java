package com.javarush.task.pro.task05.task0505;

import java.util.Scanner;

/* 
Reverse
*/

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int N = in.nextInt();
        if (N > 0) {
            int[] nArray = new int[N];
            for (int i = 0; i < nArray.length; i++) {
                nArray[i] = in.nextInt();
            }
            if (N % 2 == 1) {
                for (int i = 0; i < nArray.length; i++) {
                    System.out.println(nArray[i]);
                }
            }
            else for (int i = nArray.length - 1; i >= 0; i--) {
                System.out.println(nArray[i]);
            }

        }
    }
}
