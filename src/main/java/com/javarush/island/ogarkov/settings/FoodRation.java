package com.javarush.island.ogarkov.settings;

import java.util.HashMap;
import java.util.Map;

public class FoodRation {

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
        HERBIVORE_FOOD.put(Items.BUSH, 100);
        HERBIVORE_FOOD.put(Items.DANDELION, 100);
        HERBIVORE_FOOD.put(Items.FLOWER, 100);
        HERBIVORE_FOOD.put(Items.GRASS, 100);
        HERBIVORE_FOOD.put(Items.SPROUT, 100);

        WOLF_FOOD.put(Items.HORSE, 10);
        WOLF_FOOD.put(Items.DEER, 15);
        WOLF_FOOD.put(Items.RABBIT, 60);
        WOLF_FOOD.put(Items.MOUSE, 80);
        WOLF_FOOD.put(Items.GOAT, 60);
        WOLF_FOOD.put(Items.SHEEP, 70);
        WOLF_FOOD.put(Items.BOAR, 15);
        WOLF_FOOD.put(Items.BUFFALO, 10);
        WOLF_FOOD.put(Items.DUCK, 40);

        BOA_FOOD.put(Items.FOX, 15);
        BOA_FOOD.put(Items.RABBIT, 20);
        BOA_FOOD.put(Items.MOUSE, 40);
        BOA_FOOD.put(Items.DUCK, 10);

        FOX_FOOD.put(Items.RABBIT, 70);
        FOX_FOOD.put(Items.MOUSE, 90);
        FOX_FOOD.put(Items.DUCK, 60);
        FOX_FOOD.put(Items.CATERPILLAR, 40);

        BEAR_FOOD.put(Items.BOA, 80);
        BEAR_FOOD.put(Items.HORSE, 40);
        BEAR_FOOD.put(Items.DEER, 80);
        BEAR_FOOD.put(Items.RABBIT, 80);
        BEAR_FOOD.put(Items.MOUSE, 90);
        BEAR_FOOD.put(Items.GOAT, 70);
        BEAR_FOOD.put(Items.SHEEP, 70);
        BEAR_FOOD.put(Items.BOAR, 50);
        BEAR_FOOD.put(Items.BUFFALO, 20);
        BEAR_FOOD.put(Items.DUCK, 10);

        EAGLE_FOOD.put(Items.FOX, 10);
        EAGLE_FOOD.put(Items.RABBIT, 90);
        EAGLE_FOOD.put(Items.MOUSE, 90);
        EAGLE_FOOD.put(Items.DUCK, 80);

        MOUSE_FOOD.putAll(HERBIVORE_FOOD);
        MOUSE_FOOD.put(Items.CATERPILLAR, 90);

        BOAR_FOOD.putAll(HERBIVORE_FOOD);
        BOAR_FOOD.put(Items.MOUSE, 50);
        BOAR_FOOD.put(Items.CATERPILLAR, 90);

        DUCK_FOOD.putAll(HERBIVORE_FOOD);
        DUCK_FOOD.put(Items.CATERPILLAR, 90);
    }
}
