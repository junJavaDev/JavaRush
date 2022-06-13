package ru.javarush.ogarkov.islandsimulation.factory;

import ru.javarush.ogarkov.islandsimulation.item.abstracts.BasicItem;
import ru.javarush.ogarkov.islandsimulation.settings.Items;
import ru.javarush.ogarkov.islandsimulation.settings.Utils;

import java.util.List;

public interface Factory {
    BasicItem createItem(Items item);

    default BasicItem createRandomItem(Items item) {
        List<Items> children = item.getChildren();
        int childrenCount = children.size();
        int randomChildrenIndex = Utils.getRandomInt(childrenCount);
        return children.get(randomChildrenIndex).createItem();
    }
}
