package ru.javarush.ogarkov.islandsimulation.location;

import javafx.scene.paint.Color;

import ru.javarush.ogarkov.islandsimulation.settings.Items;

import static ru.javarush.ogarkov.islandsimulation.settings.Setting.*;

// Остров, содержит массив локаций, отображает лидера каждой локации
public class Island {
    public static Island model;
    private Location[][] locations;

    public static Island createModel() {
        model = new Island();
        return model;
    }

    public Island() {
        initialize();
    }

    public void initialize() {
        fillIsland();
    }

    private void fillIsland() {
        locations = new Location[ISLAND_WIDTH][ISLAND_HEIGHT];
        for (int x = 0; x < ISLAND_WIDTH; x++) {
            for (int y = 0; y < ISLAND_HEIGHT; y++) {
                Location location = new Location(x, y);
                locations[x][y] = location;
                Territory leader = location.getLeader();
                leader.setCellImage(leader.getIcon());
                leader.addIslandGrid(x, y, ISLAND_GRID_SIZE);
                leader.setIslandCellColor();
            }
        }
    }

    public static void resetIslandColor() {
        for (int x = 0; x < ISLAND_WIDTH; x++) {
            for (int y = 0; y < ISLAND_HEIGHT; y++) {
                model.locations[x][y].getLeader().setIslandCellColor();
            }
        }
    }

    public Location[][] getLocations() {
        return locations;
    }
}
