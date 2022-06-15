package ru.javarush.ogarkov.island.factory;

import ru.javarush.ogarkov.island.entity.abstracts.BasicItem;

public interface Factory{
    BasicItem createItem();

    void addCreatedItem();
    long getCreatedItemsCount();



}
