package ru.javarush.island.ogarkov.entity.landform;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.settings.Setting;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.Set;

public abstract class Landform extends Organism {
    public Landform() {
        lifeLength = Setting.LANDFORM_LIFE_LENGTH;
    }

    @Override
    public void reproduce(Cell cell) {
        atomicPlantSpawn(cell);
    }

    protected void atomicPlantSpawn(Cell cell) {
        cell.getLock().lock();
        try {
            if (cell.getResidentItem().is(Items.LANDFORM)) {
                Set<Organism> population = cell.getPopulation();
                population.remove(this);
                Organism plant = Items.PLANT.getFactory().createItem();
                Items newResidentItem = plant.getItem();
                population.add(plant);
                cell.setResidentItem(newResidentItem);
                int plantsToSpawn = Randomizer.getIntOriginOne(Setting.PLANT_SPAWNED_PER_EMPTY_CELL);
                for (int plantIndex = 1; plantIndex < plantsToSpawn; plantIndex++) {
                    Organism nextPlant = newResidentItem.getFactory().createItem();
                    population.add(nextPlant);
                }
            }
        } finally {
            cell.getLock().unlock();
        }
    }
}
