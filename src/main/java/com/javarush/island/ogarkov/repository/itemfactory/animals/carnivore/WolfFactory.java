package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.Wolf;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class WolfFactory extends CarnivoreFactory {
    @Override
    public Wolf createItem() {
        Factory parentFactory = Items.WOLF.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Wolf();
    }
}
