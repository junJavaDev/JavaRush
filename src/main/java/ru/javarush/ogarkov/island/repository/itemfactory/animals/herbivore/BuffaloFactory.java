package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Buffalo;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
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
