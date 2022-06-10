package ru.javarush.ogarkov.islandsimulation.settings;

import ru.javarush.ogarkov.islandsimulation.item.abstracts.Animal;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.BasicItem;
import ru.javarush.ogarkov.islandsimulation.item.fauna.*;
import ru.javarush.ogarkov.islandsimulation.item.flora.carnivore.*;
import ru.javarush.ogarkov.islandsimulation.item.flora.herbivore.*;

import java.util.HashMap;
import java.util.Map;

public class EatingProbability {

    private static final Map<Class<? extends BasicItem>, Integer> WOLF_FOOD = new HashMap<>();
    static {
        WOLF_FOOD.put(Horse.class, 10);
        WOLF_FOOD.put(Deer.class, 15);
        WOLF_FOOD.put(Rabbit.class, 60);
        WOLF_FOOD.put(Mouse.class, 80);
        WOLF_FOOD.put(Goat.class, 60);
        WOLF_FOOD.put(Sheep.class, 70);
        WOLF_FOOD.put(Boar.class, 15);
        WOLF_FOOD.put(Buffalo.class, 10);
        WOLF_FOOD.put(Duck.class, 40);
    }

    private static final Map<Class<? extends BasicItem>, Integer> BOA_FOOD = new HashMap<>();
    static {
        BOA_FOOD.put(Fox.class, 15);
        BOA_FOOD.put(Rabbit.class, 20);
        BOA_FOOD.put(Mouse.class, 40);
        BOA_FOOD.put(Duck.class, 10);
    }

    private static final Map<Class<? extends BasicItem>, Integer> FOX_FOOD = new HashMap<>();
    static {
        FOX_FOOD.put(Rabbit.class, 70);
        FOX_FOOD.put(Mouse.class, 90);
        FOX_FOOD.put(Duck.class, 60);
        FOX_FOOD.put(Caterpillar.class, 40);
    }

    private static final Map<Class<? extends BasicItem>, Integer> BEAR_FOOD = new HashMap<>();
    static {
        BEAR_FOOD.put(Boa.class, 80);
        BEAR_FOOD.put(Horse.class, 40);
        BEAR_FOOD.put(Deer.class, 80);
        BEAR_FOOD.put(Rabbit.class, 80);
        BEAR_FOOD.put(Mouse.class, 90);
        BEAR_FOOD.put(Goat.class, 70);
        BEAR_FOOD.put(Sheep.class, 70);
        BEAR_FOOD.put(Boar.class, 50);
        BEAR_FOOD.put(Buffalo.class, 20);
        BEAR_FOOD.put(Duck.class, 10);
    }

    private static final Map<Class<? extends BasicItem>, Integer> EAGLE_FOOD = new HashMap<>();
    static {
        EAGLE_FOOD.put(Fox.class, 10);
        EAGLE_FOOD.put(Rabbit.class, 90);
        EAGLE_FOOD.put(Mouse.class, 90);
        EAGLE_FOOD.put(Duck.class, 80);
    }

    private static final Map<Class<? extends BasicItem>, Integer> HERBIVORE_FOOD = new HashMap<>();
    static {
        HERBIVORE_FOOD.put(Bush.class, 100);
        HERBIVORE_FOOD.put(Dandelion.class, 100);
        HERBIVORE_FOOD.put(Flower.class, 100);
        HERBIVORE_FOOD.put(Grass.class, 100);
        HERBIVORE_FOOD.put(Sprout.class, 100);
    }

    private static final Map<Class<? extends BasicItem>, Integer> MOUSE_FOOD = new HashMap<>();
    static {
        MOUSE_FOOD.putAll(HERBIVORE_FOOD);
        MOUSE_FOOD.put(Caterpillar.class, 90);
    }

    private static final Map<Class<? extends BasicItem>, Integer> BOAR_FOOD = new HashMap<>();
    static {
        BOAR_FOOD.putAll(HERBIVORE_FOOD);
        BOAR_FOOD.put(Mouse.class, 50);
        BOAR_FOOD.put(Caterpillar.class, 90);
    }

    private static final Map<Class<? extends BasicItem>, Integer> DUCK_FOOD = new HashMap<>();
    static {
        DUCK_FOOD.putAll(HERBIVORE_FOOD);
        DUCK_FOOD.put(Caterpillar.class, 90);
    }

    public static final Map<Class<? extends BasicItem>, Map<Class<? extends BasicItem>, Integer>> FOOD = new HashMap<>();
    static {
        FOOD.put(Wolf.class, WOLF_FOOD);
        FOOD.put(Boa.class, BOA_FOOD);
        FOOD.put(Fox.class, FOX_FOOD);
        FOOD.put(Bear.class, BEAR_FOOD);
        FOOD.put(Eagle.class, EAGLE_FOOD);
        FOOD.put(Horse.class, HERBIVORE_FOOD);
        FOOD.put(Deer.class, HERBIVORE_FOOD);
        FOOD.put(Rabbit.class, HERBIVORE_FOOD);
        FOOD.put(Mouse.class, MOUSE_FOOD);
        FOOD.put(Goat.class, HERBIVORE_FOOD);
        FOOD.put(Sheep.class, HERBIVORE_FOOD);
        FOOD.put(Boar.class, BOAR_FOOD);
        FOOD.put(Buffalo.class, HERBIVORE_FOOD);
        FOOD.put(Duck.class, DUCK_FOOD);
        FOOD.put(Caterpillar.class, HERBIVORE_FOOD);

    }
}
