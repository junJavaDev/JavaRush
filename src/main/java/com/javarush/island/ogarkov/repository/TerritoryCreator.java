package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.util.Randomizer;

import java.util.HashSet;
import java.util.Set;

import static com.javarush.island.ogarkov.settings.Items.*;
import static com.javarush.island.ogarkov.settings.Setting.*;

public class TerritoryCreator {

    CellCreator cellCreator;

    public TerritoryCreator(CellCreator cellCreator) {
        this.cellCreator = cellCreator;
    }

    public Territory createTerritory(int rows, int cols) {
        Territory territory = new Territory(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var cell = cellCreator.createCell(row, col, territory);
                territory.setCell(row, col, cell);
                territory.getCells().put(cell, getRandomPopulation());
                cell.setResident(territory.getCells().get(cell).iterator().next());
            }
        }
//        territory.getLeader();
        territory.addMouseClickedAction();
        return territory;
    }

    public Territory createTerritoryModel(int rows, int cols) {
        Territory model = new Territory(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var cell = cellCreator.createTerritoryModelCell(row, col, model);
                model.setCell(row, col, cell);
                model.getCells().put(cell, getLandform());
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
