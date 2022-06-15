package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.entity.carnivore.Fox;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class FoxFactory extends CarnivoreFactory {
    @Override
    public Fox createItem() {
        Factory parentFactory = Items.FOX.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Fox();
    }
}
