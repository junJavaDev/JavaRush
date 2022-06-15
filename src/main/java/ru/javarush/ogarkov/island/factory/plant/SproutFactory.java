package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Sprout;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class SproutFactory extends AbstractFactory {
    @Override
    public Sprout createItem() {
        Factory parentFactory = Items.SPROUT.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Sprout();
    }
}
