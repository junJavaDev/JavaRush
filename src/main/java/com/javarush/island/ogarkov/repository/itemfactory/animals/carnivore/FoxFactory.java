package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.Fox;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class FoxFactory extends CarnivoreFactory {
    @Override
    public Fox createItem() {
        Factory parentFactory = Items.FOX.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Fox();
    }
}
