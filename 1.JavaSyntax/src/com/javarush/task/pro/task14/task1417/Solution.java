package com.javarush.task.pro.task14.task1417;

/* 
task1417
*/

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введи дату в формате dd.mm.yyyy : ");
        String date = scanner.nextLine();
        validateDate(date);
        System.out.println("\nДата корректна.");
    }

    public static void validateDate(String date) {
        checkDateFormat(date);
        checkYearValue(date);
        checkMonthValue(date);
        checkDayValue(date);
    }

    public static void checkDateFormat(String date) {
        if (!date.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new InvalidDateFormatException();
        }
//        ЭТО ДИЧЬ КАКАЯ-ТО, НУЖНО СРОЧНО ИЗУЧИТЬ РЕГУЛЯРКИ
//        StringTokenizer stringTokenizer = new StringTokenizer(date,".");
//        try {
//            String day = stringTokenizer.nextToken();
//            String month = stringTokenizer.nextToken();
//            String year = stringTokenizer.nextToken();
//            if (day.length() != 2 || month.length() != 2 || year.length() != 4) throw new InvalidDateFormatException();
//            Integer.parseInt(day);
//            Integer.parseInt(month);
//            Integer.parseInt(year);
//        }
//        catch (Exception e) {
//            throw new InvalidDateFormatException();
//        }

    }
    public static void checkYearValue(String date) {
        int year = Integer.parseInt(date.substring(6));
        if (year < 1900 || year > 2100) throw new InvalidYearValueException();
    }

    public static void checkMonthValue(String date) {
        int month = Integer.parseInt(date.substring(3, 5));
        if (month < 1 || month > 12) throw new InvalidMonthValueException();
    }

    public static void checkDayValue(String date) {
        HashMap<Integer, Integer> months = new HashMap<>(){{
            put(1, 31);
            put(2, 29);
            put(3, 31);
            put(4, 30);
            put(5, 31);
            put(6, 30);
            put(7, 31);
            put(8, 31);
            put(9, 30);
            put(10, 31);
            put(11, 30);
            put(12, 31);
        }};
        StringTokenizer stringTokenizer = new StringTokenizer(date,".");
        int day = Integer.parseInt(stringTokenizer.nextToken());
        int month = Integer.parseInt(stringTokenizer.nextToken());
        int daysLimit = months.get(month);
        if (day < 1 || day > daysLimit) throw new InvalidDayValueException();
    }
}
