package com.javarush.island.ogarkov.settings;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Setting {
    public static final String SIMULATION_NAME = "Island Simulation by Ogarkov";
    public static final int ISLAND_ROWS = 20;
    public static final int ISLAND_COLS = 100;
    public static final int TERRITORY_ROWS = 4;
    public static final int TERRITORY_COLS = 4;
    public static final int ISLAND_FORM_WIDTH = 1200;
    public static final int ISLAND_FORM_HEIGHT = 744;
    public static final int ISLAND_CELL_WIDTH = 35;
    public static final int ISLAND_CELL_HEIGHT = 35;
    public static final int TERRITORY_CELL_WIDTH = 35;
    public static final int TERRITORY_CELL_HEIGHT = 55;
    public static final int ISLAND_GRID_SIZE = 12;
    public static final int TERRITORY_GRID_SIZE = 2;
    public static final double INITIAL_SATIETY = 0.5;
    public static final double CARNIVORE_HUNGER = 0.8;
    public static final double HERBIVORE_HUNGER = 1;
    public static final int CARNIVORE_PER_CELL = 5;
    public static final int HERBIVORE_PER_CELL = 10;
    public static final int PLANT_PER_CELL = 50;
    public static final int LANDFORM_PER_CELL = 1;
    public static final int CARNIVORE_PROPABILITY = 50;
    public static final int HERBIVORE_PROPABILITY = 100;
    public static final int PLANT_PROPABILITY = 1000;
    public static final Color DEFAULT_ISLAND_COLOR = Color.OLIVEDRAB;
    public static final Color DEFAULT_TERRITORY_COLOR = Color.LIGHTGRAY;
    public static final Color SELECTED_COLOR = Color.RED;
    public static final String EMPTY_STRING = "";
    public static final Map<Items, Color> ISLAND_COLORS = new HashMap<>();
    static {
        ISLAND_COLORS.put(Items.BEAR, Color.BROWN);
        ISLAND_COLORS.put(Items.WOLF, Color.BLACK);
        ISLAND_COLORS.put(Items.EAGLE, Color.BLACK);
//        ISLAND_COLORS.put(Items.BOA, Color.BLACK);
//        ISLAND_COLORS.put(Items.FOX, Color.BLACK);
    }
}
