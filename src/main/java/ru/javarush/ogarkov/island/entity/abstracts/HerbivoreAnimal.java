package ru.javarush.ogarkov.island.entity.abstracts;

import ru.javarush.ogarkov.island.settings.Setting;

public abstract class HerbivoreAnimal extends Animal {

    public HerbivoreAnimal() {
        hunger = foodPerSatiation * Setting.HERBIVORE_HUNGER;
    }
}
