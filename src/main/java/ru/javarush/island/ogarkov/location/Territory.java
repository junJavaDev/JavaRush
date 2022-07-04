package ru.javarush.island.ogarkov.location;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.island.ogarkov.exception.IslandException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Territory {
    private final List<Cell> cells;
    @Setter
    private List<Territory> adjacentTerritory;

    public Territory() {
        cells = new ArrayList<>();
    }

    public Cell foundLeader() {
        return cells.stream()
                .max(Cell::compareTo)
                .orElseThrow(IslandException::new);
    }
}
