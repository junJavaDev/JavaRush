package com.javarush.island.ogarkov.location;

public class Island {
    private final Territory[][] territories;

    public Island(int rows, int cols) {
        territories = new Territory[rows][cols];
    }

    public Territory[][] getTerritories () {
        return territories;
    }

    public void setTerritoryPosition(int row, int col, Territory territory) {
        territories[row][col] = territory;
    }
}
