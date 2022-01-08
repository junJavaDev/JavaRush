package com.javarush.task.pro.task13.task1325;

import java.util.*;

/* 
task1325
*/

public class Solution {

    public static void main(String[] args) {
        TreeMap<String, String> phoneBook = new TreeMap<>();

        phoneBook.put("Войтиков Дмитрий", "555-55-66");
        phoneBook.put("Бочкарев Петр", "950-44-66");
        phoneBook.put("Власов Василий", "554-55-66");
        phoneBook.put("Александров Алексей", "505-22-66");
        phoneBook.put("Виноградов Максим", "550-55-33");
        phoneBook.put("Андреев Андрей", "555-555-555");
        phoneBook.put("Громов Евгений", "558-20-66");
        phoneBook.put("Брагин Иван", "455-65-66");
        phoneBook.put("Алексеев Александр", "500-87-66");
        phoneBook.put("Глазов Виктор", "355-59-86");

        TreeMap<String, String> contactsByFirstLetter = getContactsStartsWith(phoneBook, "Ал");

        if (contactsByFirstLetter.isEmpty()) {
            System.out.println("Контакты не найдены.");
        } else {
            for (Map.Entry<String, String> entry : contactsByFirstLetter.entrySet()) {
                System.out.printf("%s, %s%n", entry.getKey(), entry.getValue());
            }
        }
    }

    public static TreeMap<String, String> getContactsStartsWith(TreeMap<String, String> map, String startsWith) {
        TreeMap<String, String> result = new TreeMap<>();
        for (String s : map.keySet()) {
            if (s.startsWith(startsWith)) {
                result.put(s, map.get(s));
            }
        }
        return result;
    }
}