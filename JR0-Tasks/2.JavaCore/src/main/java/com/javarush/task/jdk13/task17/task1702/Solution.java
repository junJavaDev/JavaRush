package com.javarush.task.jdk13.task17.task1702;

import java.util.ArrayList;
import java.util.List;

/* 
Вместе быстрее? Ща проверим :)
*/

public class Solution {
    public static int threadCount = 10; // Количество нитей
    public static int[] testArray = new int[1000]; // Тестовый массив на 1000 значений

    static {
        for (int i = 0; i < Solution.testArray.length; i++) {
            testArray[i] = i; // Заполнение тестового массива числами, индекс элемента = значение
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StringBuilder expectedResult = new StringBuilder(); // Ожидаемый строка результата
        for (int i = 1000 - 1; i >= 0; i--) {
            expectedResult.append(i).append(" "); // Заполнение ожидаемой строки результата числами от 999 до 0 через пробел
        }

        initThreads(); // Создание и инициализация нитей, ожидание пока завершатся все нити

        StringBuffer result = new StringBuffer(); // Реальная строка результата
        for (int i : testArray) {
            result.append(i).append(" "); // Заполнение реальной строки результата числами из массива testArray через пробел
        }
        System.out.println(result);
        System.out.println(expectedResult);
        System.out.println((result.toString()).equals(expectedResult.toString()));
    }

    public static void initThreads() throws InterruptedException {
        List<Thread> threads = new ArrayList<>(threadCount); // создание списка нитей с initialCapacity = 10
        for (int i = 0; i < threadCount; i++) {
            threads.add(new SortThread()); // Добавление нитей - сортировщиков в список нитей
        }
        for (Thread thread : threads) {
            thread.start(); // Старт всех нитей
        }
        for (Thread thread : threads) {
            thread.join(); // Ожидание тредом, в котором вызывается initThreads пока завершатся все нити
        }
    }

    public static void sort(int[] array) { // Пузырёк
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int k = array[i];
                    array[i] = array[j];
                    array[j] = k;
                }
            }
        }
    }

    public static class SortThread extends Thread {
        @Override
        public void run() {
            sort(testArray);
        }
    }
}

