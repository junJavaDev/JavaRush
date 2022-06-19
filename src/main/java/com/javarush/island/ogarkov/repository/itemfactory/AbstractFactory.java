package com.javarush.island.ogarkov.repository.itemfactory;

import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.util.Randomizer;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractFactory implements Factory{
    protected final AtomicLong created = new AtomicLong();

    protected Factory getRandomFactory(Items parent) {
        if (!parent.getChildren().isEmpty()) {
            List<Items> children = parent.getChildren();
            int childrenCount = children.size();
            int randomChildIndex = Randomizer.getIntOriginOne(childrenCount);
            return children.get(randomChildIndex).getFactory();
        } else return parent.getFactory();
    }

    @Override
    public long getCreatedItemsCount() {
        return created.get();
    }

    @Override
    public void addCreatedItem() {
        created.incrementAndGet();
    }
}
