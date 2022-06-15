package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Goat;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class GoatFactory extends AbstractFactory {
    @Override
    public Goat createItem() {
        Factory parentFactory = Items.GOAT.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Goat();
    }
}
