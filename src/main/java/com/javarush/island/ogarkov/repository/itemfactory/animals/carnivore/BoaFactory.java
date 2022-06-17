package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.Boa;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class BoaFactory extends CarnivoreFactory {
    @Override
    public Boa createItem() {
        Factory parentFactory = Items.BOA.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Boa();
    }
}
