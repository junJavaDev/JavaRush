package ru.javarush.island.ogarkov.entity.plant;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Setting;

public abstract class Plant extends Organism {

    public Plant() {
        lifeLength = Setting.PLANT_LIFE_LENGTH;
    }

    @Override
    public void reproduce(Cell cell) {
        atomicReproduce(cell, Setting.PLANT_CHANCE_TO_REPRODUCE);
    }
}
