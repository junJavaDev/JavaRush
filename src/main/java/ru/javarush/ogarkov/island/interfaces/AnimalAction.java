package ru.javarush.ogarkov.island.interfaces;

import ru.javarush.ogarkov.island.entity.abstracts.BasicItem;

public interface AnimalAction {

    void eat(BasicItem food);
    void move();
    void reproduce();
}
