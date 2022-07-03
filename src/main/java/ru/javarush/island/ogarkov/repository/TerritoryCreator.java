package ru.javarush.island.ogarkov.repository;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.entity.landform.Landform;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.location.Territory;
import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.HashSet;
import java.util.Set;

import static ru.javarush.island.ogarkov.settings.Setting.*;

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

    public Set<Organism> createPopulation(Items item, int minCount) {
        var population = new HashSet<Organism>();
        Items randomItem = item.getRandom();
        int amount;
        if (minCount < item.getMaxCount()) {
            amount = Randomizer.getInt(minCount, item.getMaxCount());
        } else amount = minCount;
        for (int i = 0; i < amount; i++) {
            Organism resident = randomItem.getFactory().createItem();
            population.add(resident);
        }
        return population;
    }

    public Set<Organism> createRandomPopulation() {
        int plantHerbivoreProbability =
                CELL_PLANT_PROBABILITY +
                        CELL_HERBIVORE_PROBABILITY;
        int allProbabilities =
                plantHerbivoreProbability +
                        CELL_CARNIVORE_PROBABILITY;

        int probability = Randomizer
                .getInt(allProbabilities);

        if (probability < CELL_PLANT_PROBABILITY) {
            return createPopulation(
                    Items.PLANT,
                    PLANT_INIT_PER_CELL
            );
        } else if (probability < plantHerbivoreProbability) {
            return createPopulation(
                    Items.HERBIVORE,
                    HERBIVORE_INIT_PER_CELL
            );
        } else return createPopulation(
                    Items.CARNIVORE,
                    CARNIVORE_INIT_PER_CELL
            );
    }

    public Landform createRandomLandform() {
        return (Landform) Items.LANDFORM
                .getFactory()
                .createItem();
    }
}
