package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.Eagle;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class EagleFactory extends CarnivoreFactory {
    @Override
    public Eagle createItem() {
        Factory parentFactory = Items.EAGLE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Eagle();
    }
}
