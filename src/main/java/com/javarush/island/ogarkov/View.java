package com.javarush.island.ogarkov;

import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Setting;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

import static com.javarush.island.ogarkov.settings.Setting.*;

public class View {

    @FXML
    private AnchorPane islandField;

    @FXML
    private AnchorPane locationField;

    @FXML
    private TextFlow staticticField;

    protected void initIslandField(Island model) {

        islandField.setPrefHeight(ISLAND_ROWS * (ISLAND_CELL_HEIGHT + ISLAND_GRID_SIZE));
        islandField.setPrefWidth(ISLAND_COLS * (ISLAND_CELL_WIDTH + ISLAND_GRID_SIZE));
        for (int row = 0; row < ISLAND_ROWS; row++) {
            for (int col = 0; col < ISLAND_COLS; col++) {
                islandField.getChildren().add(model.getTerritories()[row][col].getLeader());
            }
        }
    }

    protected void updateIslandFiels(Island model) {
        int index = 0;
        islandField.setPrefHeight(ISLAND_ROWS * (ISLAND_CELL_HEIGHT + ISLAND_GRID_SIZE));
        islandField.setPrefWidth(ISLAND_COLS * (ISLAND_CELL_WIDTH + ISLAND_GRID_SIZE));
        for (int row = 0; row < ISLAND_ROWS; row++) {
            for (int col = 0; col < ISLAND_COLS; col++) {
                islandField.getChildren().set(index, model.getTerritories()[row][col].getLeader());
                index++;
            }
        }
    }

    protected void initLocationField(Territory model) {
        locationField.getChildren().clear();
        for (int x = 0; x < TERRITORY_ROWS; x++) {
            for (int y = 0; y < Setting.TERRITORY_COLS; y++) {
                locationField.getChildren().add(model.getOldCells()[x][y]);
            }
        }
    }

    protected void initStatistic(List<Text> texts) {
        staticticField.getChildren().clear();
        for (Text text : texts) {
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 11));
            staticticField.getChildren().add(text);
        }
    }
}
