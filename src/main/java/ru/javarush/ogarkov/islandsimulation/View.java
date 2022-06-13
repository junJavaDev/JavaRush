package ru.javarush.ogarkov.islandsimulation;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ru.javarush.ogarkov.islandsimulation.location.Island;
import ru.javarush.ogarkov.islandsimulation.location.Location;
import ru.javarush.ogarkov.islandsimulation.settings.Setting;

import static ru.javarush.ogarkov.islandsimulation.settings.Setting.*;

public class View {

    @FXML
    private AnchorPane islandField;

    @FXML
    private AnchorPane locationField;

    protected void initIslandField(Island model) {
        islandField.getChildren().clear();
        islandField.setPrefWidth(ISLAND_WIDTH * (ISLAND_CELL_WIDTH + ISLAND_GRID_SIZE));
        islandField.setPrefHeight(ISLAND_HEIGHT * (ISLAND_CELL_HEIGHT + ISLAND_GRID_SIZE));
        for (int x = 0; x < ISLAND_WIDTH; x++) {
            for (int y = 0; y < ISLAND_HEIGHT; y++) {
                islandField.getChildren().add(model.getLocations()[x][y].getLeader());
            }
        }
    }

    protected void initLocationField(Location model) {
        locationField.getChildren().clear();
        for (int x = 0; x < Setting.LOCATION_WIDTH; x++) {
            for (int y = 0; y < Setting.LOCATION_HEIGHT; y++) {
                locationField.getChildren().add(model.getTerritories()[x][y]);
            }
        }
    }
}
