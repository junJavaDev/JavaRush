package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Goat;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
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
