package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import javafx.application.Platform;

public class IslandUpdateWorker implements Runnable {
    public final Controller controller;
    public final Island island;
    public static Territory territoryToView = Island.model.getTerritories()[0][0];

    public IslandUpdateWorker(Island island, Controller controller) {
        this.controller = controller;
        this.island = island;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            Island.controller.updateIslandField();
            territoryToView.updateTerritoryModel(territoryToView);
        });
    }

    public static void setTerritoryToView(Territory territory) {
        territoryToView = territory;
    }
}
