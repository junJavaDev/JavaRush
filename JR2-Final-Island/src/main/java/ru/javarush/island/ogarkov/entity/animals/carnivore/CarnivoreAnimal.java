package ru.javarush.island.ogarkov.entity.animals.carnivore;

import ru.javarush.island.ogarkov.entity.animals.Animal;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Setting;
public abstract class CarnivoreAnimal extends Animal {

    public CarnivoreAnimal() {
        lifeLength = Setting.get().getCarnivoreLifeLength();
        hunger = item.getMaxFood() * Setting.get().getCarnivoreHunger();
        chanceToReproduce = Setting.get().getCarnivoreChanceToReproduce();
    }

    @Override
    public void reproduce(Cell cell) {
        if (weight > item.getMaxWeight() * Setting.get().getCarnivoreWeightToReproduce()) {
            atomicReproduce(cell, chanceToReproduce);
        }
    }
}
