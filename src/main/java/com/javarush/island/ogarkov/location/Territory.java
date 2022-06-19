package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.services.IslandUpdateWorker;
import com.javarush.island.ogarkov.util.CellComparator;
import com.javarush.island.ogarkov.util.UpdateableTreeSet;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.island.ogarkov.settings.Setting.TERRITORY_COLS;
import static com.javarush.island.ogarkov.settings.Setting.TERRITORY_ROWS;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Territory {
    public static Territory model;
    private ConcurrentHashMap<Cell, Set<Organism>> cells;
    private Cell[][] oldCells;
    private Cell leader;
    private int row;
    private int col;
    private Controller controller;
    private Island island;
    UpdateableTreeSet<Cell> sortedCells;
    Set<Territory> neighbors;
    private static IslandUpdateWorker islandUpdateWorker;


    public Set<Territory> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Territory> neighbors) {
        this.neighbors = neighbors;
    }


    public Territory(int row, int col) {
        this.row = row;
        this.col = col;
        oldCells = new Cell[row][col];
        sortedCells = new UpdateableTreeSet<>();
        cells = new ConcurrentHashMap<>();
    }

    public Territory() {
    }

    public Cell getLeader() {
        return cells.entrySet().stream()
                .sorted(CellComparator.get())
                .skip(cells.entrySet().size() - 1)
                .findAny().orElseThrow()
                .getKey();
    }


    // TODO: 16.06.2022 убрать это из класса территории
    public void addMouseClickedAction() {

        leader = getLeader();
        leader.setOnMouseClicked(event -> {
            changeTerritoryToView(this);
            updateTerritoryModel(this);
            Island.controller.updateIslandField();
        });
    }

    public void changeTerritoryToView(Territory territory) {
        IslandUpdateWorker.setTerritoryToView(territory);
    }

    public void updateTerritoryModel(Territory territory) {
        for (int x = 0; x < TERRITORY_ROWS; x++) {
            for (int y = 0; y < TERRITORY_COLS; y++) {
                Cell currentCell = territory.oldCells[x][y];
                Cell modelCell = model.getOldCells()[x][y];
                modelCell.setCellColor(Color.LIGHTGREY);
                Image territoryIcon = currentCell.getResident().getIcon();
                modelCell.setCellImage(territoryIcon);
                modelCell.getText().setText(currentCell.getPopulation().size() + "");
            }
        }
        Cell modelLeader = model.oldCells[leader.getXPosition()][leader.getYPosition()];
        leader = getLeader();
        leader.setCellColor(Color.RED);
//        leader.setCellImage(leader.getResident().getIcon());
        modelLeader.setCellColor(Color.RED);
        modelLeader.setCellImage(leader.getResident().getIcon());
    }

    public UpdateableTreeSet<Cell> getSortedCells() {
        return sortedCells;
    }


    public ConcurrentHashMap<Cell, Set<Organism>> getCells() {
        return cells;
    }

    public Cell[][] getOldCells() {
        return oldCells;
    }

    public void setCell(int row, int col, Cell cell) {
        oldCells[row][col] = cell;
        sortedCells.add(cell);
    }

    public void updateSortedCells() {
//        sortedCells.clear();
        for (int row = 0; row < TERRITORY_ROWS; row++) {
            for (int col = 0; col < TERRITORY_COLS; col++) {
                sortedCells.update(oldCells[row][col]);
            }
        }
    }
}

