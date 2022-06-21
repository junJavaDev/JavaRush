package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.util.CellComparator;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Territory {
    public static Territory modelView;
    private final HashMap<Cell, Set<Organism>> cellsPopulation;
    private final Cell[][] cells;
    Territory[] neighbors;


    public Territory(int rows, int colls) {
        cells = new Cell[rows][colls];
        cellsPopulation = new HashMap<>();
    }

    public Territory[] getNeighbors() {
        return neighbors;
    }
    public void setNeighbors(Territory[] neighbors) {
        this.neighbors = neighbors;
    }



    public Cell foundLeader() {
        return cellsPopulation.entrySet()
                .stream()
                .max(CellComparator.get())
                .orElseThrow()
                .getKey();
    }

    public Cell foundOutsider() {
        return cellsPopulation.entrySet()
                .stream()
                .min(CellComparator.get())
                .orElseThrow()
                .getKey();
    }

    public HashMap<Cell, Set<Organism>> getCellsPopulation() {
        return cellsPopulation;
    }

    public Set<Items> getResidentsItem() {
        return getCellsPopulation().keySet()
                .stream()
                .map(Cell::getResidentItem)
                .collect(Collectors.toSet());
    }

    public Cell foundCellByItem(Items item) {
        Optional<Cell> any = cellsPopulation.keySet()
                .stream()
                .filter(cell -> cell.getResidentItem() == item)
                .findAny();
        return any.orElse(null);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCellPosition(int row, int col, Cell cell) {
        cells[row][col] = cell;
    }

}

