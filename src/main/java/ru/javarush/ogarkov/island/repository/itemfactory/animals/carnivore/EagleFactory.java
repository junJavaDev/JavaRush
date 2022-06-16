package ru.javarush.ogarkov.island.repository.itemfactory.animals.carnivore;

import ru.javarush.ogarkov.island.entity.animals.carnivore.Eagle;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class EagleFactory extends CarnivoreFactory {
    @Override
    public Eagle createItem() {
        Factory parentFactory = Items.EAGLE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Eagle();
    }
}
