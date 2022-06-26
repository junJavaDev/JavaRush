package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.entity.landform.Landform;
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
                var population = createRandomPopulation();
                Items residentItem = population.iterator().next().getItem();
                Cell cell = new Cell(territory);
                territory.getCells().add(cell);
                cell.setPopulation(population);
                cell.setResidentItem(residentItem);
                cell.setLandform(createRandomLandform());
            }
        }
        return territory;
    }

    public Set<Organism> createPopulation(Items item, int maxCount) {
        var population = new HashSet<Organism>();
        int amount = Randomizer.getIntOriginOne(maxCount);
        Items randomItem = item.getRandom();
        for (int i = 0; i < amount; i++) {
            Organism resident = randomItem.getFactory().createItem();
            population.add(resident);
        }
        return population;
    }

    public Set<Organism> createRandomPopulation() {
        int maxProbability = CELL_PLANT_PROBABILITY + CELL_HERBIVORE_PROBABILITY + CELL_CARNIVORE_PROBABILITY;
        int probability = Randomizer.getInt(maxProbability);
        if (probability < CELL_PLANT_PROBABILITY) {
            return createPopulation(PLANT, PLANT_INIT_PER_CELL);
        } else if (probability < CELL_PLANT_PROBABILITY + CELL_HERBIVORE_PROBABILITY) {
            return createPopulation(HERBIVORE, HERBIVORE_INIT_PER_CELL);
        } else
            return createPopulation(CARNIVORE, CARNIVORE_INIT_PER_CELL);
    }

    public Landform createRandomLandform() {
        return (Landform) LANDFORM.getFactory().createItem();
    }
}
