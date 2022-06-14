package ru.javarush.ogarkov.island.settings;

import java.util.HashMap;
import java.util.Map;

import static ru.javarush.ogarkov.island.settings.Items.*;

public class EatingProbability {

    public static final Map<Items, Integer> HERBIVORE_FOOD = new HashMap<>();
    public static final Map<Items, Integer> WOLF_FOOD = new HashMap<>();
    public static final Map<Items, Integer> BOA_FOOD = new HashMap<>();
    public static final Map<Items, Integer> FOX_FOOD = new HashMap<>();
    public static final Map<Items, Integer> BEAR_FOOD = new HashMap<>();
    public static final Map<Items, Integer> EAGLE_FOOD = new HashMap<>();
    public static final Map<Items, Integer> MOUSE_FOOD = new HashMap<>();
    public static final Map<Items, Integer> BOAR_FOOD = new HashMap<>();
    public static final Map<Items, Integer> DUCK_FOOD = new HashMap<>();

    public static void init() {
        HERBIVORE_FOOD.put(BUSH, 100);
        HERBIVORE_FOOD.put(DANDELION, 100);
        HERBIVORE_FOOD.put(FLOWER, 100);
        HERBIVORE_FOOD.put(GRASS, 100);
        HERBIVORE_FOOD.put(SPROUT, 100);

        WOLF_FOOD.put(HORSE, 10);
        WOLF_FOOD.put(DEER, 15);
        WOLF_FOOD.put(RABBIT, 60);
        WOLF_FOOD.put(MOUSE, 80);
        WOLF_FOOD.put(GOAT, 60);
        WOLF_FOOD.put(SHEEP, 70);
        WOLF_FOOD.put(BOAR, 15);
        WOLF_FOOD.put(BUFFALO, 10);
        WOLF_FOOD.put(DUCK, 40);

        BOA_FOOD.put(FOX, 15);
        BOA_FOOD.put(RABBIT, 20);
        BOA_FOOD.put(MOUSE, 40);
        BOA_FOOD.put(DUCK, 10);

        FOX_FOOD.put(RABBIT, 70);
        FOX_FOOD.put(MOUSE, 90);
        FOX_FOOD.put(DUCK, 60);
        FOX_FOOD.put(CATERPILLAR, 40);

        BEAR_FOOD.put(BOA, 80);
        BEAR_FOOD.put(HORSE, 40);
        BEAR_FOOD.put(DEER, 80);
        BEAR_FOOD.put(RABBIT, 80);
        BEAR_FOOD.put(MOUSE, 90);
        BEAR_FOOD.put(GOAT, 70);
        BEAR_FOOD.put(SHEEP, 70);
        BEAR_FOOD.put(BOAR, 50);
        BEAR_FOOD.put(BUFFALO, 20);
        BEAR_FOOD.put(DUCK, 10);

        EAGLE_FOOD.put(FOX, 10);
        EAGLE_FOOD.put(RABBIT, 90);
        EAGLE_FOOD.put(MOUSE, 90);
        EAGLE_FOOD.put(DUCK, 80);

        MOUSE_FOOD.putAll(HERBIVORE_FOOD);
        MOUSE_FOOD.put(CATERPILLAR, 90);

        BOAR_FOOD.putAll(HERBIVORE_FOOD);
        BOAR_FOOD.put(MOUSE, 50);
        BOAR_FOOD.put(CATERPILLAR, 90);

        DUCK_FOOD.putAll(HERBIVORE_FOOD);
        DUCK_FOOD.put(CATERPILLAR, 90);
    }
}
