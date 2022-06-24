package com.javarush.island.ogarkov.interfaces;

import com.javarush.island.ogarkov.location.Cell;

public interface Movable {
    boolean move(Cell startCell);
}
