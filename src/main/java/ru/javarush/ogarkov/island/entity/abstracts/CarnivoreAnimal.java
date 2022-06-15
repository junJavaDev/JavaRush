package ru.javarush.ogarkov.island.entity.abstracts;

import ru.javarush.ogarkov.island.settings.Setting;
public abstract class CarnivoreAnimal extends Animal {

    public CarnivoreAnimal() {
        hunger = foodPerSatiation * Setting.CARNIVORE_HUNGER;
    }
}
