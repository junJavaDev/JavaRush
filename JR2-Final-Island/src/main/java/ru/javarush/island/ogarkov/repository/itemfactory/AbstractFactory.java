package ru.javarush.island.ogarkov.repository.itemfactory;

import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractFactory implements Factory {
    protected final AtomicInteger created = new AtomicInteger();

    @Override
    public int getCreatedItemsCount() {
        return created.get();
    }

    @Override
    public void addCreatedItem(Items item) {
        created.incrementAndGet();
        if (item.getHigherItem() != null) {
            Items parentItem = item.getHigherItem();
            parentItem.getFactory().addCreatedItem(parentItem);
        }
    }

    protected Factory getRandomFactory(Items parent) {
        if (!parent.getLowerItems().isEmpty()) {
            List<Items> children = parent.getLowerItems();
            int childrenCount = children.size();
            int randomChildIndex = Randomizer.getInt(childrenCount);
            return children.get(randomChildIndex).getFactory();
        } else return parent.getFactory();
    }
}
