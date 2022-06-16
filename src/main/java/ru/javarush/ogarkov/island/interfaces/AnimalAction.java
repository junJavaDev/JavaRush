package ru.javarush.ogarkov.island.interfaces;

import ru.javarush.ogarkov.island.entity.Item;

public interface AnimalAction {

    void eat(Item food);
    void move();
    void reproduce();
}
