package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.exception.IslandException;
import com.javarush.island.ogarkov.settings.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Cell foundOutsider() {
        return cells.stream()
                .min(Cell::compareTo)
                .orElseThrow(IslandException::new);
    }

    public Cell foundCellByItem(Items item) {
        Optional<Cell> any =
                cells.stream()
                .filter(cell -> cell.getResidentItem() == item)
                .findAny();
        return any.orElse(null);
    }

    public List<Cell> getCells() {
        return cells;
    }
}

