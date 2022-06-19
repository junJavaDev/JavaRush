package com.javarush.island.ogarkov.interfaces;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;

public interface AnimalAction {

    void eat(Organism food);
    Territory move(Cell startCell);
    void reproduce();
}
