package com.javarush.task.pro.task09.task0905;

import java.util.regex.Pattern;

/* 
Восьмеричный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = 21;
        System.out.println("Десятичное число " + decimalNumber + " равно восьмеричному числу " + toOctal(decimalNumber));
        int octalNumber = 25;
        System.out.println("Восьмеричное число " + octalNumber + " равно десятичному числу " + toDecimal(octalNumber));
    }

    public static int toOctal(int decimalNumber) {
        int octalNumber = 0;
        if (decimalNumber > 0) {
            int pow = 0;
            while (decimalNumber != 0) {
                octalNumber = octalNumber + (decimalNumber % 8) * (int) Math.pow(10, pow);
                decimalNumber /= 8;
                pow++;
            }
        }
        return octalNumber;
    }

    public static int toDecimal(int octalNumber) {
        int decimalNumber = 0;
        if (octalNumber > 0) {
            int pow = 0;
            while (octalNumber != 0) {
                decimalNumber = decimalNumber + (octalNumber % 10) * (int) Math.pow(8, pow);
                octalNumber /= 10;
                pow++;
            }
        }
        return decimalNumber;
    }
}
