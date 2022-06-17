package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Sprout;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class SproutFactory extends AbstractFactory {
    @Override
    public Sprout createItem() {
        Factory parentFactory = Items.SPROUT.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Sprout();
    }
}
