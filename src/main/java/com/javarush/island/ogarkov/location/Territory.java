package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.entity.Item;
import com.javarush.island.ogarkov.services.ItemWorker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;

import java.util.Arrays;

import static com.javarush.island.ogarkov.settings.Setting.*;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Territory {
    public static Territory model;
    private Island island;
    private Cell[][] cells;
    private Cell leader;
    private int xPosition;
    private int yPosition;
    private Controller controller;

    public static Territory createModel() {
        model = new Territory();
        model.clearLocation();
        model.leader.setOnMouseClicked(null);
        initModel();
        return model;
    }

    public Territory(Island island, int xPosition, int yPosition) {
        this.island = island;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        initialize();
    }

    public Territory() {
        initialize();
    }

    public Cell getLeader() {
        foundLeader();
        return leader;
    }

    public void foundLeader() {
        leader = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .max(Cell::compareTo).get();
    }

    public void setLeader(Cell leader) {
        this.leader = leader;
    }

    public void initialize() {
        fillArea();
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    private static void initModel() {
        for (int x = 0; x < LOCATION_WIDTH; x++) {
            for (int y = 0; y < LOCATION_HEIGHT; y++) {
                Cell modelCell = model.getCells()[x][y];
                modelCell.setCellColor(Color.LIGHTGREY);
                modelCell.getCellBackground().setHeight(Setting.LOCATION_CELL_HEIGHT);
                modelCell.addGrid(x, y, LOCATION_GRID_SIZE);
            }
        }
    }

    private void fillArea() {
        cells = new Cell[LOCATION_WIDTH][LOCATION_HEIGHT];
        for (int xPosition = 0; xPosition < LOCATION_WIDTH; xPosition++) {
            for (int yPosition = 0; yPosition < LOCATION_HEIGHT; yPosition++) {
                cells[xPosition][yPosition] = new Cell(this, xPosition, yPosition);
            }
        }
        foundLeader();
        addMouseClickedAction();
    }

    // TODO: 16.06.2022 убрать это из класса локейшн
    public void addMouseClickedAction() {
        leader = getLeader();
        leader.setOnMouseClicked(event -> {

            // TODO: 15.06.2022 времянка
            for (int x = 0; x < LOCATION_WIDTH; x++) {
                for (int y = 0; y < LOCATION_HEIGHT; y++) {
                    Cell currentCell = cells[x][y];
                    Cell modelCell = model.getCells()[x][y];
                    modelCell.setCellColor(Color.LIGHTGREY);
                    Image territoryIcon = currentCell.getResident().getIcon();
//                    currentTerritory.setCellColor(Color.RED);
                    modelCell.setCellImage(territoryIcon);

//                    modelTerritory.setCellColor(Color.RED);
                    modelCell.getText().setText(currentCell.getPopulation().length + "");

                }
            }
            // TODO: 15.06.2022 времянка

            Cell modelLeader = model.cells[leader.getXPosition()][leader.getYPosition()];
            // TODO: 16.06.2022 что за статика тут
            Island.resetIslandColor();
            Item item = leader.getPopulation()[0];
            if (item.getItem().is(Items.HERBIVORE) || item.getItem().is(Items.CARNIVORE)) {
                new ItemWorker((Animal) item).move();
            }
            leader.setCellColor(Color.RED);
            modelLeader.setCellColor(Color.RED);
            modelLeader.setCellImage(leader.getIcon());
            controller.updateIslandField();
        });
    }


    public void clearLocation() {
        for (int x = 0; x < LOCATION_WIDTH; x++) {
            for (int y = 0; y < LOCATION_HEIGHT; y++) {
                cells[x][y].clearPopulation();
            }
        }
    }

    public Island getIsland() {
        return island;
    }

    public Cell[][] getCells() {
        return cells;
    }

}
