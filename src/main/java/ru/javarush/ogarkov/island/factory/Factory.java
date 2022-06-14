package ru.javarush.ogarkov.island.factory;

import ru.javarush.ogarkov.island.entity.abstracts.BasicItem;
import ru.javarush.ogarkov.island.settings.Items;

public interface Factory{
    BasicItem createItem(Items item);
    int getCreatedItemsCount();



}
