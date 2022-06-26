package com.javarush.island.ogarkov.repository.itemfactory;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.settings.Items;

public interface Factory {
    Organism createItem();

    void addCreatedItem(Items item);

    int getCreatedItemsCount();


}
