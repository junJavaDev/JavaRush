package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Sheep;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class SheepFactory extends AbstractFactory {
    @Override
    public Sheep createItem() {
        Factory parentFactory = Items.SHEEP.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Sheep();
    }
}
