package com.javarush.island.ogarkov.view;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.services.SimulationWorker;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.javarush.island.ogarkov.settings.Items.PLAIN;
import static com.javarush.island.ogarkov.settings.Items.values;
import static com.javarush.island.ogarkov.settings.Setting.*;


public class Controller extends View {

    private final Statistics statistics;
    private final Island island;
    private SimulationWorker simulationWorker;
    private int selectedTerritoryIndex;
    private Image[] islandIconsForUpdate;
    private Color[] islandColorsForUpdate;
    private Image[] territoryIconsForUpdate;
    private Color[] territoryColorsForUpdate;
    private String[] territoryTextsForUpdate;
    private String[] statisticsForUpdate;

    public Controller(Island island, Statistics statistics) {
        this.island = island;
        this.statistics = statistics;
    }

    @FXML
    void initialize() {
        createTerritoryField();
        createIslandField();
        createStatisticsField();
        initUpdateableFields();
        initSliderSpeed();
        updateView();
    }

    private void createIslandField() {
        var cells = new ArrayList<CellView>();
        for (int row = 0; row < ISLAND_ROWS; row++) {
            for (int col = 0; col < ISLAND_COLS; col++) {
                var cell = new CellView();
                cell.setIslandLayout(row, col);
                cells.add(cell);
                cell.setIndex(cells.size() - 1);
                cell.setOnMouseClicked(event -> {
                    CellView source = (CellView) event.getSource();
                    selectedTerritoryIndex = source.getIndex();
                });
            }
        }
        initIslandField(cells);
    }

    private void createTerritoryField() {
        var cells = new ArrayList<CellView>();
        for (int row = 0; row < Setting.TERRITORY_ROWS; row++) {
            for (int col = 0; col < Setting.TERRITORY_COLS; col++) {
                var cellView = new CellView();
                cellView.setTerritoryLayout(row, col);
                cells.add(cellView);
            }
        }
        initTerritoryField(cells);
    }

    // TODO: 23.06.2022 добавить иконки / цвета, убрать родителей
    private void createStatisticsField() {
        var texts = new ArrayList<Text>();
        for (Items item : Items.values()) {
            Text text = new Text();
            texts.add(text);
        }
        initStatisticsField(texts);
    }

    public void prepareForUpdateView() {
        prepareIslandForUpdateView();
        prepareTerritoryForUpdateView();
    }

    public void updateView() {
        updateIslandField(islandIconsForUpdate, islandColorsForUpdate);
        updateTerritoryField(territoryIconsForUpdate, territoryColorsForUpdate, territoryTextsForUpdate);
        updateStatisticsField(statisticsForUpdate);
    }


    private void initUpdateableFields() {
        int islandSize = ISLAND_ROWS * ISLAND_COLS;
        int territorySize = TERRITORY_ROWS * TERRITORY_COLS;
        int statisticsSize = values().length;
        Arrays.fill(islandIconsForUpdate = new Image[islandSize], PLAIN.getIcon());
        Arrays.fill(islandColorsForUpdate = new Color[islandSize], DEFAULT_ISLAND_COLOR);
        Arrays.fill(territoryIconsForUpdate = new Image[territorySize], PLAIN.getIcon());
        Arrays.fill(territoryColorsForUpdate = new Color[territorySize], DEFAULT_TERRITORY_COLOR);
        Arrays.fill(territoryTextsForUpdate = new String[territorySize], EMPTY_STRING);
        Arrays.fill(statisticsForUpdate = new String[statisticsSize], EMPTY_STRING);
    }

    private void prepareIslandForUpdateView() {
        var territories = island.getTerritories();
        for (int terIndex = 0; terIndex < territories.size(); terIndex++) {
            Territory territory = territories.get(terIndex);
            Items item = territory.foundLeader().getResidentItem();
            islandIconsForUpdate[terIndex] = item.getIcon();
            islandColorsForUpdate[terIndex] =
                    terIndex == selectedTerritoryIndex ?
                            SELECTED_COLOR :
                            ISLAND_COLORS.getOrDefault(item, DEFAULT_ISLAND_COLOR);
        }
    }

    private void prepareTerritoryForUpdateView() {
        Territory selectedTerritory = island.getTerritories().get(selectedTerritoryIndex);
        List<Cell> cells = selectedTerritory.getCells();
        Cell leader = selectedTerritory.foundLeader();
        for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
            Cell cell = cells.get(cellIndex);
            territoryIconsForUpdate[cellIndex] = cell.getResidentItem().getIcon();
            territoryColorsForUpdate[cellIndex] =
                    cell == leader ?
                            SELECTED_COLOR :
                            DEFAULT_TERRITORY_COLOR;
            territoryTextsForUpdate[cellIndex] = String.valueOf(cell.getPopulation().size());
        }
    }

    public void prepareStatisticForUpdateView() {
        Items[] itemsValues = Items.values();
        for (int itemIndex = 0; itemIndex < itemsValues.length; itemIndex++) {
            Items item = itemsValues[itemIndex];
            statisticsForUpdate[itemIndex] = item + " - " + statistics.getExisting().get(item) + "\n";
        }
    }

    private void initSliderSpeed() {
        sliderSpeed.setOnMouseReleased(mouseEvent -> simulationWorker.changeSpeed(sliderSpeed.valueProperty().intValue() * -1));
    }

    public void setSimulationWorker(SimulationWorker simulationWorker) {
        this.simulationWorker = simulationWorker;
    }


    public EventHandler<WindowEvent> getCloseEventHandler() {
        return event -> simulationWorker.stopIt();
    }
}