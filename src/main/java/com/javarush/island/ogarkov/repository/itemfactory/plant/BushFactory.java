package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Bush;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class BushFactory extends AbstractFactory {
    @Override
    public Bush createItem() {
        Factory parentFactory = Items.BUSH.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Bush();
    }
}
