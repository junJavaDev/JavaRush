package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Duck;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class DuckFactory extends AbstractFactory {
    @Override
    public Duck createItem() {
        Factory parentFactory = Items.DUCK.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Duck();
    }
}
