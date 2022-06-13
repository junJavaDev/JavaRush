package ru.javarush.ogarkov.islandsimulation.location;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.CarnivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.HerbivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.settings.Setting;

import static ru.javarush.ogarkov.islandsimulation.settings.Setting.LOCATION_WIDTH;
import static ru.javarush.ogarkov.islandsimulation.settings.Setting.LOCATION_HEIGHT;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Location {
    private static Location model;
    private Territory[][] territories;
    private Territory leader;
    public int xPosition;
    public int yPosition;
    public static Location createModel() {
        model = new Location();
        model.clearLocation();
        model.leader.setOnMouseClicked(null);
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

    public void setLeader() {

    }

    public void initialize() {
        fillArea();
    }

    private void fillArea() {
        territories = new Territory[LOCATION_WIDTH][LOCATION_HEIGHT];
        for (int xPosition = 0; xPosition < LOCATION_WIDTH; xPosition++) {
            for (int yPosition = 0; yPosition < LOCATION_HEIGHT; yPosition++) {
                territories[xPosition][yPosition] = new Territory(this, xPosition, yPosition);
            }
        }
        leader = territories[0][0];
        addMouseClickedAction();
    }

    private void addMouseClickedAction() {
        leader.setOnMouseClicked(event -> {
            for (int x = 0; x < LOCATION_WIDTH; x++) {
                for (int y = 0; y < LOCATION_HEIGHT; y++) {
                    Territory currentTerritory = territories[x][y];
                    Territory modelTerritory = model.getTerritories()[x][y];
                    Image territoryIcon = currentTerritory.getResident().getIcon();
                    modelTerritory.setCellImage(territoryIcon);
                    modelTerritory.getCellBackground().setHeight(Setting.LOCATION_CELL_HEIGHT);
                    modelTerritory.getText().setText(currentTerritory.getPopulation().length + "");
                    model.getLeader().setCellColor(Color.RED);
                }
            }
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
