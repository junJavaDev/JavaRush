package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Duck;
import ru.javarush.ogarkov.island.factory.Factory;
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
