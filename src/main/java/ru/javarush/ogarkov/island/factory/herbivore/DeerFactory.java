package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Deer;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class DeerFactory extends AbstractFactory {
    @Override
    public Deer createItem() {
        Factory parentFactory = Items.DEER.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Deer();
    }
}
