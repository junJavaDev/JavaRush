package ru.javarush.ogarkov.islandsimulation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.CarnivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.HerbivoreAnimal;

// Локация, содержит массив территорий, отображает что/кто находится на территории и их количество, вычисляет лидера локации
public class Location extends Island{
    // Массив территорий
    private Territory[][] territories;
    private Territory leader;
    private static Location model;



    public static void setModel(Location model) {
        Location.model = model;
    }


    public Location(int width, int height, int cellSize) {
        super(width, height, cellSize);
    }

    public Territory getLeader() {
        return leader;
    }

    public void setLeader(Territory leader) {
        this.leader = leader;
    }

    @Override
    public void initialize() {
        fillArea();
    }


    // TODO: 09.06.2022 Сделать логику по лидеру
    private void fillArea() {
        territories = new Territory[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Rectangle cellBackground = new Rectangle();
                cellBackground.setWidth(cellSize);
                cellBackground.setHeight(cellSize);
                Territory territory = new Territory(cellBackground, new Text(), new ImageView());
                territories[x][y] = territory;
                territory.setPosition(x, y, Setting.LOCATION_GRID_SIZE);
                territory.setCellColor(Color.LIGHTGREY);
            }
        }
        leader = territories[0][0];
        addMouseClickedAction();
    }

    private void addMouseClickedAction() {
        leader.setOnMouseClicked(event -> {
            for (int x = 0; x < Setting.LOCATION_WIDTH; x++) {
                for (int y = 0; y < Setting.LOCATION_HEIGHT; y++) {
                    Territory locationModelTerritory = model.getTerritories()[x][y];
                    Territory currentTerritory = territories[x][y];
                    Image territoryIcon = currentTerritory.population[0].getIcon();
//                    locationModelTerritory.setCellColor(getColor(currentTerritory));
                    locationModelTerritory.setCellImage(territoryIcon);
//                    leader.setCellColor(Color.RED);
                    model.leader.setCellColor(Color.RED);
                }
            }
        });
    }

    // TODO: 09.06.2022 переделать этот метод
    public Color getColor(Territory territory) {
        Color color = Color.OLIVEDRAB;
        if (territory.population[0] instanceof HerbivoreAnimal) {
            color = Color.BLANCHEDALMOND;
        } else if (territory.population[0] instanceof CarnivoreAnimal) {
            color = Color.BLACK;
        }
        return color;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Territory[][] getTerritories() {
        return territories;
    }
}
