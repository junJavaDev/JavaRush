package ru.javarush.ogarkov.islandsimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class View {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane islandField;

    void updateIslandField(IslandModel model) {
        islandField.getChildren().clear();
        for (int x = 0; x < model.getWidth(); x++) {
            for (int y = 0; y < model.getHeight(); y++) {
                islandField.getChildren().add(model.getLocations()[x][y]);
            }
        }
    }
}
