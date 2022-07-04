package ru.javarush.island.ogarkov.settings;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Default {
        // This class is used by reflection in class Setting, method loadFromDefault()
        //==================================== BASIC ==============================================
        public static final String SIMULATION_NAME = "Island Simulation by Ogarkov";
        public static final String ENTITY_PATH = "ru.javarush.island.ogarkov.entity";
        public static final Image ALIVE_ICON = new Image(String.valueOf(Setting.class.getResource("/ogarkov/icons/statistics/alive.png")));
        public static final Image DEAD_ICON = new Image(String.valueOf(Setting.class.getResource("/ogarkov/icons/statistics/dead.png")));
        public static final int ISLAND_ROWS = 20;
        public static final int ISLAND_COLS = 30;
        public static final int TERRITORY_ROWS = 5;
        public static final int TERRITORY_COLS = 5;
        public static final int INITIAL_DELAY = 1000;
        public static final int MAIN_DELAY = 350;
        public static final int TRYING_LOCK_MILLIS = 5;
        //==================================== /BASIC =============================================

        //=============================== ORGANISM BEHAVIOUR ======================================
        public static final int CARNIVORE_INIT_PER_CELL = 4;
        public static final int HERBIVORE_INIT_PER_CELL = 30;
        public static final int PLANT_INIT_PER_CELL = 30;
        public static final int PLANT_SPAWNED_PER_EMPTY_CELL = 35;
        public static final int CELL_CARNIVORE_PROBABILITY = 1;
        public static final int CELL_HERBIVORE_PROBABILITY = 20;
        public static final int CELL_PLANT_PROBABILITY = 120;
        public static final double INIT_SATIETY = 0.5;
        public static final double HERBIVORE_HUNGER = 0.95;
        public static final double CARNIVORE_HUNGER = 0.7;
        public static final double INIT_WEIGHT = 0.6;
        public static final double LOSING_WEIGHT_PERCENT = 0.035;
        public static final double CARNIVORE_WEIGHT_TO_REPRODUCE = 0.65;
        public static final double HERBIVORE_WEIGHT_TO_REPRODUCE = 0.75;
        public static final int LOWER_CHANCE_TO_REPRODUCE = 18;
        public static final int HIGHER_CHANCE_TO_REPRODUCE = 90;
        public static final int CARNIVORE_CHANCE_TO_REPRODUCE = 22;
        public static final int HERBIVORE_CHANCE_TO_REPRODUCE = 75;
        public static final int PLANT_CHANCE_TO_REPRODUCE = 30;
        public static final int CARNIVORE_LIFE_LENGTH = 25;
        public static final int HERBIVORE_LIFE_LENGTH = 25;
        public static final int PLANT_LIFE_LENGTH = 2000;
        public static final int LANDFORM_LIFE_LENGTH = Integer.MAX_VALUE;
        public static final String[][][] FOOD_RATION = new String[][][]{};
        //=============================== /ORGANISM BEHAVIOUR ========================================

        //======================================= VIEW ===============================================
        public static final int STATISTICS_LINE_SPACING = 6;
        public static final int ISLAND_CELL_WIDTH = 35;
        public static final int ISLAND_CELL_HEIGHT = 35;
        public static final int TERRITORY_CELL_WIDTH = 35;
        public static final int TERRITORY_CELL_HEIGHT = 50;
        public static final int STATISTICS_LINE_HEIGHT = 14;
        public static final int STATISTICS_FIELD_SPACING = 11;
        public static final int STATISTICS_FIELD_WIDTH = 178;
        public static final int STATISTICS_ICON_SIZE = 24;
        public static final int ISLAND_GRID_SIZE = 12;
        public static final int TERRITORY_GRID_SIZE = 2;
        public static final int DIAGRAM_MAX_PLANTS = 5000;
        public static final int DIAGRAM_MAX_ANIMALS = 1000;
        public static final Color DEFAULT_ISLAND_COLOR = Color.OLIVEDRAB;
        public static final Color DEFAULT_TERRITORY_COLOR = Color.LIGHTGRAY;
        public static final Color SELECTED_COLOR = Color.DARKGREEN;
        public static final Color DIAGRAM_MAX_COLOR = Color.rgb(137, 202, 120, 0.6);
        public static final Color DIAGRAM_MIDDLE_COLOR = Color.rgb(229, 192, 123, 0.6);
        public static final Color DIAGRAM_MIN_COLOR = Color.rgb(239, 89, 11, 0.6);
        public static final Color STATISTICS_COLOR = Color.LIGHTGRAY;
        //====================================== /VIEW ===============================================
}
