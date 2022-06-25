package com.javarush.island.ogarkov.view;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
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
    private AnchorPane mainPane;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private TextFlow statisticsField;

    @FXML
    private AnchorPane territoryField;

    @FXML
    private ScrollPane scrollIslandField;

    @FXML
    private ScrollPane scrollStatisticsField;

    protected void initIslandField(List<CellView> cells) {
        islandField.getChildren().addAll(cells);
    }

    protected void initTerritoryField(List<CellView> cells) {
        rightPane.setPrefWidth(200);
        int territoryWidth = TERRITORY_COLS * (TERRITORY_CELL_WIDTH + TERRITORY_GRID_SIZE) - TERRITORY_GRID_SIZE;
        int territoryHeigh = TERRITORY_ROWS * (TERRITORY_CELL_HEIGHT + TERRITORY_GRID_SIZE) - TERRITORY_GRID_SIZE;
        territoryField.setLayoutX((rightPane.getPrefWidth() - territoryWidth) / 2);
        territoryField.setLayoutY((scrollStatisticsField.getLayoutY() - territoryHeigh) / 2);
        scrollStatisticsField.setLayoutX((rightPane.getPrefWidth() - scrollStatisticsField.getPrefWidth()) / 2);
        territoryField.getChildren().addAll(cells);
    }

    protected void initStatisticsField(List<Text> texts) {
        statisticsField.getChildren().addAll(texts);
    }

    protected void updateIslandField(Image[] icons, Color[] colors) {
        for (int cellIndex = 0; cellIndex < icons.length; cellIndex++) {
            CellView cell = (CellView) islandField.getChildren().get(cellIndex);
            cell.updateView(icons[cellIndex], colors[cellIndex]);
        }
    }

    protected void updateTerritoryField(Image[] icons, Color[] colors, String[] texts) {
        for (int cellIndex = 0; cellIndex < icons.length; cellIndex++) {
            CellView cell = (CellView) territoryField.getChildren().get(cellIndex);
            cell.updateView(icons[cellIndex], colors[cellIndex], texts[cellIndex]);
        }
    }

    protected void updateStatisticsField(String[] texts) {
        for (int textIndex = 0; textIndex < texts.length; textIndex++) {
            Text text = (Text) statisticsField.getChildren().get(textIndex);
            text.setText(texts[textIndex]);
        }
    }
}
