package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.Controller;

import static com.javarush.island.ogarkov.settings.Setting.*;

// Остров, содержит массив локаций, отображает лидера каждой локации
public class Island {
    private Territory[][] territories;

    public Island(int rows, int cols) {
        territories = new Territory[rows][cols];
    }

    public void reload() {
        for (int x = 0; x < ISLAND_WIDTH; x++) {
            for (int y = 0; y < ISLAND_HEIGHT; y++) {
                Cell leader = territories[x][y].getLeader();
                leader.setCellImage(leader.getIcon());
                leader.addIslandGrid(x, y, ISLAND_GRID_SIZE);
//                leader.setIslandCellColor();
            }
        }
    }

    public static void resetIslandColor() {
        for (int x = 0; x < ISLAND_WIDTH; x++) {
            for (int y = 0; y < ISLAND_HEIGHT; y++) {
                model.territories[x][y].getLeader().setIslandCellColor();
            }
        }
    }

    public Territory[][] getTerritories () {
        return territories;
    }
}
