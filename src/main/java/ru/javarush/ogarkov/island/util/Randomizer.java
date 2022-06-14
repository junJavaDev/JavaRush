package ru.javarush.ogarkov.island.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private Randomizer() {
    }

    public static int getInt(int bound){
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
