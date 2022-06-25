package com.javarush.island.ogarkov.entity.animals.herbivore;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.settings.Setting;

import java.util.Set;

public abstract class HerbivoreAnimal extends Animal {

    public HerbivoreAnimal() {
        lifeLength = Setting.HERBIVORE_LIFE_LENGTH;
        hunger = foodPerSatiation * Setting.HERBIVORE_HUNGER;
    }

    @Override
    public boolean reproduce(Cell cell) {
        return atomicReproduce(cell, Setting.HERBIVORE_CHANCE_TO_REPRODUCE);
    }

    @Override
    protected boolean eatIt(Cell cellWithFood) {
        Set<Organism> population = cellWithFood.getPopulation();
        while (weight < maxWeight) {
            Organism food = population.iterator().next();
            weight = Math.min(maxWeight, weight + food.getWeight());
            population.remove(food);
            if (population.isEmpty()) {
                population.add(cellWithFood.getLandform());
                cellWithFood.setResidentItem(cellWithFood.getLandform().getItem());
                break;
            }
        }
        return true;
    }
}
