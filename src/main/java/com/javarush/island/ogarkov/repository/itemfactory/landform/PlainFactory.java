package com.javarush.island.ogarkov.repository.itemfactory.landform;

import com.javarush.island.ogarkov.entity.landform.Plain;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class PlainFactory extends AbstractFactory {
    @Override
    public Plain createItem() {
        Factory parentFactory = Items.PLAIN.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Plain();
    }
}

