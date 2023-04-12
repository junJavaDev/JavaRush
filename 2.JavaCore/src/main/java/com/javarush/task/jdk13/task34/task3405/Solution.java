package com.javarush.task.jdk13.task34.task3405;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* 
Нарушение приватности
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        User user = new User("Amigo", 21);
        print(getFields(user));

        Task task = new Task("task3405", true);
        print(getFields(task));
    }

    public static Map<String, Object> getFields(Object object) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Field[] objectFields = object.getClass().getDeclaredFields();
        for (Field objectField : objectFields) {
            if (Modifier.isPrivate(objectField.getModifiers())) {
                objectField.setAccessible(true);
                result.put(objectField.getName(), objectField.get(object));
                objectField.setAccessible(false);
            }
        }
        return result;
    }

    public static void print(Map<?, ?> fields) {
        System.out.println("-------------------");
        fields.forEach((fieldName, fieldValue) -> System.out.println(fieldName + " = " + fieldValue));
    }
}
