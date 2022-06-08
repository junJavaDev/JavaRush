package ru.javarush.ogarkov.islandsimulation;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Text;

public class IslandModel {
    // Ширина, высота острова
    private final int width;
    private final int height;
    // Размер ячейки
    private final int cellSize;
    // Массив Локаций
    private Location[][] locations;

    public IslandModel(int width, int height, int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
    }

    public void initialize() {
        fillArea();
        createSimulation();
    }

    private void fillArea() {
        locations = new Location[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Rectangle cellBackground = new Rectangle();
                cellBackground.setWidth(cellSize);
                cellBackground.setHeight(cellSize);
                Location location = new Location(cellBackground, new Text(), new ImageView());
                locations[x][y] = location;
                location.setPositionX(x);
                location.setPositionY(y);
            }
        }
    }

    private void createSimulation() {
        // Здесь будет сложная логика по созданию симуляции
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Location location = locations[x][y];
                location.setCellText("#");
                location.setCellColor(Color.LIGHTGREY);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Location[][] getLocations() {
        return locations;
    }
}
