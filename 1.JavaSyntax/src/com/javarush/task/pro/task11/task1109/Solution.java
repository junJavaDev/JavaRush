package com.javarush.task.pro.task11.task1109;

/* 
Объекты внутренних и вложенных классов
*/

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static ArrayList<String>[] listString = new ArrayList[10];


    public static void main(String[] args) {
        ArrayList<ArrayList<String>> lol = new ArrayList<>();
        lol.add(new ArrayList<>());
        lol.get(0).add("df");
        ArrayList<String>[] arrayList = new ArrayList[12];
        ArrayList<Integer>[] array4 = new ArrayList[4];
        for (int i = 0; i < 10; i++) {
            listString[i] = new ArrayList<>();
        }
        System.out.println(listString);
    }


    }
