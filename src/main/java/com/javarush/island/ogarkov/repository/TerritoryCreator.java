package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.util.Randomizer;

import java.util.HashSet;
import java.util.Set;

import static com.javarush.island.ogarkov.settings.Items.*;
import static com.javarush.island.ogarkov.settings.Setting.*;

public class TerritoryCreator {

    public Territory createTerritory(int rows, int cols) {
        Territory territory = new Territory();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var population = getRandomPopulation();
                Cell cell = new Cell(territory);
                territory.getCells().add(cell);
                cell.setPopulation(population);
                cell.setResidentItem();
            }
        }
        return territory;
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
}
