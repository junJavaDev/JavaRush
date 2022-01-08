package com.javarush.task.pro.task13.task1309;

import java.util.HashMap;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        grades.put("Вася1", 1.1);
        grades.put("Вася2", 2.2);
        grades.put("Вася3", 3.3);
        grades.put("Вася4", 4.4);
        grades.put("Вася5", 5.5);
    }
}
