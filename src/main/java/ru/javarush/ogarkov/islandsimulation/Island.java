package ru.javarush.ogarkov.islandsimulation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Text;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.CarnivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.HerbivoreAnimal;

// Остров, содержит массив локаций, отображает лидера каждой локации
public class Island {
    protected final int width;
    protected final int height;
    protected final int cellSize;
    // Массив Локаций
    private Location[][] locations;
    private static Island model;

    public Island(int width, int height, int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        initialize();
    }

    public static void setModel(Island model) {
        Island.model = model;
    }

    public void initialize() {
        fillArea();
    }

    private void fillArea() {
        locations = new Location[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Location location = new Location(Setting.LOCATION_WIDTH, Setting.LOCATION_HEIGHT, Setting.CELL_SIZE);
                locations[x][y] = location;
                Territory leader = location.getLeader();
                leader.setCellImage(leader.population[0].getIcon());
                leader.setPosition(x, y, Setting.ISLAND_GRID_SIZE);
                fillCellColor(leader);
            }
        }
    }



    public void fillCellColor(Territory territory) {
        if (territory.population[0] instanceof HerbivoreAnimal) {
            territory.setCellColor(Color.BLANCHEDALMOND);
        } else if (territory.population[0] instanceof CarnivoreAnimal) {
            territory.setCellColor(Color.BLACK);
        } else territory.setCellColor(Color.OLIVEDRAB);
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
