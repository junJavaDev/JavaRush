package ru.javarush.island.ogarkov.util;

import ru.javarush.island.ogarkov.exception.IslandException;

public class Sleeper {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }
}
