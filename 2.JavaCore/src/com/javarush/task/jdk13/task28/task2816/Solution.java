package com.javarush.task.jdk13.task28.task2816;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* 
Рекурсивный алфавит
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        //Создание пула потоков
        ExecutorService stealingPool = Executors.newWorkStealingPool();

        /* Загрузка в пул потоков первого задания - Старт.
        Возврат результата работы (пустая строка) в переменную future */

        Future<String> future = stealingPool.submit(() -> {
            System.out.println("старт");
            Thread.sleep(100);
            return "";
        });

        for (char c : alphabet.toCharArray()) {
            /* Загрузка в цикле в пул потоков нового задания (объект класса Task, имплементирует Callable).
            В параметры конструктора класса Task передается символ алфавита и объект Future.
            Результат присваивается в переменную future.
            Таким образом каждый раз future перезаписывается результатом работы метода call класса Task,
            а именно "future.get() + letter", т.е. в первый раз future будет содержать пустую строку + букву
            затем букву + букву, затем 2 буквы + букву и т.д., при этом метод call будет выводить это всё в консоль */
            future = stealingPool.submit(new Task(c, future));
        }

        stealingPool.awaitTermination(1, TimeUnit.SECONDS);
    }
}
