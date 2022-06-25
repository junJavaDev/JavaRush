package com.javarush.island.ogarkov.entity.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.settings.Setting;

public abstract class CarnivoreAnimal extends Animal {

    public CarnivoreAnimal() {
        lifeLength = 6;
        hunger = foodPerSatiation * Setting.CARNIVORE_HUNGER;
    }
}
