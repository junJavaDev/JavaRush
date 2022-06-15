package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Bush;
import ru.javarush.ogarkov.island.factory.Factory;
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
