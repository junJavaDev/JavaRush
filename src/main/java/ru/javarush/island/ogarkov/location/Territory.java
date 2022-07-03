package ru.javarush.island.ogarkov.location;

import ru.javarush.island.ogarkov.exception.IslandException;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private final List<Cell> cells;
    private List<Territory> adjacentTerritory;

    public Territory() {
        cells = new ArrayList<>();
    }

    public List<Territory> getAdjacentTerritory() {
        return adjacentTerritory;
    }

    public void setAdjacentTerritory(List<Territory> adjacentTerritory) {
        this.adjacentTerritory = adjacentTerritory;
    }

    public Cell foundLeader() {
        return cells.stream()
                .max(Cell::compareTo)
                .orElseThrow(IslandException::new);
    }

    public List<Cell> getCells() {
        return cells;
    }
}
