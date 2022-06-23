package com.javarush.island.ogarkov.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

import static com.javarush.island.ogarkov.settings.Setting.*;

public class View {

    @FXML
    private AnchorPane islandField;

    @FXML
    private AnchorPane territoryField;

    @FXML
    private TextFlow statisticsField;

    protected void initIslandField(List<CellView> cells) {
        islandField.setPrefHeight(ISLAND_ROWS * (ISLAND_CELL_HEIGHT + ISLAND_GRID_SIZE) - ISLAND_GRID_SIZE);
        islandField.setPrefWidth(ISLAND_COLS * (ISLAND_CELL_WIDTH + ISLAND_GRID_SIZE) - ISLAND_GRID_SIZE);
        islandField.getChildren().addAll(cells);
    }

    protected void initTerritoryField(List<CellView> cells) {
        territoryField.getChildren().addAll(cells);
    }

    protected void initStatisticsField(List<Text> texts) {
        statisticsField.getChildren().addAll(texts);
    }

    protected void updateIslandField(Image[] icons, Color[] colors) {
        for (int cellIndex = 0; cellIndex < icons.length; cellIndex++) {
            CellView cell = (CellView)islandField.getChildren().get(cellIndex);
            cell.updateView(icons[cellIndex], colors[cellIndex]);
        }
    }

    protected void updateTerritoryField(Image[] icons, Color[] colors, String[] texts) {
        for (int cellIndex = 0; cellIndex < icons.length; cellIndex++) {
            CellView cell = (CellView)territoryField.getChildren().get(cellIndex);
            cell.updateView(icons[cellIndex], colors[cellIndex], texts[cellIndex]);
        }
    }

    protected void updateStatisticsField(String[] texts) {
        for (int textIndex = 0; textIndex < texts.length; textIndex++) {
            Text text = (Text)statisticsField.getChildren().get(textIndex);
            text.setText(texts[textIndex]);
        }
    }
}
