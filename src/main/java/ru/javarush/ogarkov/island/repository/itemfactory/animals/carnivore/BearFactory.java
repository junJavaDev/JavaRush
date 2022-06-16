package ru.javarush.ogarkov.island.repository.itemfactory.animals.carnivore;

import ru.javarush.ogarkov.island.entity.animals.carnivore.Bear;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class BearFactory extends CarnivoreFactory {
    @Override
    public Bear createItem() {
        Factory parentFactory = Items.BEAR.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Bear();
    }
}
