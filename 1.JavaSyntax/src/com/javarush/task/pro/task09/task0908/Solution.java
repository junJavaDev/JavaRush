package com.javarush.task.pro.task09.task0908;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/* 
Двоично-шестнадцатеричный конвертер
*/

public class Solution {
    public static String[][] hexBinary = {
            {"0000", "0"},
            {"0001", "1"},
            {"0010", "2"},
            {"0011", "3"},
            {"0100", "4"},
            {"0101", "5"},
            {"0110", "6"},
            {"0111", "7"},
            {"1000", "8"},
            {"1001", "9"},
            {"1010", "a"},
            {"1011", "b"},
            {"1100", "c"},
            {"1101", "d"},
            {"1110", "e"},
            {"1111", "f"}
    };
    public static void main(String[] args) {
        String binaryNumber = "110100111010000";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "69d0";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        String result = "";

        if (binaryNumber != null && binaryNumber.replaceAll("[01]", "").equals("")) {
            StringBuilder builder = new StringBuilder();
            int remainder = binaryNumber.length() % 4;
            if (remainder != 0 ) {
                binaryNumber = "0".repeat(4 - remainder) + binaryNumber;
            }
                int parts = binaryNumber.length() / 4;
                String[] symbols = new String[parts];
                int cursor = 0;
                for (int i = 0; i < parts; i++) {
                    symbols[i] = binaryNumber.substring(cursor, cursor + 4);
                    cursor += 4;
                }
                for (String symbol : symbols) {
                    for (int j = 0; j < 16; j++) {
                        if (symbol.equals(hexBinary[j][0])) {
                            builder.append(hexBinary[j][1]);
                        }
                    }
                }
                result = builder.toString();
            }
        return result;
        }

    public static String toBinary(String hexNumber) {
        String result = "";
        if (hexNumber != null && hexNumber.replaceAll("[0-f]", "").equals("")) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < hexNumber.length(); i++) {
                for (int j = 0; j < 16; j++) {
                    if (hexBinary[j][1].equals(hexNumber.charAt(i) + "")) {
                        builder.append(hexBinary[j][0]);
                    }
                }
            }
            result = builder.toString();
        }
        return result;
    }
}
