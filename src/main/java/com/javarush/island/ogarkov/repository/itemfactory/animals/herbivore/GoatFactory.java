package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Goat;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class GoatFactory extends AbstractFactory {
    @Override
    public Goat createItem() {
        Factory parentFactory = Items.GOAT.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Goat();
    }
}
