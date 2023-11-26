package com.javarush.task.pro.task09.task0906;

import java.util.regex.Pattern;

/* 
Двоичный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = Integer.MAX_VALUE;
        System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toBinary(decimalNumber));
        String binaryNumber = "01111111111111111111111111111111";
        System.out.println("Двоичное число " + binaryNumber + " равно десятичному числу " + toDecimal(binaryNumber));
    }

    public static String toBinary(int decimalNumber) {
        String result = "";
        if (decimalNumber > 0) {
            StringBuilder builder = new StringBuilder();
            while (decimalNumber != 0) {
                builder.append(decimalNumber % 2);
                decimalNumber /= 2;
            }
            builder.reverse();
            result = builder.toString();
        }
        return result;
    }

    public static int toDecimal(String binaryNumber) {
        int decimal = 0;
        if (binaryNumber != null) {
            for (int i = 0; i < binaryNumber.length(); i++) {
                int current = Integer.parseInt(binaryNumber.charAt(binaryNumber.length() - i - 1) + "");
                decimal += current * (int)Math.pow(2, i);
            }
        }

        return decimal;
    }
}
