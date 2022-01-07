package com.javarush.task.pro.task13.task1317;

/* 
Трансформируем enum в класс
*/

import java.util.ArrayList;
import java.util.Collections;

public class Month {
    private int index;
    private static Month[] values = new Month[12];
    public static final Month JANUARY = new Month(0);
    public static final Month FEBRUARY = new Month(1);
    public static final Month MARCH = new Month(2);
    public static final Month APRIL = new Month(3);
    public static final Month MAY = new Month(4);
    public static final Month JUNE = new Month(5);
    public static final Month JULY = new Month(6);
    public static final Month AUGUST = new Month(7);
    public static final Month SEPTEMBER = new Month(8);
    public static final Month OCTOBER = new Month(9);
    public static final Month NOVEMBER = new Month(10);
    public static final Month DECEMBER = new Month(11);

    private Month(int index) {
        this.index = index;
        values[index] = this;
    }

    public static Month[] values() {
        return values;
    }

    public int ordinal() {
        return this.index;
    }
}
