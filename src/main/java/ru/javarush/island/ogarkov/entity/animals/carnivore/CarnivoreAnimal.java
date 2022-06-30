package ru.javarush.island.ogarkov.entity.animals.carnivore;

import ru.javarush.island.ogarkov.entity.animals.Animal;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Setting;

public abstract class CarnivoreAnimal extends Animal {

    public CarnivoreAnimal() {
        lifeLength = Setting.CARNIVORE_LIFE_LENGTH;
    }

    @Override
    public void reproduce(Cell cell) {
        atomicReproduce(cell, Setting.CARNIVORE_CHANCE_TO_REPRODUCE);
    }
}
