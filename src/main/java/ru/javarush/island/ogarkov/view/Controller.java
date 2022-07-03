package ru.javarush.island.ogarkov.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import ru.javarush.island.ogarkov.entity.Statistics;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.location.Island;
import ru.javarush.island.ogarkov.location.Territory;
import ru.javarush.island.ogarkov.services.SimulationWorker;
import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.settings.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ru.javarush.island.ogarkov.settings.Items.*;
import static ru.javarush.island.ogarkov.settings.Setting.*;


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
    private Image[] statisticsIconsForUpdate;
    private String[] statisticsAliveForUpdate;
    private String[] statisticsDeadForUpdate;
    private double[] statisticsDiagramForUpdate;

    public Controller(Island island, Statistics statistics) {
        this.island = island;
        this.statistics = statistics;
    }

    public void prepareForUpdateView() {
        prepareIslandForUpdateView();
        prepareTerritoryForUpdateView();
        prepareStatisticForUpdateView();
    }

    public void updateView() {
        updateIslandField(
                islandIconsForUpdate,
                islandColorsForUpdate
        );
        updateTerritoryField(
                territoryIconsForUpdate,
                territoryColorsForUpdate,
                territoryTextsForUpdate
        );
        updateStatisticsField(
                statisticsIconsForUpdate,
                statisticsAliveForUpdate,
                statisticsDeadForUpdate,
                statisticsDiagramForUpdate
        );
    }

    public void setSimulationWorker(SimulationWorker simulationWorker) {
        this.simulationWorker = simulationWorker;
    }

    public EventHandler<WindowEvent> getCloseEventHandler() {
        return event -> simulationWorker.stopIt();
    }

    @FXML
    private void initialize() {
        createTerritoryField();
        createIslandField();
        createStatisticsField(
                PLANT,
                CARNIVORE,
                HERBIVORE
        );
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
                    prepareIslandForUpdateView();
                    prepareTerritoryForUpdateView();
                    updateView();
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

    private void createStatisticsField(Items... parent) {
        for (Items parents : parent) {
            for (Items item : parents.getLower()) {
                StatisticsView statisticsView = new StatisticsView();
                statisticsView.updateView(
                        item.getIcon(),
                        EMPTY_STRING,
                        EMPTY_STRING
                );
                if (item.is(ANIMAL)) {
                    addStatisticsView(animalStatisticsField, statisticsView);
                } else if (item.is(PLANT)) {
                    addStatisticsView(plantStatisticsField, statisticsView);
                }
            }
        }
        updateMaxHeight(plantStatisticsPane);
        updateMaxHeight(animalStatisticsPane);
    }

    private void initUpdateableFields() {
        int islandSize = ISLAND_ROWS * ISLAND_COLS;
        int territorySize = TERRITORY_ROWS * TERRITORY_COLS;
        int statisticsSize = Items.getLowerItems().size()- LANDFORM.getLower().size();
        Arrays.fill(islandIconsForUpdate = new Image[islandSize], PLAIN.getIcon());
        Arrays.fill(islandColorsForUpdate = new Color[islandSize], DEFAULT_ISLAND_COLOR);
        Arrays.fill(territoryIconsForUpdate = new Image[territorySize], PLAIN.getIcon());
        Arrays.fill(territoryColorsForUpdate = new Color[territorySize], DEFAULT_TERRITORY_COLOR);
        Arrays.fill(territoryTextsForUpdate = new String[territorySize], EMPTY_STRING);
        Arrays.fill(statisticsIconsForUpdate = new Image[statisticsSize], PLAIN.getIcon());
        Arrays.fill(statisticsAliveForUpdate = new String[statisticsSize], EMPTY_STRING);
        Arrays.fill(statisticsDeadForUpdate = new String[statisticsSize], EMPTY_STRING);
        Arrays.fill(statisticsDiagramForUpdate = new double[statisticsSize], 0.001);
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
                            DEFAULT_ISLAND_COLOR;
        }
    }

    private void prepareTerritoryForUpdateView() {
        Territory selectedTerritory = island.getTerritories().get(selectedTerritoryIndex);
        List<Cell> cells = selectedTerritory.getCells();
        Cell leader = selectedTerritory.foundLeader();
        for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
            Cell cell = cells.get(cellIndex);
            territoryIconsForUpdate[cellIndex] =
                    cell
                            .getResidentItem()
                            .getIcon();
            territoryColorsForUpdate[cellIndex] =
                    cell == leader ?
                            SELECTED_COLOR :
                            DEFAULT_TERRITORY_COLOR;
            territoryTextsForUpdate[cellIndex] =
                    String.valueOf(cell.getPopulation().size());
        }
    }

    private void prepareStatisticForUpdateView() {
        var plantList = statistics.getSortedAlive(PLANT);
        var animalList = statistics.getSortedAlive(ANIMAL);
        int plantsCount = plantList.size();
        int maxAlivePlants = plantList.get(0).getValue();
        if (maxAlivePlants < DIAGRAM_MAX_PLANTS) {
            maxAlivePlants = DIAGRAM_MAX_PLANTS;
        }
        int maxAliveAnimals = animalList.get(0).getValue();
        if (maxAliveAnimals < DIAGRAM_MAX_ANIMALS) {
            maxAliveAnimals = DIAGRAM_MAX_ANIMALS;
        }

        List<Map.Entry<Items, Integer>> aliveList = new ArrayList<>(plantList);
        aliveList.addAll(animalList);
        for (int entryIndex = 0; entryIndex < aliveList.size(); entryIndex++) {
            Items item = aliveList.get(entryIndex).getKey();
            Image icon = item.getIcon();

            int aliveCount = aliveList.get(entryIndex).getValue();
            int deadCount = statistics.getDead().get(item);
            statisticsIconsForUpdate[entryIndex] = icon;
            statisticsAliveForUpdate[entryIndex] = String.valueOf(aliveCount);
            statisticsDeadForUpdate[entryIndex] = String.valueOf(deadCount);
            if (entryIndex < plantsCount) {
                statisticsDiagramForUpdate[entryIndex] = (1.0 * aliveCount/maxAlivePlants);
            } else {
                statisticsDiagramForUpdate[entryIndex] = (1.0 * aliveCount/maxAliveAnimals);
            }
        }
    }

    private void initSliderSpeed() {
        sliderSpeed.setOnMouseReleased(
                mouseEvent -> simulationWorker
                        .changeSpeed(
                                sliderSpeed
                                        .valueProperty()
                                        .intValue() * -1));
    }
}