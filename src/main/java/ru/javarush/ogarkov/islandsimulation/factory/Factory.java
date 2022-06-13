package ru.javarush.ogarkov.islandsimulation.factory;

import ru.javarush.ogarkov.islandsimulation.item.abstracts.BasicItem;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public interface Factory {
    BasicItem createItem(Items item);
}
