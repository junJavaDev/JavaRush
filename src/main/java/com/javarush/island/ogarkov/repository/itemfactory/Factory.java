package com.javarush.island.ogarkov.repository.itemfactory;

import com.javarush.island.ogarkov.entity.Organizm;

public interface Factory{
    Organizm createItem();

    void addCreatedItem();
    long getCreatedItemsCount();



}
