package com.javarush.island.ogarkov.entity.landform;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;
import com.javarush.island.ogarkov.util.Randomizer;

import java.util.Set;

public abstract class Landform extends Organism {
    @Override
    protected boolean atomicReproduce(Cell cell, int chance) {
//            cell.getLock().lock();
//            try {
                boolean isReproduced = false;
                if (cell.getResidentItem().is(Items.LANDFORM) && !isReproducedTried) {
                    Set<Organism> population = cell.getPopulation();
                    Organism plant = Items.PLANT.getFactory().createItem();
                    Items newResidentItem = plant.getItem();
                    population.add(plant);
                    cell.setResidentItem(newResidentItem);
                    int plantsToReproduce = Randomizer.getIntOriginOne(Setting.PLANT_REPRODUCED_PER_EMPTY_CELL);
                    for (int plantIndex = 1; plantIndex < plantsToReproduce; plantIndex++) {
                        Organism nextPlant = newResidentItem.getFactory().createItem();
                        population.add(nextPlant);
                    }
                    isReproduced = true;
                    isReproducedTried = true;
                }
                return isReproduced;
//            } finally {
//                cell.getLock().unlock();
//            }
    }
}
