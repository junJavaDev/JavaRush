package ru.javarush.ogarkov.island.location;

import ru.javarush.ogarkov.island.Controller;

import static ru.javarush.ogarkov.island.settings.Setting.*;

// Остров, содержит массив локаций, отображает лидера каждой локации
public class Island {
    public static Island model;
    private Location[][] locations;
    static Controller controller;
    // TODO: 16.06.2022 убрать статик, переделать контроллёр

    public static Island createModel() {
        model = new Island();
        return model;
    }

    public static void setController(Controller controller) {
        Island.controller = controller;
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
                Location location = new Location(this, x, y, controller);
                locations[x][y] = location;
                Territory leader = location.getLeader();
                leader.setCellImage(leader.getIcon());
                leader.addIslandGrid(x, y, ISLAND_GRID_SIZE);
                leader.setIslandCellColor();
            }
        }
    }


    public void reload() {
        for (int x = 0; x < ISLAND_WIDTH; x++) {
            for (int y = 0; y < ISLAND_HEIGHT; y++) {
                Territory leader = locations[x][y].getLeader();
                leader.setCellImage(leader.getIcon());
                leader.addIslandGrid(x, y, ISLAND_GRID_SIZE);
//                leader.setIslandCellColor();
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
