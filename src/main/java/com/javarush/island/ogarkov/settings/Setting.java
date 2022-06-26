package com.javarush.island.ogarkov.settings;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Setting {
    // TODO: 26.06.2022 добавить загрузку из yaml
    //=============================== BASIC =======================================
    public static final String SIMULATION_NAME = "Island Simulation by Ogarkov";
    public static final String ENTITY_PATH = "com.javarush.island.ogarkov.entity";
    public static final int INITIAL_DELAY = 1000;
    public static final int MAIN_DELAY = 350; // simulation speed, 100 to debug
    public static final int UPDATE_DELAY = 30;
    public static final int ISLAND_ROWS = 20;
    public static final int ISLAND_COLS = 100;
    public static final int TERRITORY_ROWS = 5;
    public static final int TERRITORY_COLS = 5;
    //=============================== /BASIC =======================================

    //=============================== INIT =========================================
    public static final int CARNIVORE_INIT_PER_CELL = 3;
    public static final int HERBIVORE_INIT_PER_CELL = 15;
    public static final int PLANT_INIT_PER_CELL = 50;
    public static final int CELL_CARNIVORE_PROBABILITY = 1;
    public static final int CELL_HERBIVORE_PROBABILITY = 15;
    public static final int CELL_PLANT_PROBABILITY = 150;
    public static final double INIT_SATIETY = 0.5;
    public static final double HUNGER = 0.8;
    public static final int LOSING_WEIGHT_PERCENT = 5;
    public static final int CARNIVORE_CHANCE_TO_REPRODUCE = 25;
    public static final int HERBIVORE_CHANCE_TO_REPRODUCE = 80;
    public static final int PLANT_CHANCE_TO_REPRODUCE = 30;
    public static final int PLANT_REPRODUCED_PER_EMPTY_CELL = 14;
    public static final int CARNIVORE_LIFE_LENGTH = 30;
    public static final int HERBIVORE_LIFE_LENGTH = 60;
    public static final int PLANT_LIFE_LENGTH = 60;
    public static final int LANDFORM_LIFE_LENGTH = Integer.MAX_VALUE;
    public static final int TRYING_LOCK_MILLIS = 5;
    public static final String EMPTY_STRING = "";
    //=============================== /INIT ========================================

    //=============================== FOOD_RATION ==================================
    public static final String[][][] FOOD_RATION = {
            {{"BEAR"}, {"boa:80", "horse:40", "deer:80", "rabbit:80", "mouse:90", "goat:70", "sheep:70", "boar:50", "buffalo:20", "duck:10"}},
            {{"BOA"}, {"fox:15", "rabbit:20", "mouse:40", "duck:10"}},
            {{"EAGLE"}, {"fox:10", "rabbit:90", "mouse:90", "duck:80"}},
            {{"FOX"}, {"rabbit:70", "mouse:90", "duck:60", "caterpillar:40"}},
            {{"WOLF"}, {"horse:10", "deer:15", "rabbit:60", "mouse:80", "goat:60", "sheep:70", "boar:15", "buffalo:10", "duck:40"}},
            {{"BOAR"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100", "mouse:50", "caterpillar:90"}},
            {{"BUFFALO"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100"}},
            {{"CATERPILLAR"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100"}},
            {{"DEER"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100"}},
            {{"DUCK"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100", "caterpillar:90"}},
            {{"GOAT"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100"}},
            {{"HORSE"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100"}},
            {{"MOUSE"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100", "caterpillar:90"}},
            {{"RABBIT"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100"}},
            {{"SHEEP"}, {"bush:100", "dandelion:100", "flower:100", "grass:100", "sprout:100", "tree:100"}},
    };
    //=============================== /FOOD_RATION =================================

    //=============================== VIEW =========================================
    public static final int ISLAND_CELL_WIDTH = 35;
    public static final int ISLAND_CELL_HEIGHT = 35;
    public static final int TERRITORY_CELL_WIDTH = 37;
    public static final int TERRITORY_CELL_HEIGHT = 55;
    public static final int ISLAND_GRID_SIZE = 12;
    public static final int TERRITORY_GRID_SIZE = 2;
    public static final Color DEFAULT_ISLAND_COLOR = Color.OLIVEDRAB;
    public static final Color DEFAULT_TERRITORY_COLOR = Color.LIGHTGRAY;
    public static final Color SELECTED_COLOR = Color.RED;
    public static final Map<Items, Color> ISLAND_COLORS = new HashMap<>();
    static {
        ISLAND_COLORS.put(Items.BEAR, Color.BROWN);
        ISLAND_COLORS.put(Items.WOLF, Color.BLACK);
        ISLAND_COLORS.put(Items.EAGLE, Color.BLACK);
        ISLAND_COLORS.put(Items.BOA, Color.BLACK);
        ISLAND_COLORS.put(Items.FOX, Color.BLACK);
    }
    //=============================== /VIEW ========================================
}
