package ru.javarush.ogarkov.island.repository.itemfactory;

import ru.javarush.ogarkov.island.entity.Item;

public interface Factory{
    Item createItem();

    void addCreatedItem();
    long getCreatedItemsCount();



}
