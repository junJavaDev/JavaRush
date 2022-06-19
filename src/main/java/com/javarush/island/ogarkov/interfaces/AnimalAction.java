package com.javarush.island.ogarkov.interfaces;

import com.javarush.island.ogarkov.entity.Organizm;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;

public interface AnimalAction {

    void eat(Organizm food);
    Territory move(Cell startCell);
    void reproduce();
}
