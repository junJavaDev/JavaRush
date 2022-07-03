package ru.javarush.island.ogarkov.entity.animals.carnivore;

import ru.javarush.island.ogarkov.entity.animals.Animal;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Setting;
public abstract class CarnivoreAnimal extends Animal {

    public CarnivoreAnimal() {
        lifeLength = Setting.CARNIVORE_LIFE_LENGTH;
        hunger = item.getMaxFood() * Setting.CARNIVORE_HUNGER;
        chanceToReproduce = Setting.CARNIVORE_CHANCE_TO_REPRODUCE;
    }

    @Override
    public void reproduce(Cell cell) {
        if (weight > item.getMaxWeight() * Setting.CARNIVORE_WEIGHT_TO_REPRODUCE) {
            atomicReproduce(cell, chanceToReproduce);
        }
    }
}
