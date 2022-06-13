package ru.javarush.ogarkov.islandsimulation.location;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.CarnivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.HerbivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.settings.Items;
import ru.javarush.ogarkov.islandsimulation.settings.Setting;

import java.util.Arrays;
import java.util.stream.Stream;

import static ru.javarush.ogarkov.islandsimulation.settings.Setting.*;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Location {
    public static Location model;
    private Territory[][] territories;
    private Territory leader;
    public int xPosition;
    public int yPosition;

    public static Location createModel() {
        model = new Location();
        model.clearLocation();
        model.leader.setOnMouseClicked(null);
        initModel();
        return model;
    }

    public Location(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        initialize();
    }

    public Location() {
        initialize();
    }

    public Territory getLeader() {
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

    private void addMouseClickedAction() {
        leader.setOnMouseClicked(event -> {
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
            Territory modelLeader = model.territories[leader.xPosition][leader.yPosition];
            Island.resetIslandColor();
            leader.setCellColor(Color.RED);
            modelLeader.setCellColor(Color.RED);
        });
    }


    public void clearLocation() {
        for (int x = 0; x < LOCATION_WIDTH; x++) {
            for (int y = 0; y < LOCATION_HEIGHT; y++) {
                territories[x][y].clearPopulation();
            }
        }
    }

    public Territory[][] getTerritories() {
        return territories;
    }

}
