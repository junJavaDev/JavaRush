package com.javarush.island.ogarkov.entity.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.settings.Setting;

public abstract class CarnivoreAnimal extends Animal {

    public CarnivoreAnimal() {
        lifeLength = Setting.CARNIVORE_LIFE_LENGTH;
        hunger = foodPerSatiation * Setting.CARNIVORE_HUNGER;
    }

    @Override
    public boolean reproduce(Cell cell) {
        return atomicReproduce(cell, Setting.CARNIVORE_CHANCE_TO_REPRODUCE);
    }
}
