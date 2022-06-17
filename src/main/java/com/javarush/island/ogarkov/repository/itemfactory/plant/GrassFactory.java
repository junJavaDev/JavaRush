package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Grass;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class GrassFactory extends AbstractFactory {
    @Override
    public Grass createItem() {
        Factory parentFactory = Items.GRASS.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Grass();
    }
}
