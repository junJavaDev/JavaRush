package com.javarush.island.ogarkov.entity.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.settings.Setting;

public abstract class HerbivoreAnimal extends Animal {

    public HerbivoreAnimal() {
        hunger = foodPerSatiation * Setting.HERBIVORE_HUNGER;
    }
}
