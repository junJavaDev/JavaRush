package com.javarush.island.ogarkov.repository.itemfactory;

import com.javarush.island.ogarkov.entity.Item;

public interface Factory{
    Item createItem();

    void addCreatedItem();
    long getCreatedItemsCount();



}
