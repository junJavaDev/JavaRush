package ru.javarush.ogarkov.islandsimulation.settings;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    public static int getRandomInt(int bound){
        return random.nextInt(bound);
    }
}
