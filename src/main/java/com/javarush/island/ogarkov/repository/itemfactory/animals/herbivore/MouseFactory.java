package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Mouse;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class MouseFactory extends AbstractFactory {
    @Override
    public Mouse createItem() {
        Factory parentFactory = Items.MOUSE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Mouse();
    }
}
