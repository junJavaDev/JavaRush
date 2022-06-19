package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;

import java.util.HashSet;
import java.util.Set;

import static com.javarush.island.ogarkov.settings.Setting.*;

public class IslandCreator {

    TerritoryCreator territoryCreator;

    public IslandCreator(TerritoryCreator territoryCreator) {
        this.territoryCreator = territoryCreator;
    }

    public Island createIsland(int rows, int cols) {
        Island island = new Island(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var territory = territoryCreator.createTerritory(TERRITORY_COLS, TERRITORY_ROWS);
                island.setTerritory(row, col, territory);
                Cell leader = territory.getLeader();
                addLayoutView(row, col, leader);
            }
        }
        fillNeighbors(island);
        return island;
    }

    private void addLayoutView(int row, int col, Cell leader) {
        leader.setCellImage(leader.getIcon());
        leader.setLayoutX((col * (ISLAND_CELL_WIDTH + ISLAND_GRID_SIZE)));
        leader.setLayoutY((row * (ISLAND_CELL_HEIGHT + ISLAND_GRID_SIZE)));
        leader.setIslandCellColor();
    }

    private void fillNeighbors(Island island) {
        int rows = island.getTerritories().length;
        int cols = island.getTerritories()[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Territory territory = island.getTerritories()[row][col];
                Set<Territory> neighbors = new HashSet<>();
                int minRow = Math.max(row - 1, 0);
                int maxRow = Math.min(row + 2, rows);
                int minCol = Math.max(col - 1, 0);
                int maxCol = Math.min(col + 2, cols);
                for (int neighborRow = minRow; neighborRow < maxRow; neighborRow++) {
                    for (int neighborCol = minCol; neighborCol < maxCol; neighborCol++) {
                        if (neighborRow != row || neighborCol != col) {
                            neighbors.add(island.getTerritories()[neighborRow][neighborCol]);
                        }
                    }
                }
//                System.out.print("territory = " + territory);
//                System.out.println("neighbors = " + neighbors);
                territory.setNeighbors(neighbors);
            }
        }
    }

}
