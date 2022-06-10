package ru.javarush.ogarkov.islandsimulation.settings;

import java.util.HashMap;
import java.util.Map;

import static ru.javarush.ogarkov.islandsimulation.settings.Items.*;

public class EatingProbability {

    public static void initialize() {
        System.out.println("in static method");
        new EatingProbability();
    }

    public static final Map<Items, Integer> WOLF_FOOD = new HashMap<>();

    {
        System.out.println("is NONstatic EatingProbability");
        WOLF_FOOD.put(HORSE, 10);
        WOLF_FOOD.put(DEER, 15);
        WOLF_FOOD.put(RABBIT, 60);
        WOLF_FOOD.put(MOUSE, 80);
        WOLF_FOOD.put(GOAT, 60);
        WOLF_FOOD.put(SHEEP, 70);
        WOLF_FOOD.put(BOAR, 15);
        WOLF_FOOD.put(BUFFALO, 10);
        WOLF_FOOD.put(DUCK, 40);
    }

    public static final Map<Items, Integer> BOA_FOOD = new HashMap<>();

    {
        BOA_FOOD.put(FOX, 15);
        BOA_FOOD.put(RABBIT, 20);
        BOA_FOOD.put(MOUSE, 40);
        BOA_FOOD.put(DUCK, 10);
    }

    public static final Map<Items, Integer> FOX_FOOD = new HashMap<>();

    {
        FOX_FOOD.put(RABBIT, 70);
        FOX_FOOD.put(MOUSE, 90);
        FOX_FOOD.put(DUCK, 60);
        FOX_FOOD.put(CATERPILLAR, 40);
    }

    public static final Map<Items, Integer> BEAR_FOOD = new HashMap<>();

    {
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
    }

    public static final Map<Items, Integer> EAGLE_FOOD = new HashMap<>();

    {
        EAGLE_FOOD.put(FOX, 10);
        EAGLE_FOOD.put(RABBIT, 90);
        EAGLE_FOOD.put(MOUSE, 90);
        EAGLE_FOOD.put(DUCK, 80);
    }

    public static final Map<Items, Integer> HERBIVORE_FOOD = new HashMap<>();

    {
        HERBIVORE_FOOD.put(BUSH, 100);
        HERBIVORE_FOOD.put(DANDELION, 100);
        HERBIVORE_FOOD.put(FLOWER, 100);
        HERBIVORE_FOOD.put(GRASS, 100);
        HERBIVORE_FOOD.put(SPROUT, 100);
    }

    public static final Map<Items, Integer> MOUSE_FOOD = new HashMap<>();

    {
        MOUSE_FOOD.putAll(HERBIVORE_FOOD);
        MOUSE_FOOD.put(CATERPILLAR, 90);
    }

    public static final Map<Items, Integer> BOAR_FOOD = new HashMap<>();

    {
        BOAR_FOOD.putAll(HERBIVORE_FOOD);
        BOAR_FOOD.put(MOUSE, 50);
        BOAR_FOOD.put(CATERPILLAR, 90);
    }

    public static final Map<Items, Integer> DUCK_FOOD = new HashMap<>();

    {
        DUCK_FOOD.putAll(HERBIVORE_FOOD);
        DUCK_FOOD.put(CATERPILLAR, 90);
    }



//    public static final Map<Class<? extends Animal>, Integer, Map<Class<? extends Animal>, Integer, Integer>> FOOD = new HashMap<>();
//    static {
//        FOOD.put(WOLF, WOLF_FOOD);
//        FOOD.put(BOA, BOA_FOOD);
//        FOOD.put(FOX, FOX_FOOD);
//        FOOD.put(BEAR, BEAR_FOOD);
//        FOOD.put(EAGLE, EAGLE_FOOD);
//        FOOD.put(HORSE, HERBIVORE_FOOD);
//        FOOD.put(DEER, HERBIVORE_FOOD);
//        FOOD.put(RABBIT, HERBIVORE_FOOD);
//        FOOD.put(MOUSE, MOUSE_FOOD);
//        FOOD.put(GOAT, HERBIVORE_FOOD);
//        FOOD.put(SHEEP, HERBIVORE_FOOD);
//        FOOD.put(BOAR, BOAR_FOOD);
//        FOOD.put(BUFFALO, HERBIVORE_FOOD);
//        FOOD.put(DUCK, DUCK_FOOD);
//        FOOD.put(CATERPILLAR, HERBIVORE_FOOD);
//
//    }
}
