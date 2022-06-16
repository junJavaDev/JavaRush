package ru.javarush.ogarkov.island.location;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.javarush.ogarkov.island.Controller;
import ru.javarush.ogarkov.island.entity.animals.Animal;
import ru.javarush.ogarkov.island.entity.Item;
import ru.javarush.ogarkov.island.services.ItemWorker;
import ru.javarush.ogarkov.island.settings.Items;
import ru.javarush.ogarkov.island.settings.Setting;

import java.util.Arrays;

import static ru.javarush.ogarkov.island.settings.Setting.*;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Location {
    public static Location model;
    private Island island;
    private Territory[][] territories;
    private Territory leader;
    private int xPosition;
    private int yPosition;
    private Controller controller;

    public static Location createModel() {
        model = new Location();
        model.clearLocation();
        model.leader.setOnMouseClicked(null);
        initModel();
        return model;
    }

    public Location(Island island, int xPosition, int yPosition, Controller controller) {
        this.island = island;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.controller = controller;
        initialize();
    }

    public Location() {
        initialize();
    }

    public Territory getLeader() {
        foundLeader();
        return leader;
    }

    public void foundLeader() {
        leader = Arrays.stream(territories)
                .flatMap(Arrays::stream)
                .max(Territory::compareTo).get();
    }

    public void setLeader(Territory leader) {
        this.leader = leader;
    }

    public void initialize() {
        fillArea();
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    private static void initModel() {
        for (int x = 0; x < LOCATION_WIDTH; x++) {
            for (int y = 0; y < LOCATION_HEIGHT; y++) {
                Territory modelTerritory = model.getTerritories()[x][y];
                modelTerritory.setCellColor(Color.LIGHTGREY);
                modelTerritory.getCellBackground().setHeight(Setting.LOCATION_CELL_HEIGHT);
                modelTerritory.addGrid(x, y, LOCATION_GRID_SIZE);
            }
        }
    }

    private void fillArea() {
        territories = new Territory[LOCATION_WIDTH][LOCATION_HEIGHT];
        for (int xPosition = 0; xPosition < LOCATION_WIDTH; xPosition++) {
            for (int yPosition = 0; yPosition < LOCATION_HEIGHT; yPosition++) {
                territories[xPosition][yPosition] = new Territory(this, xPosition, yPosition);
            }
        }
        foundLeader();
        addMouseClickedAction();
    }

    // TODO: 16.06.2022 убрать это из класса локейшн
    public void addMouseClickedAction() {
        leader = getLeader();
        leader.setOnMouseClicked(event -> {

            // TODO: 15.06.2022 времянка
            for (int x = 0; x < LOCATION_WIDTH; x++) {
                for (int y = 0; y < LOCATION_HEIGHT; y++) {
                    Territory currentTerritory = territories[x][y];
                    Territory modelTerritory = model.getTerritories()[x][y];
                    modelTerritory.setCellColor(Color.LIGHTGREY);
                    Image territoryIcon = currentTerritory.getResident().getIcon();
//                    currentTerritory.setCellColor(Color.RED);
                    modelTerritory.setCellImage(territoryIcon);

//                    modelTerritory.setCellColor(Color.RED);
                    modelTerritory.getText().setText(currentTerritory.getPopulation().length + "");

                }
            }
            // TODO: 15.06.2022 времянка

            Territory modelLeader = model.territories[leader.getXPosition()][leader.getYPosition()];
            // TODO: 16.06.2022 что за статика тут
            Island.resetIslandColor();
            Item item = leader.getPopulation()[0];
            if (item.getItem().is(Items.HERBIVORE) || item.getItem().is(Items.CARNIVORE)) {
                new ItemWorker((Animal) item).move();
            }
            leader.setCellColor(Color.RED);
            modelLeader.setCellColor(Color.RED);
            modelLeader.setCellImage(leader.getIcon());
            controller.updateIslandField();
        });
    }


    public void clearLocation() {
        for (int x = 0; x < LOCATION_WIDTH; x++) {
            for (int y = 0; y < LOCATION_HEIGHT; y++) {
                territories[x][y].clearPopulation();
            }
        }
    }

    public Island getIsland() {
        return island;
    }

    public Territory[][] getTerritories() {
        return territories;
    }

}
