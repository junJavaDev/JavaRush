package com.javarush.task.pro.task09.task0906;

public class TempSolution {
    public static void main(String[] args) {
        int decimalNumber = 555444333;
        System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toBinary(decimalNumber));
        String binaryNumber = "100001000110110110100001101101";
        System.out.println("Двоичное число " + binaryNumber + " равно десятичному числу " + toDecimal(binaryNumber));
    }

    public static String toBinary(int decimalNumber) {
        //напишите тут ваш код
        String binaryNumber = "";
        if (decimalNumber <= 0) {
            return binaryNumber;
        }
        while (decimalNumber != 0) {
            binaryNumber = decimalNumber % 2 + binaryNumber;
            decimalNumber = decimalNumber / 2;
        }
        return binaryNumber;
    }

    public static int toDecimal(String binaryNumber) {
        //напишите тут ваш код
        int decimalNumber = 0;
        if (binaryNumber.equals("") || binaryNumber == null) {
            return decimalNumber;
        }
        for (int i = 0; i < binaryNumber.length(); i++) {
            decimalNumber = (int) (decimalNumber + Integer.parseInt(String.valueOf(binaryNumber.charAt(binaryNumber.length()-1-i))) * Math.pow(2, i));
        }
        return decimalNumber;
    }
}
