package ru.javarush.island.ogarkov.location;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private final List<Territory> territories;
    private final Territory[][] islandMap;

    public Island(int rows, int cols) {
        territories = new ArrayList<>();
        islandMap = new Territory[rows][cols];
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public Territory[][] getIslandMap() {
        return islandMap;
    }


}
