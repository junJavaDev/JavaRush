package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.entity.animals.Animal;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Setting;

import java.util.Set;

public abstract class HerbivoreAnimal extends Animal {

    public HerbivoreAnimal() {
        lifeLength = Setting.get().getHerbivoreLifeLength();
        hunger = item.getMaxFood() * Setting.get().getHerbivoreHunger();
        chanceToReproduce = Setting.get().getHerbivoreChanceToReproduce();
    }

    @Override
    public void reproduce(Cell cell) {
        if (weight > item.getMaxWeight() * Setting.get().getHerbivoreWeightToReproduce()) {
            atomicReproduce(cell, chanceToReproduce);
        }
    }

    @Override
    protected boolean eatIt(Cell cellWithFood) {
        Set<Organism> population = cellWithFood.getPopulation();
        while (weight < item.getMaxWeight()) {
            Organism food = population.iterator().next();
            weight = Math.min(item.getMaxWeight(), weight + food.getWeight());
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
