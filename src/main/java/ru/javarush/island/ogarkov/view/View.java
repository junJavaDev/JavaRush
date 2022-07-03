package ru.javarush.island.ogarkov.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

import static ru.javarush.island.ogarkov.settings.Setting.*;

public class View {
    @FXML
    protected AnchorPane mainPane;
    @FXML
    protected AnchorPane islandField;
    @FXML
    protected Slider sliderSpeed;
    @FXML
    protected AnchorPane rightPane;
    @FXML
    protected AnchorPane plantStatisticsPane;
    @FXML
    protected AnchorPane animalStatisticsPane;
    @FXML
    protected VBox plantStatisticsField;
    @FXML
    protected VBox animalStatisticsField;
    @FXML
    protected AnchorPane territoryField;
    @FXML
    protected ScrollPane scrollIslandField;
    @FXML
    protected ScrollPane scrollStatisticsField;
    @FXML
    protected Label speedLabel;

    protected void initIslandField(List<CellView> cells) {
        islandField.getChildren().addAll(cells);
    }

    protected void initTerritoryField(List<CellView> cells) {
        rightPane.setPrefWidth(200);
        int territoryWidth = TERRITORY_COLS * (TERRITORY_CELL_WIDTH + TERRITORY_GRID_SIZE) - TERRITORY_GRID_SIZE;
        int territoryHeigh = TERRITORY_ROWS * (TERRITORY_CELL_HEIGHT + TERRITORY_GRID_SIZE) - TERRITORY_GRID_SIZE;
        territoryField.setLayoutX((rightPane.getPrefWidth() - territoryWidth) / 2);
        territoryField.setLayoutY((speedLabel.getLayoutY() - territoryHeigh + territoryField.getLayoutY()) / 2);
        scrollStatisticsField.setLayoutX((rightPane.getPrefWidth() - scrollStatisticsField.getPrefWidth()) / 2);
        territoryField.getChildren().addAll(cells);
    }

    protected void addStatisticsView(VBox statisticsField, StatisticsView statisticsView) {
        statisticsField.getChildren().add(statisticsView);
    }

    protected void updateMaxHeight(AnchorPane statisticsPane) {
        VBox statisticsField = (VBox) statisticsPane.getChildren().get(0);
        StatisticsView statisticsView = (StatisticsView) statisticsField.getChildren().get(0);
        double statisticsViewHeight = statisticsView.getBackgroundHeight();
        double spacing = statisticsField.getSpacing();
        int statisticsViewCount = statisticsField.getChildren().size();
        double maxHeight = statisticsViewCount * (statisticsViewHeight + spacing) - spacing;
        statisticsPane.setMaxHeight(maxHeight);
    }

    protected void updateIslandField(Image[] icons, Color[] colors) {
        for (int cellIndex = 0; cellIndex < icons.length; cellIndex++) {
            CellView cellView = (CellView) islandField.getChildren().get(cellIndex);
            cellView.updateView(icons[cellIndex], colors[cellIndex]);
        }
    }

    protected void updateTerritoryField(Image[] icons, Color[] colors, String[] texts) {
        for (int cellIndex = 0; cellIndex < icons.length; cellIndex++) {
            CellView cellView = (CellView) territoryField.getChildren().get(cellIndex);
            cellView.updateView(icons[cellIndex], colors[cellIndex], texts[cellIndex]);
        }
    }

    protected void updateStatisticsField(Image[] icons, String[] alive, String[] dead, double[] diagramPercent) {
        int plantsCount = plantStatisticsField.getChildren().size();
        for (int statisticsViewIndex = 0; statisticsViewIndex < icons.length; statisticsViewIndex++) {
            StatisticsView statisticsView;
            if (statisticsViewIndex < plantsCount) {
                statisticsView = (StatisticsView) plantStatisticsField.getChildren().get(statisticsViewIndex);
            } else {
                statisticsView = (StatisticsView) animalStatisticsField.getChildren().get(statisticsViewIndex-plantsCount);
            }
            statisticsView.updateDiagram(diagramPercent[statisticsViewIndex]);
            statisticsView.updateView(icons[statisticsViewIndex], alive[statisticsViewIndex], dead[statisticsViewIndex]);
        }
    }
}
