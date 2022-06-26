package com.javarush.island.ogarkov.entity.plant;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.settings.Setting;

public abstract class Plant extends Organism {

    public Plant() {
        lifeLength = Setting.PLANT_LIFE_LENGTH;
    }

    @Override
    public boolean reproduce(Cell cell) {
        return atomicReproduce(cell, Setting.PLANT_CHANCE_TO_REPRODUCE);
    }
}
