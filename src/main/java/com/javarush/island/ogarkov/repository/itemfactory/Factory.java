package com.javarush.island.ogarkov.repository.itemfactory;

import com.javarush.island.ogarkov.entity.Organism;

public interface Factory{
    Organism createItem();

    void addCreatedItem();
    long getCreatedItemsCount();



}
