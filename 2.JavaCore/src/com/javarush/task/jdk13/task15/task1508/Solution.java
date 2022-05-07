package com.javarush.task.jdk13.task15.task1508;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<>();

    static {
        labels.put(3.14, "PI");
        labels.put(7.0, "Days of week");
        labels.put(31.4, "My Age");
        labels.put(1.23, "One and twenty three");
        labels.put(3.15, "Not a PI");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
