package com.javarush.island.ogarkov.interfaces;

import com.javarush.island.ogarkov.entity.Item;

public interface AnimalAction {

    void eat(Item food);
    void move();
    void reproduce();
}
