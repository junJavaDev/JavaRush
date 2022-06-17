package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Buffalo;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class BuffaloFactory extends AbstractFactory {
    @Override
    public Buffalo createItem() {
        Factory parentFactory = Items.BUFFALO.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Buffalo();
    }
}
