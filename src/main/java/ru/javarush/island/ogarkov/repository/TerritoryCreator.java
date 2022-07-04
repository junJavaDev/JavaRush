package ru.javarush.island.ogarkov.repository;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.entity.landform.Landform;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.location.Territory;
import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.settings.Setting;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.HashSet;
import java.util.Set;

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

    public Set<Organism> createRandomPopulation() {
        //TODO Code style. Long code. Needs to be split into several methods
        // OK
        int carnivoreProbability = Setting.get().getCellCarnivoreProbability();
        int animalProbability = getAnimalProbability();
        int randomProbability = getRandomProbability();

        if (randomProbability < carnivoreProbability) {
            return createCarnivorePopulation();
        } else if (randomProbability < animalProbability) {
            return createHerbivorePopulation();
        } else return createPlantPopulation();
    }

    private Set<Organism> createPopulation(Items item, int minCount) {
        var population = new HashSet<Organism>();
        Items randomItem = item.getRandomItem();
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

    private Set<Organism> createHerbivorePopulation() {
        return createPopulation(
                Items.HERBIVORE,
                Setting.get().getHerbivoreInitPerCell()
        );
    }

    private Set<Organism> createCarnivorePopulation() {
        return createPopulation(
                Items.CARNIVORE,
                Setting.get().getCarnivoreInitPerCell()
        );
    }

    private Set<Organism> createPlantPopulation() {
        return createPopulation(
                Items.PLANT,
                Setting.get().getPlantInitPerCell()
        );
    }

    private Landform createRandomLandform() {
        return (Landform) Items.LANDFORM
                .getFactory()
                .createItem();
    }

    private int getAnimalProbability() {
        return Setting.get().getCellHerbivoreProbability() +
                Setting.get().getCellCarnivoreProbability();
    }

    private int getRandomProbability() {
        int allProbabilities = getAnimalProbability() +
                        Setting.get().getCellPlantProbability();
        return Randomizer.getInt(allProbabilities);
    }
}
