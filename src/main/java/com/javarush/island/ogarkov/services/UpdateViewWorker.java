package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static com.javarush.island.ogarkov.settings.Setting.*;

public class UpdateViewWorker implements Runnable {
    public final Controller controller;
    public final Island island;
    public final Territory territoryModel;
    public static Territory territoryToView;

    public UpdateViewWorker(Island island, Territory territoryModel, Controller controller) {
        this.territoryModel = territoryModel;
        this.controller = controller;
        this.island = island;
    }

    @Override
    public void run() {
        updateIslandView(island);
        updateTerritoryView(territoryToView);
        Platform.runLater(() -> {
            controller.updateIslandAreaView();
//            controller.updateTerritoryAreaView();
        });
    }

    public void updateIslandView(Island island) {
        for (int x = 0; x < ISLAND_ROWS; x++) {
            for (int y = 0; y < ISLAND_COLS; y++) {
                var currentTerritory = island.getTerritories()[x][y];
                if (currentTerritory != territoryToView) {
                    var leader = currentTerritory.foundLeader();
                    leader.setOnMouseClicked(event -> {
                        UpdateViewWorker.setTerritoryToView(currentTerritory);
                    });
//                leader.addIslandGrid(x, y, ISLAND_GRID_SIZE);
                    leader.setCellImage(leader.getResidentItem().getIcon());
                    leader.setLeaderColor();
                }
            }
        }
    }

    public void updateTerritoryView(Territory territory) {
        for (int row = 0; row < TERRITORY_ROWS; row++) {
            for (int cell = 0; cell < TERRITORY_COLS; cell++) {
                Cell currentCell = territory.getCells()[row][cell];
                Cell modelCell = territoryModel.getCells()[row][cell];
                Image territoryIcon = currentCell.getResidentItem().getIcon();
                modelCell.setCellColor(Color.LIGHTGREY);
                modelCell.setCellImage(territoryIcon);
                modelCell.setQuantity(String.valueOf(currentCell.getPopulation().size()));
            }
        }
        Cell leader = territory.foundLeader();
        Cell modelLeader = territoryModel.getCells()[leader.getTerritoryRow()][leader.getTerritoryCell()];
        leader.setCellColor(Color.RED);
        leader.setCellImage(leader.getResidentItem().getIcon());
        modelLeader.setCellColor(Color.RED);
        modelLeader.setCellImage(leader.getResidentItem().getIcon());
    }

    public static void setTerritoryToView(Territory territory) {
        territoryToView = territory;
    }
}
