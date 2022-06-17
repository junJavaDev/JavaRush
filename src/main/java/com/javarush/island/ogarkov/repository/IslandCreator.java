package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;

import static com.javarush.island.ogarkov.settings.Setting.*;

// TODO: 16.06.2022 переместить
//        territories = new Territory[ISLAND_WIDTH][ISLAND_HEIGHT];


public class IslandCreator {

    TerritoryCreator territoryCreator;

    public IslandCreator(TerritoryCreator territoryCreator) {
        this.territoryCreator = territoryCreator;
    }

    public Island createIsland(int rows, int cols) {
        Island island = new Island(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Territory territory = new Territory(this, row, col);
                territories[row][col] = territory;
                Cell leader = territory.getLeader();
                leader.setCellImage(leader.getIcon());
                leader.addIslandGrid(row, col, ISLAND_GRID_SIZE);
                leader.setIslandCellColor();
            }
        }
    }
}
