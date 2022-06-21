package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.services.UpdateViewWorker;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;
import com.javarush.island.ogarkov.util.Randomizer;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

import static com.javarush.island.ogarkov.settings.Items.*;
import static com.javarush.island.ogarkov.settings.Setting.*;

public class TerritoryCreator {

    public Territory createTerritory(int rows, int cols) {
        Territory territory = new Territory(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var population = getRandomPopulation();
                var cell = new Cell(row, col, territory);
                cell.setPopulation(population);
                // TODO: 21.06.2022 Просится метод 
                cell.setResidentItem(population.iterator().next().getItem());
                cell.setOnMouseClicked(event -> {
                    UpdateViewWorker.setTerritoryToView(territory);
                });
                territory.setCellPosition(row, col, cell);
                territory.getCellsPopulation().put(cell, population);
            }
        }
        return territory;
    }

    public Territory createTerritoryViewModel(int rows, int cols) {
        Territory model = new Territory(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var cell = new Cell(row, col, model);
                cell.setCellColor(Color.LIGHTGREY);
                cell.getCellBackground().setHeight(Setting.TERRITORY_CELL_HEIGHT);
                cell.addGrid(col, row, TERRITORY_GRID_SIZE);
                model.setCellPosition(row, col, cell);
                model.getCellsPopulation().put(cell, getLandform());
            }
        }
        return model;
    }

    public Set<Organism> getPopulation(Items item, int maxCount) {
        var population = new HashSet<Organism>();
        int amount = Randomizer.getIntOriginOne(maxCount);
        Items randomItem = item.getRandom();
        for (int i = 0; i < amount; i++) {
            Organism resident = randomItem.getFactory().createItem();
            population.add(resident);
        }
        return population;
    }

    public Set<Organism> getRandomPopulation() {
        int maxProbability = PLANT_PROPABILITY + HERBIVORE_PROPABILITY + CARNIVORE_PROPABILITY;
        int probability = Randomizer.getInt(maxProbability);
        if (probability < PLANT_PROPABILITY) {
            return getPopulation(PLANT, PLANT_PER_CELL);
        } else if (probability < PLANT_PROPABILITY + HERBIVORE_PROPABILITY) {
            return getPopulation(HERBIVORE, HERBIVORE_PER_CELL);
        } else
            return getPopulation(CARNIVORE, CARNIVORE_PER_CELL);
    }

    public Set<Organism> getLandform() {
        var landformPopulation = new HashSet<Organism>();
        Items randomLandformItem = LANDFORM.getRandom();
        Organism resident = randomLandformItem.getFactory().createItem();
        landformPopulation.add(resident);
        return landformPopulation;
    }
}
