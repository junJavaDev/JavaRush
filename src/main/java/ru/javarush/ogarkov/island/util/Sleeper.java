package ru.javarush.ogarkov.island.util;

import ru.javarush.ogarkov.island.exception.IslandException;

public class Sleeper {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }
}
