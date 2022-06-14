package ru.javarush.ogarkov.island.factory;

import ru.javarush.ogarkov.island.entity.abstracts.BasicItem;
import ru.javarush.ogarkov.island.settings.Items;
import ru.javarush.ogarkov.island.util.Randomizer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractFactory implements Factory {
    public final AtomicInteger count = new AtomicInteger();

    @Override
    public int getCreatedItemsCount() {
        return count.get();
    }

    protected BasicItem createRandomItem(Items item) {
        List<Items> children = item.getChildren();
        int childrenCount = children.size();
        int randomChildIndex = Randomizer.getInt(childrenCount);
        Items randomChild = children.get(randomChildIndex);
        return randomChild.createItem();
    }
}
