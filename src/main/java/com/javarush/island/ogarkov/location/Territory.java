package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.services.UpdateViewWorker;
import com.javarush.island.ogarkov.util.CellComparator;

import java.util.HashMap;
import java.util.Set;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Territory {
    public static Territory modelView;
    private HashMap<Cell, Set<Organism>> cellsPopulation;
    private Cell[][] cells;
    Territory[] neighbors;

    public Territory[] getNeighbors() {
        return neighbors;
    }
    public void setNeighbors(Territory[] neighbors) {
        this.neighbors = neighbors;
    }

    public Territory(int rows, int colls) {
        cells = new Cell[rows][colls];
        cellsPopulation = new HashMap<>();
    }

    public Cell getLeader() {
        return cellsPopulation.entrySet().stream()
                .sorted(CellComparator.get())
                .skip(cellsPopulation.entrySet().size() - 1)
                .findAny().orElseThrow()
                .getKey();
    }





    public HashMap<Cell, Set<Organism>> getCellsPopulation() {
        return cellsPopulation;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
    }

}

