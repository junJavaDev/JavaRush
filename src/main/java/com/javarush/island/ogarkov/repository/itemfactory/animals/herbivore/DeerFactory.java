package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Deer;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class DeerFactory extends AbstractFactory {
    @Override
    public Deer createItem() {
        Factory parentFactory = Items.DEER.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Deer();
    }
}
