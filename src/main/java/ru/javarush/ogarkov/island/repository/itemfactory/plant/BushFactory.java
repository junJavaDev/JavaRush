package ru.javarush.ogarkov.island.repository.itemfactory.plant;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.plant.Bush;
import ru.javarush.ogarkov.island.settings.Items;

public class BushFactory extends AbstractFactory {
    @Override
    public Bush createItem() {
        Factory parentFactory = Items.BUSH.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Bush();
    }
}
