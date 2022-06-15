package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.entity.carnivore.Wolf;
import ru.javarush.ogarkov.island.factory.Factory;
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
