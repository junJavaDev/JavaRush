package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Horse;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class HorseFactory extends AbstractFactory {
    @Override
    public Horse createItem() {
        Factory parentFactory = Items.HORSE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Horse();
    }
}
