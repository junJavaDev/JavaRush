package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.entity.carnivore.Bear;
import ru.javarush.ogarkov.island.factory.Factory;
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
