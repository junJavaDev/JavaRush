package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Buffalo;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class BuffaloFactory extends AbstractFactory {
    @Override
    public Buffalo createItem() {
        Factory parentFactory = Items.BUFFALO.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Buffalo();
    }
}
