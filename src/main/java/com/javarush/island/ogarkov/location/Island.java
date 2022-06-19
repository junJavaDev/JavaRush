package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.Controller;

import static com.javarush.island.ogarkov.settings.Setting.*;

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

    public void reload() {
        for (int x = 0; x < ISLAND_ROWS; x++) {
            for (int y = 0; y < ISLAND_COLS; y++) {
                territories[x][y].updateSortedCells();
                territories[x][y].addMouseClickedAction();
                Cell leader = territories[x][y].getLeader();
                leader.addIslandGrid(x, y, ISLAND_GRID_SIZE);
                leader.setCellImage(leader.getResident().getIcon());
                leader.setIslandCellColor();
            }
        }
    }



    public Territory[][] getTerritories () {
        return territories;
    }

    public void setTerritory (int row, int col, Territory territory) {
        territories[row][col] = territory;
    }
}
