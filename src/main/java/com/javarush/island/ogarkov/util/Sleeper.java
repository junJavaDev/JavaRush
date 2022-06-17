package com.javarush.island.ogarkov.util;

import com.javarush.island.ogarkov.exception.IslandException;

public class Sleeper {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }
}
