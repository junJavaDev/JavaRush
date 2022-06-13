package ru.javarush.ogarkov.islandsimulation.location;

import javafx.scene.paint.Color;

import ru.javarush.ogarkov.islandsimulation.settings.Items;

import static ru.javarush.ogarkov.islandsimulation.settings.Setting.*;

// Остров, содержит массив локаций, отображает лидера каждой локации
public class Island {
    private Location[][] locations;

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
                leader.addGrid(x, y, ISLAND_GRID_SIZE);
                fillCellColor(leader);
            }
        }
    }

    public void fillCellColor(Territory territory) {
        Items item = territory.getResident().getItem();
        if (item.is(Items.HERBIVORE)) {
            territory.setCellColor(Color.BLANCHEDALMOND);
        } else if (item.is(Items.CARNIVORE)) {
            territory.setCellColor(Color.BLACK);
        } else territory.setCellColor(Color.OLIVEDRAB);
    }

    public Location[][] getLocations() {
        return locations;
    }
}
