package com.javarush.island.ogarkov.entity.plant;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;

public abstract class Plant extends Organism {
    @Override
    public boolean reproduce(Cell cell) {
        return atomicReproduce(cell, 30);
    }
}
