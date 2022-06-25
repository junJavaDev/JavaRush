package com.javarush.island.ogarkov.entity.plant;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.settings.Setting;

public abstract class Plant extends Organism {
    @Override
    public boolean reproduce(Cell cell) {
        return atomicReproduce(cell, Setting.PLANT_CHANCE_TO_REPRODUCE);
    }
}
