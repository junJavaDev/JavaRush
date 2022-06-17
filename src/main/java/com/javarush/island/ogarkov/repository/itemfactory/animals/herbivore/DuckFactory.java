package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Duck;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class DuckFactory extends AbstractFactory {
    @Override
    public Duck createItem() {
        Factory parentFactory = Items.DUCK.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Duck();
    }
}
