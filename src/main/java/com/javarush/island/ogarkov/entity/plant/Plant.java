package com.javarush.island.ogarkov.entity.plant;

import com.javarush.island.ogarkov.entity.Organism;

public abstract class Plant extends Organism {
//    @Override
//    protected boolean atomicReproduce(Cell cell, int chance) {
//        boolean isReproduced = super.atomicReproduce(cell, chance);
//        if (!isReproduced) {
//            cell.getLock().lock();
//            try {
//                if (cell.getResidentItem().is(Items.LANDFORM)) {
//                    Set<Organism> population = cell.getPopulation();
//                    Organism plant = Items.PLANT.getFactory().createItem();
//                    Items newResidentItem = plant.getItem();
//                    population.add(plant);
//                    cell.setResidentItem(newResidentItem);
//                    int plantsToReproduce = Randomizer.getIntOriginOne(Setting.PLANT_REPRODUCED_PER_EMPTY_CELL);
//                    for (int plantIndex = 1; plantIndex < plantsToReproduce; plantIndex++) {
//                        Organism nextPlant = newResidentItem.getFactory().createItem();
//                        population.add(nextPlant);
//                    }
//                    isReproduced = true;
//                }
//            } finally {
//                cell.getLock().unlock();
//            }
//        }
//        return isReproduced;
//    }
}
