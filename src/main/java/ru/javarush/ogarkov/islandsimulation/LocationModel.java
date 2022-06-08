package ru.javarush.ogarkov.islandsimulation;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LocationModel {
    // Ширина, высота острова
    private final int width;
    private final int height;
    // Размер ячейки
    private final int cellSize;
    // Массив Локаций
    private Location[][] population;

    public LocationModel(int width, int height, int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
    }

    public void initialize() {
        fillArea();
        createSimulation();
    }

    private void fillArea() {
        population = new Location[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Rectangle cellBackground = new Rectangle();
                cellBackground.setWidth(cellSize);
                cellBackground.setHeight(cellSize);
                Location location = new Location(cellBackground, new Text(), new ImageView());
                population[x][y] = location;
                location.setPositionX(x);
                location.setPositionY(y);
            }
        }
    }

    private void createSimulation() {
        // Здесь будет сложная логика по созданию симуляции
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Location location = population[x][y];
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

    public Location[][] getPopulation() {
        return population;
    }
}
