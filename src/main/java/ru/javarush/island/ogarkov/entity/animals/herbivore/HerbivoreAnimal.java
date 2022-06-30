package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.entity.animals.Animal;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Setting;

import java.util.Set;

public abstract class HerbivoreAnimal extends Animal {

    public HerbivoreAnimal() {
        lifeLength = Setting.HERBIVORE_LIFE_LENGTH;
    }

    @Override
    public void reproduce(Cell cell) {
        atomicReproduce(cell, Setting.HERBIVORE_CHANCE_TO_REPRODUCE);
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
