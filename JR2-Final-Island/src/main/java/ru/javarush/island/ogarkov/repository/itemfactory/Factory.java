package ru.javarush.island.ogarkov.repository.itemfactory;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.settings.Items;

public interface Factory {
    Organism createItem();

    void addCreatedItem(Items item);

    int getCreatedItemsCount();


}
