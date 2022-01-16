package com.javarush.task.pro.task16.task1604;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* 
День недели рождения твоего
*/

public class Solution {

    static Calendar birthDate = new GregorianCalendar(1990, Calendar.OCTOBER, 3);


    public static void main(String[] args) {
        System.out.println(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Calendar calendar) {
//      Не принимается валидатором, даже когда с большой буквы :(
//      SimpleDateFormat format = new SimpleDateFormat("EEEE");
//      String day = format.format(calendar.getTime());
//      return day.replace(day.substring(0, 1), day.substring(0, 1).toUpperCase());
        String[] daysOfWeek = new String[]{
                "Воскресенье",
                "Понедельник",
                "Вторник",
                "Среда",
                "Четверг",
                "Пятница",
                "Суббота"
        };
        return daysOfWeek[calendar.getTime().getDay()];
    }
}
