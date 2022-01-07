package com.javarush.task.pro.task13.task1323;

import java.util.ArrayList;
import java.util.HashSet;

import static java.util.Arrays.asList;

/* 
task1323
*/

public class Solution {
    public static HashSet<String> listToSet(ArrayList<String> listOfWords) {
        return new HashSet<>(listOfWords);
    }

    public static void main(String[] args) {
        ArrayList<String> listOfWords = new ArrayList<>(asList(
                "Программы на Java транслируются в байт-код Java выполняемый виртуальной машиной Java (JVM) — программой обрабатывающей байт-код и передающей инструкции оборудованию "
                .split(" ")));
        System.out.println("Количество слов в списке: " + listOfWords.size());
        HashSet<String> setOfWords = listToSet(listOfWords);
        System.out.println("Количество слов во множестве: " + setOfWords.size());
    }
}