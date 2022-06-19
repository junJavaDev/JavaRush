package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.services.IslandUpdateWorker;
import com.javarush.island.ogarkov.util.UpdateableTreeSet;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import com.javarush.island.ogarkov.Controller;

import java.util.*;

import static com.javarush.island.ogarkov.settings.Setting.*;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Territory {
    public static Territory model;
    private Cell[][] cells;
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
        cells = new Cell[row][col];
        sortedCells = new UpdateableTreeSet<>();
    }

    public Territory() {
    }

    public Cell getLeader() {
        return sortedCells.last();
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

    public void changeTerritoryToView(Territory territory){
        IslandUpdateWorker.setTerritoryToView(territory);
    }

    public void updateTerritoryModel(Territory territory) {
        for (int x = 0; x < TERRITORY_ROWS; x++) {
            for (int y = 0; y < TERRITORY_COLS; y++) {
                Cell currentCell = territory.cells[x][y];
                Cell modelCell = model.getCells()[x][y];
                modelCell.setCellColor(Color.LIGHTGREY);
                Image territoryIcon = currentCell.getResident().getIcon();
                modelCell.setCellImage(territoryIcon);
                modelCell.getText().setText(currentCell.getPopulation().size() + "");
            }
        }
        Cell modelLeader = model.cells[leader.getXPosition()][leader.getYPosition()];
        leader = getLeader();
        leader.setCellColor(Color.RED);
//        leader.setCellImage(leader.getResident().getIcon());
        modelLeader.setCellColor(Color.RED);
        modelLeader.setCellImage(leader.getResident().getIcon());
    }
    public UpdateableTreeSet<Cell> getSortedCells() {
        return sortedCells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
        sortedCells.add(cell);
    }

    public void updateSortedCells() {
        sortedCells.clear();
        for (int row = 0; row < TERRITORY_ROWS; row++) {
            for (int col = 0; col < TERRITORY_COLS; col++) {
                sortedCells.add(cells[row][col]);
            }
        }
    }

}
