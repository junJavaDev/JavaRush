package ru.javarush.island.ogarkov.repository;

import ru.javarush.island.ogarkov.location.Island;
import ru.javarush.island.ogarkov.location.Territory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static ru.javarush.island.ogarkov.settings.Setting.TERRITORY_COLS;
import static ru.javarush.island.ogarkov.settings.Setting.TERRITORY_ROWS;

public class IslandCreator {

    TerritoryCreator territoryCreator;

    public IslandCreator(TerritoryCreator territoryCreator) {
        this.territoryCreator = territoryCreator;
    }

    public Island createIsland(int rows, int cols) {
        Island island = new Island(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Territory territory = territoryCreator.createTerritory(TERRITORY_COLS, TERRITORY_ROWS);
                island.getIslandMap()[row][col] = territory;
                island.getTerritories().add(territory);
            }
        }
        fillAdjacentTerritories(island, rows, cols);
        return island;
    }

    private void fillAdjacentTerritories(Island island, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Territory territory = island.getIslandMap()[row][col];
                Set<Territory> adjacent = new HashSet<>();
                int minRow = Math.max(row - 1, 0);
                int maxRow = Math.min(row + 2, rows);
                int minCol = Math.max(col - 1, 0);
                int maxCol = Math.min(col + 2, cols);
                for (int adjacentRow = minRow; adjacentRow < maxRow; adjacentRow++) {
                    for (int adjacentCol = minCol; adjacentCol < maxCol; adjacentCol++) {
                        if (adjacentRow != row || adjacentCol != col) {
                            adjacent.add(island.getIslandMap()[adjacentRow][adjacentCol]);
                        }
                    }
                }
                territory.setAdjacentTerritory(new ArrayList<>(adjacent));
            }
        }
    }
}
