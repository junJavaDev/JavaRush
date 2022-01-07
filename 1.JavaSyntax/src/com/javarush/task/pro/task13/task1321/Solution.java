package com.javarush.task.pro.task13.task1321;

public class Solution {

    public static void main(String[] args) {
        System.out.println(getTranslationForDayOfWeek("Вторник"));
        System.out.println(getTranslationForDayOfWeek("Пятница"));
        System.out.println(getTranslationForDayOfWeek("Высплюсенье"));
    }

    public static String getTranslationForDayOfWeek(String ru) {
        String en = "Недействительный день недели";
        if (ru.equals("понедельник")) en = "Monday";
        if (ru.equals("вторник")) en = "Tuesday";
        if (ru.equals("среда")) en = "Wednesday";
        if (ru.equals("четверг")) en = "Thursday";
        if (ru.equals("пятница")) en = "Friday";
        if (ru.equals("суббота")) en = "Saturday";
        if (ru.equals("воскресенье")) en = "Sunday";
        return en;

    }
}
