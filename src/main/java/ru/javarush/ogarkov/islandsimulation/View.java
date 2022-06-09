package ru.javarush.ogarkov.islandsimulation;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class View {

    @FXML
    private AnchorPane islandField;

    @FXML
    private AnchorPane locationField;

    protected void initIslandField(Island model) {
        islandField.getChildren().clear();
        for (int x = 0; x < model.getWidth(); x++) {
            for (int y = 0; y < model.getHeight(); y++) {
                islandField.getChildren().add(model.getLocations()[x][y].getLeader());
            }
        }
    }

    protected void initLocationField(Location model) {
        locationField.getChildren().clear();
        for (int x = 0; x < model.getWidth(); x++) {
            for (int y = 0; y < model.getHeight(); y++) {
                locationField.getChildren().add(model.getTerritories()[x][y]);
            }
        }
    }
}
