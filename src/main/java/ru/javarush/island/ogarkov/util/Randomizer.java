package ru.javarush.island.ogarkov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private Randomizer() {
    }

    public static int getInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static int getIntOriginOne(int bound) {
        return ThreadLocalRandom.current().nextInt(1, bound);
    }

    public static int getInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
