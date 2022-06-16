package ru.javarush.ogarkov.island.repository.itemfactory.animals.carnivore;

import ru.javarush.ogarkov.island.entity.animals.carnivore.Wolf;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class WolfFactory extends CarnivoreFactory {
    @Override
    public Wolf createItem() {
        Factory parentFactory = Items.WOLF.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Wolf();
    }
}
