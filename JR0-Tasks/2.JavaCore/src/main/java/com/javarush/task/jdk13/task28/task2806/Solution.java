package com.javarush.task.jdk13.task28.task2806;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* 
Параллельный парсинг
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            lines.add(scanner.nextLine());
        }

        List<FutureTask<Link>> tasks = new ArrayList<>();
        for (String line : lines) {
            ParseLinkTask parseLinkTask = new ParseLinkTask(line);
            FutureTask<Link> futureTask = new FutureTask<>(parseLinkTask);
            Thread thread = new Thread(futureTask);
            tasks.add(futureTask);
            thread.start();
        }

        for (Future<Link> future : tasks) {
            System.out.println(future.get());
        }
    }
}