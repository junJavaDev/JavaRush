package ru.javarush.ogarkov.island.entity.animals.carnivore;

import ru.javarush.ogarkov.island.entity.animals.Animal;
import ru.javarush.ogarkov.island.settings.Setting;
public abstract class CarnivoreAnimal extends Animal {

    public CarnivoreAnimal() {
        hunger = foodPerSatiation * Setting.CARNIVORE_HUNGER;
    }
}
