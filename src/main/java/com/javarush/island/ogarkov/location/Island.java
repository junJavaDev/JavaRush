package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.Controller;

// Остров, содержит массив локаций, отображает лидера каждой локации
public class Island {
    public static Island model;
    public static Controller controller;
    private final Territory[][] territories;

    public Island(int rows, int cols) {
        territories = new Territory[rows][cols];
    }

    public static void setController(Controller controller) {
        Island.controller = controller;
    }



    public Territory[][] getTerritories () {
        return territories;
    }

    public void setTerritory (int row, int col, Territory territory) {
        territories[row][col] = territory;
    }
}
