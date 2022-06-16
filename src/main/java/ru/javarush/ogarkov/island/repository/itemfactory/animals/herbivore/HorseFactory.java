package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Horse;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class HorseFactory extends AbstractFactory {
    @Override
    public Horse createItem() {
        Factory parentFactory = Items.HORSE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Horse();
    }
}
