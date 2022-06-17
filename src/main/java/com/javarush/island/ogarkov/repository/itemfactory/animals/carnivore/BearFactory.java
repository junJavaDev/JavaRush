package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.Bear;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class BearFactory extends CarnivoreFactory {
    @Override
    public Bear createItem() {
        Factory parentFactory = Items.BEAR.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Bear();
    }
}
